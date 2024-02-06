package org.merge;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final MergeSort mergeSort = new MergeSort();
    private static final Random rand = new Random();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nMerge Sort\n");
            System.out.println("Choose an option:");
            System.out.println("1. Create an Array from Input");
            System.out.println("2. Generate Array of Input Size with Random Integers Ranging 0-Input Size");
            System.out.println("Type 'quit' to exit.");
            System.out.print("\nOption: ");

            String inputOption = scanner.nextLine().trim();

            if ("quit".equalsIgnoreCase(inputOption)) {
                break;
            }

            switch (inputOption) {
                case "1":
                    handleInputArrayOption();
                    break;
                case "2":
                    handleRandomArrayOption();
                    break;
                default:
                    System.out.println("Invalid option. Please enter 1, 2, or 'quit'.");
            }
        }

        scanner.close();
    }

    private static void handleInputArrayOption() {
        ArrayList<Integer> list = new ArrayList<>();
        System.out.println("Enter integers (type 'end' to finish):");

        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                list.add(scanner.nextInt());
            } else {
                String input = scanner.next();
                if ("end".equalsIgnoreCase(input)) {
                    scanner.nextLine();
                    break;
                } else {
                    System.out.println("Please enter an integer or 'end' to finish.");
                }
            }
        }

        int[] numbers = list.stream().mapToInt(i -> i).toArray();
        processArray(numbers);
    }

    private static void handleRandomArrayOption() {
        int size = getValidInteger();
        int[] array = new int[size];
        System.out.println("Initializing array of size " + size + "...");
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(size + 1);
        }
        System.out.println("Array initialized.");
        processArray(array);
    }

    private static int getValidInteger() {
        while (true) {
            System.out.print("Enter size of array: ");
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private static void processArray(int[] array) {
        if (askForTiming()) {
            System.out.println("\nStarting merge...");
            long startTime = System.currentTimeMillis();
            mergeSort.sort(array);
            long endTime = System.currentTimeMillis();
            System.out.println("Merge complete.");
            printElapsedTime(startTime, endTime);
        } else {
            System.out.println("\nStarting merge...");
            mergeSort.sort(array);
            System.out.println("Merge complete.");
        }

        if (askToPrintArray()) {
            printArray(array);
        }
    }

    private static boolean askForTiming() {
        System.out.print("Would you like to time the sorting process? (y/n): ");
        return "y".equalsIgnoreCase(scanner.nextLine().trim());
    }

    private static boolean askToPrintArray() {
        System.out.print("Would you like to print the sorted array? (y/n): ");
        return "y".equalsIgnoreCase(scanner.nextLine().trim());
    }

    private static void printArray(int[] array) {
        System.out.println("Sorted array (first 500 elements or less if array smaller than 500):");
        int limit = Math.min(array.length, 500);
        for (int i = 0; i < limit; i++) {
            System.out.print(array[i] + (i < limit - 1 ? ", " : ""));
        }
        System.out.println();
    }

    private static void printElapsedTime(long startTime, long endTime) {
        long elapsedTime = endTime - startTime;
        long elapsedSeconds = elapsedTime / 1000;
        long secondsDisplay = elapsedSeconds % 60;
        long elapsedMinutes = elapsedSeconds / 60;
        long elapsedMilliseconds = elapsedTime % 1000;

        System.out.println("Execution time: " + elapsedMinutes + " minutes, " + secondsDisplay + " seconds, " + elapsedMilliseconds + " milliseconds.");
    }

}
