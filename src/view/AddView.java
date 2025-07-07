package view;

import java.util.Scanner;
import java.util.Optional;
import exceptions.InvalidInputException;
import utility.ScreenHelper;
import model.Book;
import utility.Validation;


public class AddView {
    private final Scanner scanner = new Scanner(System.in);
    public Optional<Book> showMenu() {

        String title,author,status;
        int year;

        while(true) {
            try {

                ScreenHelper.clearScreen();
                System.out.println("****Add book****\n");

                System.out.print("Title: ");
                title = scanner.nextLine();
                Validation.checkTitle(title);

                System.out.print("Author: ");
                author = scanner.nextLine();
                Validation.checkAuthor(author);

                System.out.print("Published in: ");
                year = Integer.parseInt(scanner.nextLine());
                Validation.checkYear(year);

                System.out.print("Availability: ");
                status = scanner.nextLine();
                Validation.checkStatus(status);

                break;
            }
            catch (InvalidInputException e) {
                MessageView.showError(e.getMessage());

            }
        }

        return Optional.of(new Book(title, author , year, status));



    }
}
