package dev.vaggos;

import java.util.Scanner;

public class MyUtils {

    private static String[] getISBNs(Book[] books) {
        String[] isbnList = new String[books.length];
        int count = 0;
        for (Book book : books) {
            if (book != null) {
                isbnList[count++] = book.getISBN();
            }
        }
        return isbnList;
    }

    private static int[] getPublishedYears(Book[] books) {
        int[] publishedYears = new int[books.length];
        int count = 0;
        for (Book book : books) {
            if (book != null) {
                publishedYears[count++] = book.getYearPublished();
            }
        }
        return publishedYears;
    }

    private static boolean isBinEmpty(int[] array) {
        for (int i : array) {
            if (i != -1) {
                return false;
            }
        }
        return true;
    }

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
    public static Book[] valueSearch(Book[] books, float minPrice, float maxPrice) {
        Book[] booksInRange = new Book[books.length];
        int count = 0;
        for (Book book : books) {
            if (book != null && book.getPrice() >= minPrice && book.getPrice() <= maxPrice) {
                booksInRange[count++] = book;
            }
        }
        return booksInRange;
    }

    public static int menu(String[] options, String title) {
        String returnString = "     " + title;
        for (int i = 0; i < options.length; i++) {
            returnString += "\n     " + (i + 1) + ") " + options[i];
        }
        System.out.println(returnString);

        return getInt("\nΔώστε Επιλογή (1-" + options.length + "): ");
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

    public static void search(Book[] books, String key) {
        boolean done = false;
        int choice;
        while (!done) {
            choice = menu(new String[]{"Σειριακή Αναζήτηση", "Δυαδική Αναζήτηση (Προσοχή ο πίνακας πρέπει να είναι ταξινομημένος κατά το πεδίο αναζήτησης)", "Επιστροφή στην Επιλογή Πεδίου Αναζήτησης"}, "Επιλογή Μεθόδου Αναζήτησης");
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
            choice = menu(new String[]{"Σειριακή Αναζήτηση", "Δυαδική Αναζήτηση (Προσοχή ο πίνακας πρέπει να είναι ταξινομημένος κατά το πεδίο αναζήτησης)", "Επιστροφή στην Επιλογή Πεδίου Αναζήτησης"}, "Επιλογή Μεθόδου Αναζήτησης");
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

    public static void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    public static void selectionSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }

    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }

    public static void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }

    private static void merge(int[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        for (int i = 0; i < n1; i++) {
            leftArray[i] = array[left + i];
        }
        for (int i = 0; i < n2; i++) {
            rightArray[i] = array[mid + 1 + i];
        }

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }
}