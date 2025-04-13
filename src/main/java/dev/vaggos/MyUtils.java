package dev.vaggos;

import java.util.ArrayList;
import java.util.List;

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
    public static List<Integer> binSearch(int[] array, int key) {
        List<Integer> list = new ArrayList<>();
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == key) {
                int temp = mid;
                while (temp >= left && array[temp] == key) {
                    list.add(temp--);
                }
                temp = mid + 1;
                while (temp <= right && array[temp] == key) {
                    list.add(temp++);
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
    public static List<Integer> binSearch(String[] array, String key) {
        List<Integer> list = new ArrayList<>();
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cmp = array[mid].compareTo(key);

            if (cmp == 0) {
                int temp = mid;
                while (temp >= left && array[temp].equals(key)) {
                    list.add(temp--);
                }
                temp = mid + 1;
                while (temp <= right && array[temp].equals(key)) {
                    list.add(temp++);
                }
                break;
            } else if (cmp < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
//        while (left <= right) {
//            int mid = left + (right - left) / 2;
//            int cmp = array[mid].compareTo(key);
//            if (cmp == 0) {
//                list.add(mid);
//            } else if (cmp < 0) {
//                left = mid + 1;
//            } else {
//                right = mid - 1;
//            }
//        }
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
        StringBuilder sb = new StringBuilder();
        sb.append("     " + title);
        for (int i = 0; i < options.length; i++) {
            sb.append("\n     ").append(i + 1).append(") ").append(options[i]);
        }
        System.out.println(sb.toString());

        return getInt("\nΔώστε Επιλογή (1-" + actionNumber + "): ");
    }

    public static int getInt(String prompt) {
        System.out.print(prompt);
        return new java.util.Scanner(System.in).nextInt();
    }

    public static float getFloat(String prompt) {
        System.out.print(prompt);
        return new java.util.Scanner(System.in).nextFloat();
    }

    public static String getString(String prompt) {
        System.out.print(prompt);
        return new java.util.Scanner(System.in).nextLine();
    }

    public static boolean correctISBN(int yearPublished, String ISBN) {
        if (yearPublished <= 2007) {
            int total = 0;
            for (int i = 0; i < ISBN.length(); i++) {
                int temp = Integer.parseInt(ISBN.substring(i, i + 1));
                temp *= (10 - i);
                total += temp;
            }

            total %= 11;

            return total == 0;

        } else {
            int total = 0;
            for (int i = 0; i < ISBN.length(); i++) {
                int temp = Integer.parseInt(ISBN.substring(i, i + 1));
                if (i % 2 == 0) {
                    temp *= 1;
                } else {
                    temp *= 3;
                }
                total += temp;
            }

            total %= 10;
            return total == 0;
        }
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

    public static void search(Book[] books, String key) {
        boolean done = false;
        int choise = 0;
        while (!done) {
            choise = menu(new String[]{"Σειριακή Αναζήτηση", "Δυαδική Αναζήτηση (Προσοχή ο πίνακας πρέπει να είναι ταξινομημένος κατά το πεδίο αναζήτησης)", "Επιστροφή στην Επιλογή Πεδίου Αναζήτησης"}, "Επιλογή Μεθόδου Αναζήτησης", 3);
            switch (choise) {
                case 1: {
                    int index = seqSearch(getISBNs(books), key);
                    if (index != -1) {
                        books[index].toString();
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                }
                case 2: {
                    List<Integer> indexes = binSearch(getISBNs(books), key);
                    if (indexes.isEmpty()) {
                        System.out.println("Book not found.");
                    }
                    for (Integer index : indexes) {
                        books[index].toString();
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
        int choise = 0;
        while (!done) {
            choise = menu(new String[]{"Σειριακή Αναζήτηση", "Δυαδική Αναζήτηση (Προσοχή ο πίνακας πρέπει να είναι ταξινομημένος κατά το πεδίο αναζήτησης)", "Επιστροφή στην Επιλογή Πεδίου Αναζήτησης"}, "Επιλογή Μεθόδου Αναζήτησης", 3);
            switch (choise) {
                case 1: {
                    int index = seqSearch(getPublishedYears(books), key);
                    if (index != -1) {
                        books[index].toString();
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                }
                case 2: {
                    List<Integer> indexes = binSearch(getPublishedYears(books), key);
                    if (indexes.isEmpty()) {
                        System.out.println("Book not found.");
                    }
                    for (Integer index : indexes) {
                        books[index].toString();
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