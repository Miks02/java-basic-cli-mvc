package repository;

import utility.LoggerHelper;
import model.Book;
import java.util.ArrayList;
import java.util.logging.Logger;

public class InMemoryBookRepository {
    private final ArrayList<Book> bookList;
    private static final Logger logger = LoggerHelper.getLogger();

    public InMemoryBookRepository(ArrayList<Book> bookList) {

        this.bookList = bookList;
    }


    public ArrayList<Book> getBookList() {
        return bookList;
    }


    public void refreshList(ArrayList<Object> updatedList) {
        bookList.clear();
        for (Object obj : updatedList) {
            if (obj instanceof Book)
                bookList.add((Book) obj);
        }


    }

}