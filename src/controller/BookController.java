package controller;

import model.Book;
import service.BookService;
import view.MessageView;
import view.MainView;

import java.util.Optional;
import java.util.ArrayList;


public class BookController {
    private final BookService service;
    private final MainView view;


    public BookController(BookService service, MainView view) {
        this.service = service;
        this.view = view;
    }

    public void printAllBooks() {
        Optional<ArrayList<Book>> bookList;

        if(service.getAllBooks().isEmpty()) {

            MessageView.showError("No books found!");
            return;
        }

        bookList = Optional.of(service.getAllBooks().get());

        view.getShowAllView().showAllBooks(bookList.get());

        MessageView.showSuccess("All books printed successfully!");
    }

    public void start() {

        while(true) {
            switch(view.showMenu()) {
                case 1:

                    while(!service.addBook(view.getAddView().showMenu().orElseThrow()))
                        MessageView.showError("Book already exists!");
                    MessageView.showSuccess("Book added successfully!");
                    break;
                case 2:
                    if(service.getAllBooks().isEmpty()) {
                        MessageView.showError("No books found!");
                        break;
                    }

                    while(!service.removeBook(view.getRemoveView().showMenu()))
                        MessageView.showError("Book not found!\nTry Again");
                    MessageView.showSuccess("Book removed successfully!");
                    break;
                case 3:
                    while(true) {
                        String title = view.getUpdateView().showMenu();
                        if(service.findBookByTitle(title).isEmpty())
                            MessageView.showError("Book not found!\nTry Again");
                        else {
                            service.updateBook(title, view.getUpdateView().updateMenu().orElseThrow());
                            MessageView.showSuccess("Book updated successfully!");
                            break;
                        }
                    }
                    break;
                case 4:
                    printAllBooks();
                    break;
                case 5:
                    return;


            }

        }



    }

}
