package model;

public class Book {
    private int id;
    private String title;
    private String author;
    private int yearOfRelease;
    private String status;

    public Book() {

    }

    public Book( String title, String author, int yearOfRelease, String status) {
        this.title = title;
        this.author = author;
        this.yearOfRelease = yearOfRelease;
        this.status = status;
    }

    public Book(int id, String title, String author, int yearOfRelease, String status) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.yearOfRelease = yearOfRelease;
        this.status = status;
    }

    public int getID() {return id;}
    public String getTitle() {return title;}
    public String getAuthor() {return author;}
    public int getYear() {return yearOfRelease;}
    public String getStatus() {return status;}

    public void setID(int id) {this.id = id;}
    public void setTitle(String title) {this.title = title;}
    public void setAuthor(String author) {this.author = author;}
    public void setYear(int year) {this.yearOfRelease = year;}
    public void setStatus(String status) {this.status = status;}

}
