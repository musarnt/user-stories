// TASK 3: Performance Matrix and Casting
// Stores quarterly scores for each registered employee and calculates averages.
// Explicit casting from double to int truncates decimals — no rounding occurs.

public class PerformanceMatrix {

    // Matrix: rows = employees, columns = quarters
    private double[][] scores;
    private String[] employeeNames;

    public PerformanceMatrix(String[] employeeNames, double[][] scores) {
        this.employeeNames = employeeNames;
        this.scores = scores;
    }

    public void generateReport() {

        double teamTotal = 0;

        System.out.println("\n=== Performance Report ===\n");

        // Nested loops to traverse the matrix and calculate each employee's average
        for (int i = 0; i < scores.length; i++) {

            double employeeTotal = 0;

            for (int j = 0; j < scores[i].length; j++) {
                employeeTotal += scores[i][j];
            }

            double average = employeeTotal / scores[i].length;
            teamTotal += average;

            // Explicit casting: double to int — decimals are truncated, not rounded
            // Example: 84.6 becomes 84, precision is lost in the simplified score
            int simplifiedScore = (int) average;

            System.out.println("Employee:         " + employeeNames[i]);
            System.out.printf("Average:          %.2f%n", average);
            System.out.printf("Simplified score: %d%n", simplifiedScore);
            System.out.println();
        }

        double teamAverage = teamTotal / scores.length;
        int simplifiedTeamScore = (int) teamAverage;

        System.out.println("=== Team Summary ===");
        System.out.printf("Team average:           %.2f%n", teamAverage);
        System.out.printf("Simplified team score:  %.0f%n", (double) simplifiedTeamScore);
    }
}