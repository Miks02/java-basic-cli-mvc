package view;

import model.Book;
import utility.ScreenHelper;

import java.util.ArrayList;
import java.util.Scanner;


public class ShowAllView {
    public void showAllBooks(ArrayList<Book> books) {
        ScreenHelper.clearScreen();
        System.out.println("****All books****\n");


        for(Book b : books) {

            System.out.println("ID: " + b.getID());
            System.out.println("Title: " + b.getTitle());
            System.out.println("Author: " + b.getAuthor());
            System.out.println("Published in: " + b.getYear());
            System.out.println("Status: " + b.getStatus());
            System.out.println();
        }

    }
}
