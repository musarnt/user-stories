// TASK 1: Switch Legacy vs Modern
// Comparison: Java 8 switch uses 'case : break;' which risks fall-through if break is forgotten.
// Java 17/21 switch expression uses '->' syntax, eliminates fall-through and can return a value directly.

public class MainMenu {

    // Java 8 legacy syntax — fall-through risk if break is omitted
    public void showMainMenu(int option) {
        switch (option) {
            case 1:
                System.out.println("Register employee");
                break;
            case 2:
                System.out.println("Query employee");
                break;
            case 3:
                System.out.println("Calculate salary");
                break;
            default:
                System.out.println("Invalid option");
                break;
        }
    }

    // Java 17/21 modern syntax — no fall-through, returns value directly
    public String getSalaryCategory(double salary) {

        // Convert salary range to a level — ternary operator chain
        int level = salary <= 2000 ? 1 :
                    salary <= 4000 ? 2 :
                    salary <= 7000 ? 3 : 4;

        return switch (level) {
            case 1 -> "Junior";
            case 2 -> "Mid-Senior";
            case 3 -> "Senior";
            case 4 -> "Expert";
            default -> "Unknown";
        };
    }
}