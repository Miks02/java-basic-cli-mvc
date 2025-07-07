package view;

import java.util.Scanner;
import exceptions.InvalidInputException;
import utility.ScreenHelper;
import utility.Validation;


public class RemoveView {
    private final Scanner scanner = new Scanner(System.in);
    public String showMenu() {
        String title;
        while(true) {

            try {
                ScreenHelper.clearScreen();
                System.out.println("****Remove book by title****\n");

                System.out.print("Enter book's title: ");
                title = scanner.nextLine();
                Validation.checkTitle(title);
                break;
            }
            catch(InvalidInputException | NumberFormatException e) {
                MessageView.showError(e.getMessage());
            }

        }
        return title;

    }
}
