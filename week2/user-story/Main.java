// Corporate Talent Hub - Control Flow and Version Evolution
// User Story: evolve the system to allow dynamic interaction and processing of multiple employees.
// Integrates: Task 1 (Switch), Task 2 (do-while + var), Task 3 (Matrix + Casting), Task 4 (try-catch + ternary)

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Employee data shared across all tasks
        String[] employeeNames = {"Alice", "Bob", "Carol"};
        double[][] scores = {
            {85.5, 90.0, 78.3},  // Alice
            {92.1, 88.7, 95.4},  // Bob
            {70.0, 75.5, 80.2}   // Carol
        };

        // Calculate averages for RobustSystem
        double[] averages = new double[employeeNames.length];
        for (int i = 0; i < scores.length; i++) {
            double total = 0;
            for (int j = 0; j < scores[i].length; j++) {
                total += scores[i][j];
            }
            averages[i] = total / scores[i].length;
        }

        MainMenu menu = new MainMenu();
        PerformanceMatrix matrix = new PerformanceMatrix(employeeNames, scores);
        RobustSystem robustSystem = new RobustSystem(employeeNames, averages);

        Scanner scanner = new Scanner(System.in);
        var option = 0;

        // TASK 2: do-while keeps the system active until user exits
        do {
            // TASK 1: legacy switch for main menu
            System.out.println("\n=== Corporate Talent Hub ===");
            System.out.println("1. Show menu options");
            System.out.println("2. Get salary category");
            System.out.println("3. Generate performance report");
            System.out.println("4. Evaluate promotion");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");

            try {
                // TASK 2: var inference — Java 11+
                var input = scanner.nextInt();
                option = input;

                // TASK 1: main menu uses legacy switch (Java 8) as required by the spec
                // Fall-through risk exists if break is omitted — contrast with modern switch expression in MainMenu.java
                switch (option) {
                    case 1:
                        menu.showMainMenu(option);
                        break;
                    case 2:
                        System.out.print("Enter salary: ");
                        var salary = scanner.nextDouble();
                        System.out.println("Category: " + menu.getSalaryCategory(salary));
                        break;
                    case 3:
                        matrix.generateReport();
                        break;
                    case 4:
                        robustSystem.evaluatePromotion(scanner);
                        break;
                    case 0:
                        System.out.println("Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option.");
                        break;
                }

            } catch (java.util.InputMismatchException e) {
                System.out.println("Error: invalid input. Please enter a number.");
                scanner.nextLine(); // Clear buffer
                option = -1; // Reset option to keep loop running
            }

        } while (option != 0);

        scanner.close();
    }
}