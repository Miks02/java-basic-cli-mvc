package view;

import java.util.Optional;
import java.util.Scanner;

import exceptions.InvalidInputException;
import model.Book;
import utility.ScreenHelper;
import utility.Validation;

public class UpdateView {
    Scanner scanner = new Scanner(System.in);

    public String showMenu() {
        String title = "";

        while(true) {
            try {
                ScreenHelper.clearScreen();
                System.out.println("****Update book****\n");

                System.out.print("Book's title: ");
                title = scanner.nextLine();
                Validation.checkTitle(title);

                break;

            } catch(InvalidInputException e) {
                MessageView.showError(e.getMessage());

            }
        }

        return title;


    }

    public Optional<Book> updateMenu() {
        String title,author,status;
        int year;

        while(true) {
            try {
                ScreenHelper.clearScreen();
                System.out.println("****Update Book****\n");

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
