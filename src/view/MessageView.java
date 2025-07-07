package view;

import utility.ScreenHelper;
import java.util.Scanner;

public class MessageView {
    private static final Scanner scanner = new Scanner(System.in);

    public static void showSuccess(String message) {
        System.out.println("*Success!*\n" + message);
        ScreenHelper.pauseScreen(scanner);
    }

    public static void showError(String message) {
        System.out.println("*Error occurred!*\n" + message);
        ScreenHelper.pauseScreen(scanner);
    }

}
