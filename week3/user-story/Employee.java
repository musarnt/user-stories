// Employee - Week 3
// Simplified model focused on collections management.
// Uses String ID as unique key for HashMap lookups.

public class Employee {

    private String id;
    private String name;
    private double salary;
    private double performanceScore;

    // Validation constants for primitive ranges
    private static final double MIN_SALARY = 0.0;
    private static final double MAX_SALARY = 50_000.0;
    private static final double MIN_SCORE = 0.0;
    private static final double MAX_SCORE = 100.0;

    public Employee(String id, String name, double salary, double performanceScore) {
        this.id = id;
        this.name = name;
        setSalary(salary);
        setPerformanceScore(performanceScore);
    }

    // --- Getters ---

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public double getPerformanceScore() {
        return performanceScore;
    }

    // --- Setters with validation ---
    // Salary and score are validated against logical ranges
    // to prevent invalid data from entering the collections.

    public void setSalary(double salary) {
        if (salary < MIN_SALARY || salary > MAX_SALARY) {
            throw new IllegalArgumentException(
                String.format("Salary $%,.2f is out of valid range ($%,.2f - $%,.2f)",
                    salary, MIN_SALARY, MAX_SALARY)
            );
        }
        this.salary = salary;
    }

    public void setPerformanceScore(double performanceScore) {
        if (performanceScore < MIN_SCORE || performanceScore > MAX_SCORE) {
            throw new IllegalArgumentException(
                String.format("Score %.2f is out of valid range (%.1f - %.1f)",
                    performanceScore, MIN_SCORE, MAX_SCORE)
            );
        }
        this.performanceScore = performanceScore;
    }

    @Override
    public String toString() {
        return String.format("ID: %s | Name: %-15s | Salary: $%,.2f | Score: %.1f",
                id, name, salary, performanceScore);
    }
}