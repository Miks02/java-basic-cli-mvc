package repository;

import utility.LoggerHelper;
import model.Book;
import java.io.*;
import java.util.logging.Logger;
import java.util.ArrayList;

public class FileBookRepository {
    private static final Logger logger = LoggerHelper.getLogger();

    public FileBookRepository() {

    }


    public void updateFile(ArrayList<Book> bookList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("books.txt", false)))
        {
            for(Book book : bookList) {
                writer.write(book.getID() + "," + book.getTitle() + "," + book.getAuthor() + "," + book.getYear() + "," + book.getStatus());
                writer.newLine();
            }
            logger.info("File has been updated");
        }
        catch (IOException e) {
            logger.severe("*Updating the file has failed!*\nMessage: " + e.getMessage());
        }
    }



}
