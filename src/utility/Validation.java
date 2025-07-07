package utility;

import exceptions.InvalidInputException;

public class Validation {

    public static void checkInput(int input) {
        if(input < 1)
            throw new InvalidInputException("Input cannot be a number lower than 1!");
        if(input > 5)
            throw new InvalidInputException("Input cannot be a number higher than 5!");
    }

    public static void checkTitle(String title) {
        if(title.isEmpty())
            throw new InvalidInputException("Empty value entered!");
        if(title.length() < 2)
            throw new InvalidInputException("Title cannot have a length of 2 characters!");
        if(!Character.isUpperCase(title.charAt(0)))
            throw new InvalidInputException("Book title  needs to start with an upper case!");
    }

    public static void checkAuthor(String author) {
        if(author.isEmpty())
            throw new InvalidInputException("Empty value entered");
        if(author.length() < 2)
            throw new InvalidInputException("Author's name cannot have a length of 2 characters!");
        if(!Character.isUpperCase(author.charAt(0)))
            throw new InvalidInputException("Author's name needs to start with an upper case!");

    }

    public static void checkYear(int year) {
        if(year > 2025)
            throw new InvalidInputException("Book could not have been released in the future!");
    }

    public static void checkStatus(String status) {
        if(!status.equalsIgnoreCase("Available") && !status.equalsIgnoreCase("Borrowed"))
            throw new InvalidInputException("Book status can be either 'Available' or 'Borrowed'");
    }

}
