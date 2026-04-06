import java.util.Scanner;

// TASK 4: Robustness and Error Diagnosis
// Wraps Scanner input in try-catch to handle InputMismatchException.
// Java 17/21 improvement: NullPointerException messages now indicate exactly which variable was null (JEP 358).
// In Java 8, the same exception would only print a generic message with no detail.

public class RobustSystem {

    private String[] employeeNames;
    private double[] averages;

    public RobustSystem(String[] employeeNames, double[] averages) {
        this.employeeNames = employeeNames;
        this.averages = averages;
    }

    public void evaluatePromotion(Scanner scanner) {

        System.out.print("\nEnter employee ID (1-" + employeeNames.length + "): ");

        try {
            // Java 11+ var inference — equivalent to: int id = scanner.nextInt()
            var id = scanner.nextInt();

            if (id < 1 || id > employeeNames.length) {
                System.out.println("Error: ID must be between 1 and " + employeeNames.length);
                return;
            }

            int index = id - 1;
            double average = averages[index];
            String name = employeeNames[index];

            // TASK 4: ternary operator to determine promotion eligibility
            String status = average >= 85.0 ? "Eligible for promotion" : "Not eligible for promotion";

            System.out.println("\nEmployee: " + name);
            System.out.printf("Average:  %.2f%n", average);
            System.out.println("Status:   " + status);

        } catch (java.util.InputMismatchException e) {
            System.out.println("Error: invalid input. Please enter a numeric ID.");
            System.out.println("Exception detail: " + e.getMessage());
            scanner.nextLine(); // Clear buffer
        }
    }
}