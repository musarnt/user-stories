package com.riwi;

import java.util.Scanner;

public class DynamicMenu {

    public void start() {
        Scanner scanner = new Scanner(System.in);
        var option = 0;

        do {
            // Display menu options
            System.out.println("\n=== Corporate Talent Hub ===");
            System.out.println("1. Register employee");
            System.out.println("2. Query salary");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");

            // var for local variables (Point 2)
            var input = scanner.nextInt();
            option = input;

            switch (option) {
                case 1 -> {
                    System.out.print("Enter employee ID (1-9999): ");
                    var id = scanner.nextInt();

                    System.out.print("Enter age (18-65): ");
                    var age = scanner.nextInt();

                    System.out.print("Enter salary: ");
                    var salary = scanner.nextDouble();

                    System.out.print("Is active? (true/false): ");
                    var active = scanner.nextBoolean();

                    // Point 3: validate ranges
                    if (id < 1 || id > 9999) {
                        System.out.println("Error: ID must be between 1 and 9999.");
                    } else if (age < 18 || age > 65) {
                        System.out.println("Error: Age must be between 18 and 65.");
                    } else if (salary < 0) {
                        System.out.println("Error: Salary cannot be negative.");
                    } else {
                        System.out.println("Employee registered successfully.");
                        System.out.println("Active: " + active);
                    }
                }
                case 2 -> {
                    System.out.print("Enter salary to categorize: ");
                    var salary = scanner.nextDouble();
                    System.out.println("Category: " + getSalaryCategory(salary));
                }
                case 0 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid option.");
            }

        } while (option != 0);

        scanner.close();
    }

    // Returns salary category based on range
    private String getSalaryCategory(double salary) {
        int level = salary <= 2000 ? 1 :
                    salary <= 4000 ? 2 :
                    salary <= 7000 ? 3 : 4;

        return switch (level) {
            case 1 -> "Junior";
            case 2 -> "Mid";
            case 3 -> "Senior";
            case 4 -> "Expert";
            default -> "Unknown";
        };
    }
}