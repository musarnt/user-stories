/*  
TASK 1: Migration to ArrayList and HashMap (Legacy Java 8/11)
    Replaces the fixed arrays from Week 2 with dynamic collections.
    ArrayList allows unlimited employee storage with automatic resizing.
    HashMap provides O(1) lookups by ID — far more efficient than iterating an array. 
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TalentManager {

    private ArrayList<Employee> employeeList;
    private HashMap<String, Employee> employeeMap;

    public TalentManager() {
        employeeList = new ArrayList<>();
        employeeMap = new HashMap<>();
    }

    // --- Getter for external access (used by SequencedDemo, FilterReport) ---

    public ArrayList<Employee> getEmployeeList() {
        return employeeList;
    }

    // --- Add employee ---
    // Validates duplicate ID before inserting into both collections.
    // Both structures must stay in sync: ArrayList for ordered access,
    // HashMap for instant lookup by key.

    public void addEmployee(Scanner scanner) {
        System.out.print("\nEnter employee ID: ");
        var id = scanner.nextLine().trim();

        if (id.isEmpty()) {
            System.out.println("Error: ID cannot be empty.");
            return;
        }

        if (employeeMap.containsKey(id)) {
            System.out.println("Error: an employee with ID '" + id + "' already exists.");
            return;
        }

        System.out.print("Enter name: ");
        var name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Error: name cannot be empty.");
            return;
        }

        double salary;
        try {
            System.out.print("Enter salary (0 - 50,000): ");
            salary = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Error: salary must be a numeric value.");
            return;
        }

        double score;
        try {
            System.out.print("Enter performance score (0 - 100): ");
            score = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Error: score must be a numeric value.");
            return;
        }

        try {
            var employee = new Employee(id, name, salary, score);
            employeeList.add(employee);
            employeeMap.put(id, employee);
            System.out.println("Employee '" + name + "' added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // --- List all employees ---
    // Iterates the ArrayList to maintain insertion order.

    public void listEmployees() {
        if (employeeList.isEmpty()) {
            System.out.println("\nNo employees registered.");
            return;
        }

        System.out.println("\n=== Employee List ===\n");
        for (var employee : employeeList) {
            System.out.println(employee);
        }
        System.out.println("\nTotal: " + employeeList.size() + " employee(s)");
    }

    // --- Search by ID ---
    // HashMap.get() runs in O(1) — no need to loop through the entire list.
    // In Week 2, finding an employee required iterating a fixed array.

    public void searchById(Scanner scanner) {
        System.out.print("\nEnter employee ID to search: ");
        var id = scanner.nextLine().trim();

        var employee = employeeMap.get(id);

        if (employee == null) {
            System.out.println("No employee found with ID '" + id + "'.");
        } else {
            System.out.println("\n=== Employee Found ===\n");
            System.out.println(employee);
        }
    }

    // --- Remove employee ---
    // Must remove from both ArrayList and HashMap to keep them in sync.
    // HashMap.remove() is O(1), but ArrayList.remove() is O(n)
    // because it shifts elements after the removed index.

    public void removeEmployee(Scanner scanner) {
        System.out.print("\nEnter employee ID to remove: ");
        var id = scanner.nextLine().trim();

        var employee = employeeMap.remove(id);

        if (employee == null) {
            System.out.println("No employee found with ID '" + id + "'.");
        } else {
            employeeList.remove(employee);
            System.out.println("Employee '" + employee.getName() + "' removed successfully.");
        }
    }
}