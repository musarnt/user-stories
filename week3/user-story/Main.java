/*  
Corporate Talent Hub - Week 3: Dynamic Talent Management
    User Story: evolve static storage into a dynamic architecture
    using Java Collections Framework (ArrayList, HashMap, List.of, Sequenced Collections).
    Integrates: Task 1 (ArrayList/HashMap), Task 2 (Factory Methods),
    Task 3 (Sequenced Collections), Task 4 (removeIf + var)
*/

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        var manager = new TalentManager();
        var config = new ConfigData();
        var sequenced = new SequencedDemo();
        var filter = new FilterReport();

        Scanner scanner = new Scanner(System.in);
        var option = "";

        // do-while keeps the system active until user exits
        do {
            System.out.println("\n=== Corporate Talent Hub ===");
            System.out.println("1. Add employee");
            System.out.println("2. List employees");
            System.out.println("3. Search by ID");
            System.out.println("4. Remove employee");
            System.out.println("5. Show config data (techs & locations)");
            System.out.println("6. Sequenced collections demo");
            System.out.println("7. Filter & report");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");

            option = scanner.nextLine().trim();

            // Legacy switch (Java 8) — case : break; syntax as required by spec.
            // Fall-through risk exists if break is omitted.
            switch (option) {
                case "1":
                    manager.addEmployee(scanner);
                    break;
                case "2":
                    manager.listEmployees();
                    break;
                case "3":
                    manager.searchById(scanner);
                    break;
                case "4":
                    manager.removeEmployee(scanner);
                    break;
                case "5":
                    config.showConfigData();
                    break;
                case "6":
                    sequenced.demonstrate(manager.getEmployeeList());
                    break;
                case "7":
                    filter.filterAndReport(manager.getEmployeeList());
                    break;
                case "0":
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }

        } while (!option.equals("0"));

        scanner.close();
    }
}