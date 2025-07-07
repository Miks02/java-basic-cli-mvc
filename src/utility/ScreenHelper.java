package utility;

import java.util.Scanner;

public class ScreenHelper {
    public static void clearScreen() {
        for(int i = 0; i < 50; i++)
            System.out.println();
    }

    public static void pauseScreen(Scanner scanner) {
        System.out.println("Press enter to continue...");
        scanner.nextLine();
    }

}
