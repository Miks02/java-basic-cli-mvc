package service;

import repository.DataBookRepository;
import repository.InMemoryBookRepository;
import model.Book;
import java.util.ArrayList;
import java.util.Optional;


public class BookService {
    private final DataBookRepository dataRepo;
    private final InMemoryBookRepository memoryRepo;


    public BookService(DataBookRepository dataRepo,InMemoryBookRepository memoryRepo) {
        this.dataRepo = dataRepo;
        this.memoryRepo = memoryRepo;

    }

    public Optional<Book> findBookByTitle(String title) {
        if(dataRepo.getAllBooks().isEmpty())
            return Optional.empty();

        return memoryRepo.getBookList().stream()
                .filter(b -> b.getTitle().equalsIgnoreCase(title))
                .findFirst();
    }

    public boolean addBook(Book book) {
        if(findBookByTitle(book.getTitle()).isEmpty()) {
            dataRepo.add(book);
            return true;
        }
        return false;


    }

    public boolean removeBook(String title) {
        if(findBookByTitle(title).isPresent()) {
            dataRepo.remove(title);
            return true;
        }
        return false;

    }

    public void updateBook(String title, Book book) {
        if(findBookByTitle(title).isPresent()) {
            dataRepo.update(title, book);

        }


    }

    public Optional<ArrayList<Book>> getAllBooks() {

        if(dataRepo.getAllBooks().isEmpty())
            return Optional.empty();

        return Optional.of(dataRepo.getAllBooks().orElseThrow());
    }


}
