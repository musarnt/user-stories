package com.riwi.talent.view;

import com.riwi.talent.controller.EmployeeController;
import com.riwi.talent.model.Employee;

import java.util.List;
import java.util.Scanner;

// ConsoleView: the only layer allowed to interact with the user directly.
// All Scanner usage is confined here — the controller never reads from System.in.
// This means the UI can be replaced (console to web API) without touching
// the controller or model layers.
public class ConsoleView {

    private final EmployeeController controller;
    private final Scanner scanner;

    public ConsoleView() {
        this.controller = new EmployeeController();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;

        while (running) {
            printMenu();
            int option = readInt("Select an option: ");

            switch (option) {
                case 1 -> insertEmployee();
                case 2 -> listEmployees();
                case 3 -> updateEmployee();
                case 4 -> deleteEmployee();
                case 5 -> System.out.println(controller.generateReport());
                case 0 -> {
                    System.out.println("Exiting the system. Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    private void printMenu() {
        System.out.println("""
                
                ================================================
                           CORPORATE TALENT HUB
                ================================================
                  1. Add employee
                  2. List employees
                  3. Update employee
                  4. Delete employee
                  5. Generate final report
                  0. Exit
                ================================================
                """);
    }

    private void insertEmployee() {
        System.out.println("--- Add New Employee ---");
        String name   = readString("Name: ");
        String role   = readString("Role: ");
        double salary = readDouble("Salary: ");
        controller.addEmployee(name, role, salary);
    }

    private void listEmployees() {
        List<Employee> employees = controller.getAllEmployees();

        if (employees.isEmpty()) {
            System.out.println("No employees registered.");
            return;
        }

        System.out.println("--- Employee List ---");
        for (Employee e : employees) {
            System.out.printf("ID: %d | Name: %s | Role: %s | Salary: $%.2f%n",
                    e.getId(), e.getName(), e.getRole(), e.getSalary());
        }
    }

    private void updateEmployee() {
        System.out.println("--- Update Employee ---");
        int id        = readInt("Employee ID to update: ");
        String name   = readString("New name: ");
        String role   = readString("New role: ");
        double salary = readDouble("New salary: ");
        controller.updateEmployee(id, name, role, salary);
    }

    private void deleteEmployee() {
        System.out.println("--- Delete Employee ---");
        int id = readInt("Employee ID to delete: ");
        controller.deleteEmployee(id);
    }

    // Helper methods — all Scanner interaction goes through here
    private String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private int readInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // consume the newline left by nextInt()
        return value;
    }

    private double readDouble(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        double value = scanner.nextDouble();
        scanner.nextLine(); // consume the newline left by nextDouble()
        return value;
    }
}