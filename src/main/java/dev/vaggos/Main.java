package dev.vaggos;

public class Main {
    public static void main(String[] args) {
        Book[] books = new Book[10];
        int bookCounter = 0;
        boolean done = false;

        books[0] = new Book("Book1", "Author1", "Publisher1");
        books[1] = new Book("Book2", "Author2", "Publisher2");
        books[2] = new Book("Book3", "Author3", "Publisher3");

        books[0].setNumberOfPages(100);
        books[0].setYearPublished(2000);
        books[0].setPrice(10.0f);
        books[0].setISBN("0696340666");

        books[1].setNumberOfPages(200);
        books[1].setYearPublished(2012);
        books[1].setPrice(20.0f);
        books[1].setISBN("9784191774612");

        books[2].setNumberOfPages(300);
        books[2].setYearPublished(2012);
        books[2].setPrice(30.0f);
        books[2].setISBN("9788928072958");


        while (!done) {

            int choice = MyUtils.menu(new String[]{"Εισαγωγή στοιχείων βιβλίου", "Αναζήτηση βιβλίου", "Εμφάνιση στοιχείων βιβλίων με τιμή μεταξύ κάποιων ορίων", "Ταξινόμηση βιβλίων", "Εμφάνιση στοιχείων όλων των βιβλίων", "Τέλος"}, "Λίστα Επιλογών");

            switch (choice) {
                case 1 : {
                    if (books[books.length - 1] != null) {
                        System.out.println("No more space for new books.");
                    } else {
                        String title = MyUtils.getString("Enter book title: ");
                        String author = MyUtils.getString("Enter book author: ");
                        String publisher = MyUtils.getString("Enter book publisher: ");

                        Book book = new Book(title, author, publisher);

                        int numberOfPages = MyUtils.getInt("Enter number of pages: ");
                        if (!book.setNumberOfPages(numberOfPages)) {
                            while (numberOfPages <= 0) {
                                System.out.println("Invalid number of pages. Please enter a positive number.");
                                numberOfPages = MyUtils.getInt("Enter number of pages: ");
                            }
                        }

                        int yearPublished = MyUtils.getInt("Enter year published: ");
                        if (!book.setYearPublished(yearPublished)) {
                            while (yearPublished <= 0) {
                                System.out.println("Invalid year published. Please enter a positive number.");
                                yearPublished = MyUtils.getInt("Enter year published: ");
                            }
                        }

                        float price = MyUtils.getFloat("Enter book price: ");
                        if (!book.setPrice(price)) {
                            while (price <= 0) {
                                System.out.println("Invalid price. Please enter a positive number.");
                                price = MyUtils.getFloat("Enter book price: ");
                            }
                        }

                        String ISBN = MyUtils.getString("Enter book ISBN: ");

                        if (!MyUtils.correctISBN(yearPublished, ISBN)) {
                            while (!MyUtils.correctISBN(yearPublished, ISBN)) {
                                System.out.println("Invalid ISBN for the given year published. Please enter a valid ISBN.");
                                ISBN = MyUtils.getString("Enter book ISBN: ");
                            }
                        }
                        book.setISBN(ISBN);

                        books[bookCounter] = book;
                        System.out.println(books[bookCounter]);
                        bookCounter++;
                        System.out.println("Book added successfully.");
                        System.out.println("Book details: ");

                    }
                    break;
                }
                case 2 : {
                    if (MyUtils.isBooksEmpty(books)){
                        System.out.println("No books available for search.");
                        break;
                    }
                    boolean subMenuDone = false;
                    while (!subMenuDone) {
                        int subMenuChoice = MyUtils.menu(new String[]{"ISBN", "Έτος κυκλοφορίας", "Επιστροφή στην Αρχική Λίστα Επιλογών"}, "Επιλογή Πεδίου Αναζήτησης");
                        switch (subMenuChoice) {
                            case 1 : {
                                MyUtils.search(books, MyUtils.getString("Enter book ISBN: "));
                                break;
                            }
                            case 2 : {
                                MyUtils.search(books, MyUtils.getInt("Enter year published: "));
                                break;
                            }
                            case 3: {
                                subMenuDone = true;
                                break;
                            }
                            default : {
                                System.out.println("Invalid choice. Please try again.");
                                break;
                            }
                        }
                    }
                    break;
                }
                case 3 : {
                    if (MyUtils.isBooksEmpty(books)){
                        System.out.println("No books available for search.");
                        break;
                    }
                    try {
                        float minPrice = MyUtils.getFloat("Enter minimum price: ");
                        float maxPrice = MyUtils.getFloat("Enter maximum price: ");
                        Book[] booksInRange = MyUtils.valueSearch(books, minPrice, maxPrice);
                        int count = 0;
                        for (Book book : booksInRange) {
                            if (book != null) {
                                System.out.println(book);
                                count++;
                            }
                        }
                        if (count == 0) {
                            System.out.println("No books found within the given price range.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter numeric values for the price range.");
                    }
                    break;
                }
                case 4: {
                    if (MyUtils.isBooksEmpty(books)) {
                        System.out.println("No books available for sorting.");
                        break;
                    }

                    boolean sortingDone = false;
                    while (!sortingDone) {
                        int fieldChoice = MyUtils.menu(new String[]{
                            "Όνομα Συγγραφέα",
                            "ISBN",
                            "Έτος κυκλοφορίας",
                            "Τιμή βιβλίου",
                            "Επιστροφή στην Αρχική Λίστα Επιλογών"
                        }, "Επιλογή Πεδίου Ταξινόμησης");

                        if (fieldChoice == 5) {
                            break; // Επιστροφή στην αρχική λίστα επιλογών
                        }

                        int methodChoice = MyUtils.menu(new String[]{
                            "Bubblesort",
                            "Ταξινόμηση με Εισαγωγή",
                            "Ταξινόμηση με Επιλογή",
                            "Γρήγορη Ταξινόμηση",
                            "Ταξινόμηση με Συγχώνευση",
                            "Επιστροφή στην Επιλογή Πεδίου Ταξινόμησης"
                        }, "Επιλογή Μεθόδου Ταξινόμησης");

                        if (methodChoice == 6) {
                            continue; // Επιστροφή στην επιλογή πεδίου ταξινόμησης
                        }

                        switch (fieldChoice) {
                            case 1: // Όνομα Συγγραφέα
                                MyUtils.sortBooksByField(books, "author", methodChoice);
                                break;
                            case 2: // ISBN
                                MyUtils.sortBooksByField(books, "isbn", methodChoice);
                                break;
                            case 3: // Έτος κυκλοφορίας
                                MyUtils.sortBooksByField(books, "year", methodChoice);
                                break;
                            case 4: // Τιμή βιβλίου
                                MyUtils.sortBooksByField(books, "price", methodChoice);
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                                break;
                        }

                        System.out.println("Books sorted successfully.");
                        sortingDone = true; // Επιστροφή στην αρχική λίστα επιλογών μετά την ταξινόμηση
                    }
                    break;
                }
                case 5 : {
                    if (MyUtils.isBooksEmpty(books)){
                        System.out.println("No books available for display.");
                        break;
                    }
                    int count = 0;
                    for (Book book : books) {
                        if (book != null) {
                            System.out.println("Book " + (count + 1) + ":");
                            System.out.println(book);
                            System.out.println();
                            count++;
                        }
                    }
                    break;
                }
                case 6 : {
                    done = true;
                    break;
                }
                default: {
                    System.out.println("Invalid choice. Please try again.");
                    break;

                }
            }
        }
        System.out.println("Exiting the program.");

    }


}