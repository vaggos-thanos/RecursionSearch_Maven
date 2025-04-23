package dev.vaggos;

public class Book {
    private String title;
    private String author;
    private String ISBN;
    private String publisher;
    private int numberOfPages;
    private int yearPublished;
    private double price;

    public Book(String title, String author, String publisher) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public boolean setNumberOfPages(int numberOfPages) {
        if (numberOfPages > 0) {
            this.numberOfPages = numberOfPages;
        } else {
            return false;
        }
        return true;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public boolean setYearPublished(int yearPublished) {
        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        if (yearPublished > 0 && yearPublished <= currentYear) {
            this.yearPublished = yearPublished;
        } else {
            return false;
        }
        return true;
    }

    public double getPrice() {
        return price;
    }

    public boolean setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        } else {
            return false;
        }
        return true;
    }

    public String toString() {
        String temp = "\nTitle: " + title +
                "\nAuthor: " + author +
                "\nISBN: " + ISBN +
                "\nPublisher: " + publisher +
                "\nNumber of Pages: " + numberOfPages +
                "\nYear Published: " + yearPublished +
                "\nPrice: " + price +
                "\n";
        return temp;
    }
}
