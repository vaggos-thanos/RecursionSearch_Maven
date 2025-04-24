package dev.vaggos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyUtils {

    // Sequential search for integers
    public static int seqSearch(int[] array, int key) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == key) {
                return i;
            }
        }
        return -1;
    }

    // Sequential search for strings
    public static int seqSearch(String[] array, String key) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(key)) {
                return i;
            }
        }
        return -1;
    }

    // Binary search for integers
    public static int[] binSearch(int[] array, int key) {
        int[] list = new int[array.length];
        for (int index = 0; index < array.length; index++) {
            list[index] = -1;
        }
        int left = 0;
        int right = array.length - 1;
        int count = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == key) {
                int temp = mid;
                while (temp >= left && array[temp] == key) {
                    list[count] = temp--;
                    count++;

                }
                temp = mid + 1;
                while (temp <= right && array[temp] == key) {
                    list[count] = temp++;
                    count++;
                }
                break;
            } else if (array[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return list;
    }

    // Binary search for strings
    public static int[] binSearch(String[] array, String key) {
        int[] list = new int[array.length];
        for (int index = 0; index < array.length; index++) {
            list[index] = -1;
        }
        int count = 0;
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cmp = array[mid].compareTo(key);

            if (cmp == 0) {
                int temp = mid;
                while (temp >= left && array[temp].equals(key)) {
                    list[count] = temp--;
                    count++;
                }
                temp = mid + 1;
                while (temp <= right && array[temp].equals(key)) {
                    list[count] = temp++;
                    count++;
                }
                break;
            } else if (cmp < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return list;
    }

    // Search for books within a price range
    public static List<Book> valueSearch(Book[] books, float minPrice, float maxPrice) {
        List<Book> booksInRange = new ArrayList<>();
        for (Book book : books) {
            if (book != null && book.getPrice() >= minPrice && book.getPrice() <= maxPrice) {
                booksInRange.add(book);
            }
        }
        return booksInRange;
    }

    public static int menu(String[] options, String title, int actionNumber) {
        String returnString = "     " + title;
        for (int i = 0; i < options.length; i++) {
            returnString += "\n     " + (i + 1) + ") " + options[i];
        }
        System.out.println(returnString);

        return getInt("\nΔώστε Επιλογή (1-" + actionNumber + "): ");
    }

    public static int getInt(String prompt) {
        System.out.print(prompt);
        return new Scanner(System.in).nextInt();
    }

    public static float getFloat(String prompt) {
        System.out.print(prompt);
        return new Scanner(System.in).nextFloat();
    }

    public static String getString(String prompt) {
        System.out.print(prompt);
        return new Scanner(System.in).nextLine();
    }

    public static boolean correctISBN(int yearPublished, String ISBN) {
        int total = 0;
        if(yearPublished <= 2007) {
            for (int i = 0; i < ISBN.length(); i++) {
                int temp = Integer.parseInt(ISBN.substring(i, i + 1));
                temp *= (10 - i);
                total += temp;
            }

            total %= 11;

        } else {
            for (int i = 0; i < ISBN.length(); i++) {
                int temp = Integer.parseInt(ISBN.substring(i, i + 1));
                if (i % 2 != 0) {
                    temp *= 3;
                }
                total += temp;
            }

            total %= 10;
        }
        return total == 0;
    }

    private static String[] getISBNs(Book[] books) {
        List<String> isbnList = new ArrayList<>();
        for (Book book : books) {
            if (book != null) {
                isbnList.add(book.getISBN());
            }
        }
        return isbnList.toArray(new String[0]);
    }

    private static int[] getPublishedYears(Book[] books) {
        List<Integer> publishedYears = new ArrayList<>();
        for (Book book : books) {
            if (book != null) {
                publishedYears.add(book.getYearPublished());
            }
        }
        return publishedYears.stream().mapToInt(i -> i).toArray();
    }

    private static boolean isBinEmpty(int[] array) {
        for (int i : array) {
            if (i != -1) {
                return false;
            }
        }
        return true;
    }

    public static void search(Book[] books, String key) {
        boolean done = false;
        int choice;
        while (!done) {
            choice = menu(new String[]{"Σειριακή Αναζήτηση", "Δυαδική Αναζήτηση (Προσοχή ο πίνακας πρέπει να είναι ταξινομημένος κατά το πεδίο αναζήτησης)", "Επιστροφή στην Επιλογή Πεδίου Αναζήτησης"}, "Επιλογή Μεθόδου Αναζήτησης", 3);
            switch (choice) {
                case 1: {
                    int index = seqSearch(getISBNs(books), key);
                    if (index != -1) {
                        System.out.println(books[index]);
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                }
                case 2: {
                    int[] indexes = binSearch(getISBNs(books), key);
                    if (isBinEmpty(indexes)) {
                        System.out.println("Book not found.");
                    }
                    for (Integer index : indexes) {
                        if (index == -1) {
                            break;
                        }
                        System.out.println(books[index]);
                    }
                    break;
                }
                case 3: {
                    System.out.println("Returning to the main menu.");
                    done = true;
                    break;
                }
                default: {
                    System.out.println("Invalid choice. Please try again.");
                    break;
                }
            }
        }
    }

    public static void search(Book[] books, int key) {
        boolean done = false;
        int choice;
        while (!done) {
            choice = menu(new String[]{"Σειριακή Αναζήτηση", "Δυαδική Αναζήτηση (Προσοχή ο πίνακας πρέπει να είναι ταξινομημένος κατά το πεδίο αναζήτησης)", "Επιστροφή στην Επιλογή Πεδίου Αναζήτησης"}, "Επιλογή Μεθόδου Αναζήτησης", 3);
            switch (choice) {
                case 1: {
                    int index = seqSearch(getPublishedYears(books), key);
                    if (index != -1) {
                        System.out.println(books[index]);
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                }
                case 2: {
                    int[] indexes = binSearch(getPublishedYears(books), key);
                    if (isBinEmpty(indexes)) {
                        System.out.println("Book not found.");
                    }
                    for (Integer index : indexes) {
                        if (index == -1) {
                            break;
                        }
                        System.out.println(books[index]);
                    }
                    break;
                }
                case 3: {
                    System.out.println("Returning to the main menu.");
                    done = true;
                    break;
                }
                default: {
                    System.out.println("Invalid choice. Please try again.");
                    break;
                }
            }
        }
    }
}