package com.riwi;

import java.util.Scanner;

public class RobustSystem {

    public void start() {

        // Scores from day3 matrix — used to evaluate promotion eligibility
        double[] averages = {84.6, 92.06, 75.23};
        String[] employees = {"Alice", "Bob", "Carol"};

        try (var scanner = new Scanner(System.in)) {

            System.out.print("Enter employee ID (1-3): ");

            try {
                // Point 1: wrap Scanner input in try-catch
                // If user types a letter instead of a number, InputMismatchException is thrown
                var id = scanner.nextInt();

                if (id < 1 || id > 3) {
                    System.out.println("Error: ID must be between 1 and 3.");
                    return;
                }

                // Arrays are zero-indexed, so we subtract 1
                int index = id - 1;
                double average = averages[index];
                String name = employees[index];

                // Point 2: ternary operator to determine promotion eligibility
                // Promotion threshold: average >= 85.0
                String promotionStatus = average >= 85.0 ? "Eligible for promotion" : "Not eligible for promotion";

                System.out.println("\nEmployee: " + name);
                System.out.println("Average:  " + average);
                System.out.println("Status:   " + promotionStatus);

            } catch (java.util.InputMismatchException e) {
                // Point 1: inform the user and continue — system does not crash
                // Point 3: Java 17+ provides more detailed exception messages
                // In legacy Java (8/11), this would print a generic "null" message
                // Java 17+ identifies exactly which token caused the mismatch
                System.out.println("Error: invalid input. Please enter a numeric ID.");
                System.out.println("Exception detail: " + e.getMessage());
            }
        }
    }
}