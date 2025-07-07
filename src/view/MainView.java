package view;

import java.util.Scanner;
import exceptions.InvalidInputException;
import utility.ScreenHelper;
import utility.Validation;


public class MainView {
    private final AddView addView;
    private final RemoveView removeView;
    private final UpdateView updateView;
    private final ShowAllView showAllView;

    private final Scanner scanner = new Scanner(System.in);

    public MainView() {
        this.addView = new AddView();
        this.removeView = new RemoveView();
        this.updateView = new UpdateView();
        this.showAllView = new ShowAllView();

    }

    public int showMenu() {


        int input;

        while(true) {
            try {
                ScreenHelper.clearScreen();
                System.out.println("****Library management application****\n");

                System.out.println("1. Add book");
                System.out.println("2. Remove book");
                System.out.println("3. Update book");
                System.out.println("4. Show all books");
                System.out.println("5. Exit");
                System.out.print("Input: ");


                input = Integer.parseInt(scanner.nextLine());
                Validation.checkInput(input);

                break;
            }
            catch(InvalidInputException | NumberFormatException e) {
                MessageView.showError(e.getMessage());
            }
        }
        return input;

    }

    public AddView getAddView() {
        return addView;
    }

    public RemoveView getRemoveView() {
        return removeView;
    }

    public UpdateView getUpdateView() {
        return updateView;
    }

    public ShowAllView getShowAllView() {
        return showAllView;
    }



}
