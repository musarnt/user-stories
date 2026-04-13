/* 
TASK 4: Advanced Filtering and Type Inference with var
Combines removeIf() for collection filtering with var (Java 11+) for type inference.

    var comparison — Java 8 vs Java 11+:
        Java 8 explicit:   ArrayList<Employee> list = new ArrayList<>();
                            double total = 0.0;
                            Employee emp = list.get(0);
        Java 11+ inferred: var list = new ArrayList<Employee>();
                            var total = 0.0;
                            var emp = list.get(0);

var reduces boilerplate without losing type safety — the compiler still
infers and enforces the exact type at compile time. It does NOT make
Java dynamically typed. The variable's type is locked at declaration.
Limitation: var cannot be used for fields, method parameters, or return types.
*/
import java.util.ArrayList;

public class FilterReport {

    private static final double MIN_PASSING_SCORE = 60.0;

    /*  removeIf() — introduced in Java 8 as part of the Collection interface.
        Accepts a Predicate (lambda) and removes all elements that match it.
        More concise than the legacy approach of iterating with an Iterator
        and calling iterator.remove() manually to avoid ConcurrentModificationException.
    */
    public void filterAndReport(ArrayList<Employee> employeeList) {

        if (employeeList.isEmpty()) {
            System.out.println("\nNo employees to filter.");
            return;
        }

        System.out.println("\n=== Filter & Report ===\n");
        System.out.println("Minimum passing score: " + MIN_PASSING_SCORE);
        System.out.println("Employees before filter: " + employeeList.size());

        // removeIf removes employees with score below the threshold.
        // The lambda reads: "remove if employee's score is less than MIN_PASSING_SCORE"
        var removed = employeeList.removeIf(
                employee -> employee.getPerformanceScore() < MIN_PASSING_SCORE
        );

        if (removed) {
            System.out.println("Employees after filter:  " + employeeList.size());
        } else {
            System.out.println("No employees were removed — all meet the minimum score.");
        }

        // --- Final report: total employees and average salary ---

        if (employeeList.isEmpty()) {
            System.out.println("\nAll employees were filtered out. No report to generate.");
            return;
        }

        // var infers double — compiler knows the type at declaration
        var totalSalary = 0.0;

        for (var employee : employeeList) {
            totalSalary += employee.getSalary();
        }

        var averageSalary = totalSalary / employeeList.size();

        System.out.println("\n=== Final Report ===\n");
        for (var employee : employeeList) {
            System.out.println(employee);
        }
        System.out.printf("\nTotal employees:  %d%n", employeeList.size());
        System.out.printf("Average salary:   $%,.2f%n", averageSalary);
    }
}