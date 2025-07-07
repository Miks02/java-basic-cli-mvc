package repository;


import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.*;
import utility.LoggerHelper;
import model.Book;

public class DataBookRepository  {
    private final SqlBookRepository sqlRepo;
    private final InMemoryBookRepository memoryRepo;
    private final FileBookRepository fileRepo;
    private static final Logger logger = LoggerHelper.getLogger();

    public DataBookRepository(SqlBookRepository sqlRepo, InMemoryBookRepository memoryRepo, FileBookRepository fileRepo) {
        this.sqlRepo = sqlRepo;
        this.memoryRepo = memoryRepo;
        this.fileRepo = fileRepo;
    }

    public void isDataBaseEmpty() {
        if(memoryRepo.getBookList().isEmpty()) {
            memoryRepo.refreshList(sqlRepo.selectAll().orElseThrow());
        }

    }

    public void add(Book book) {

        try {

            sqlRepo.beginTransaction();
            isDataBaseEmpty();

            sqlRepo.insert(book);


            memoryRepo.refreshList(sqlRepo.selectAll().orElseThrow());
            fileRepo.updateFile(memoryRepo.getBookList());

            sqlRepo.commitTransaction();
            sqlRepo.closeConnection();


        }
        catch(Exception e) {
            sqlRepo.rollback();
            memoryRepo.refreshList(sqlRepo.selectAll().orElseThrow());
            sqlRepo.closeConnection();
            logger.severe("*Failed to add book*\nMessage: " + e.getMessage());
        }
    }

    public void remove(String title) {

        try {
            sqlRepo.beginTransaction();
            isDataBaseEmpty();

            sqlRepo.delete(title);

            memoryRepo.refreshList(sqlRepo.selectAll().orElseThrow());
            fileRepo.updateFile(memoryRepo.getBookList());

            sqlRepo.commitTransaction();
            sqlRepo.closeConnection();


        }
        catch(Exception e) {
            sqlRepo.rollback();
            memoryRepo.refreshList(sqlRepo.selectAll().orElseThrow());
            sqlRepo.closeConnection();
            logger.severe("*Failed to remove book*\nMessage: " + e.getMessage());
        }
    }

    public void update(String title, Book book) {
        try {
            sqlRepo.beginTransaction();
            isDataBaseEmpty();

            sqlRepo.update(title,book);
            memoryRepo.refreshList(sqlRepo.selectAll().orElseThrow());

            fileRepo.updateFile(memoryRepo.getBookList());
            sqlRepo.commitTransaction();
            sqlRepo.closeConnection();

        }
        catch (Exception e) {
            sqlRepo.rollback();
            memoryRepo.refreshList(sqlRepo.selectAll().orElseThrow());
            sqlRepo.closeConnection();
            logger.severe("*Failed to update book*\nMessage: " + e.getMessage());

        }

    }

    public Optional<ArrayList<Book>> getAllBooks() {
        try {
            sqlRepo.beginTransaction();
            isDataBaseEmpty();
            sqlRepo.commitTransaction();
            sqlRepo.closeConnection();
            logger.info("\nFetching completed!");
            if(memoryRepo.getBookList().isEmpty())
                return Optional.empty();
            return Optional.of(memoryRepo.getBookList());

        }
        catch (Exception e) {
            sqlRepo.rollback();
            sqlRepo.closeConnection();
            logger.severe("Failed to fetch data");
        }
        return Optional.empty();

    }



}
