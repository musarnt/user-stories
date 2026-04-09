import java.util.Scanner;

// TASK 4: Robustness and Error Diagnosis
// Wraps Scanner input in try-catch to handle InputMismatchException.
// Java 17/21 improvement: NullPointerException messages now indicate exactly which variable was null (JEP 358).
// In Java 8, the same exception would only print a generic message with no detail.

public class RobustSystem {

    // Validation constants for primitive ranges
    private static final double MIN_SALARY = 0.0;
    private static final double MAX_SALARY = 50000.0;
    private static final double MIN_SCORE = 0.0;
    private static final double MAX_SCORE = 100.0;

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

            // Validate score range — scores must be between 0.0 and 100.0
            if (average < MIN_SCORE || average > MAX_SCORE) {
                System.out.printf("Error: average %.2f is out of valid range (%.1f - %.1f)%n",
                        average, MIN_SCORE, MAX_SCORE);
                return;
            }

            // Validate salary input
            System.out.print("Enter salary to validate: ");
            var salary = scanner.nextDouble();

            if (salary < MIN_SALARY || salary > MAX_SALARY) {
                System.out.printf("Error: salary $%,.2f is out of valid range ($%,.2f - $%,.2f)%n",
                        salary, MIN_SALARY, MAX_SALARY);
                return;
            }

            // TASK 4: ternary operator to determine promotion eligibility
            String status = average >= 85.0 ? "Eligible for promotion" : "Not eligible for promotion";

            System.out.println("\nEmployee: " + name);
            System.out.printf("Average:  %.2f%n", average);
            System.out.printf("Salary:   $%,.2f%n", salary);
            System.out.println("Status:   " + status);

        } catch (java.util.InputMismatchException e) {
            System.out.println("Error: invalid input. Please enter a numeric value.");
            System.out.println("Exception detail: " + e.getMessage());
            scanner.nextLine(); // Clear buffer
        }
    }
}