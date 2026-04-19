import java.util.List;

public class Main {

    public static void main(String[] args) {

        // --- Build the employee roster ---
        Developer dev1 = new Developer(1, "Alice Torres",  4500.0, "Java");
        Developer dev2 = new Developer(2, "Carlos Rios",   3800.0, "Python");
        Manager   mgr1 = new Manager(3,   "Diana Mejia",   7200.0, 50000.0);
        ExternalConsultant ext1 = new ExternalConsultant(4, "Luis Pena", "TechCorp", 85.0);

        List<Person> roster = List.of(dev1, dev2, mgr1, ext1);

        // --- TASK 3: Profile validation with legacy and modern instanceof ---
        System.out.println("=== Profile Validation ===");
        for (Person p : roster) {
            validateProfile(p);
        }

        // --- TASK 2: Emit end-of-month PerformanceReport for each Employee ---
        System.out.println("\n=== End-of-Month Performance Reports ===");
        List<Employee> employees = List.of(dev1, dev2, mgr1);
        for (Employee e : employees) {
            PerformanceReport report = generateReport(e);
            System.out.println(report);
        }

        // --- TASK 4: Promotion bonuses and promotion log ---
        System.out.println("\n=== Promotion Bonuses ===");
        List<Promotable> promotables = List.of(dev1, dev2, mgr1);
        for (Promotable promotable : promotables) {
            promotable.logPromotion();
        }
    }

    // TASK 3 — Pattern Matching for instanceof
    static void validateProfile(Person p) {

        /*
        Legacy style (Java 8/11): manual cast required after instanceof check.
        Risk: if the cast is wrong at runtime, a ClassCastException is thrown.
        
           if (p instanceof Developer) {
               Developer dev = (Developer) p;
               System.out.println("Developer language: " + dev.getPrimaryLanguage());
           } else if (p instanceof Manager) {
               Manager mgr = (Manager) p;
               System.out.println("Manager budget: " + mgr.getMonthlyBudget());
           
         Modern style (Java 17/21): Pattern Matching for instanceof.
         The binding variable (dev, mgr, ext) is declared and cast in one step.
         The compiler guarantees the cast is safe — no ClassCastException risk.
        */
        if (p instanceof Developer dev) {
            System.out.println("[Developer] " + dev.getName()
                    + " | Language: " + dev.getPrimaryLanguage()
                    + " | Salary: $" + dev.getSalary());

        } else if (p instanceof Manager mgr) {
            System.out.println("[Manager] " + mgr.getName()
                    + " | Budget: $" + mgr.getMonthlyBudget()
                    + " | Salary: $" + mgr.getSalary());

        } else if (p instanceof ExternalConsultant ext) {
            System.out.println("[ExternalConsultant] " + ext.getName()
                    + " | Agency: " + ext.getAgency()
                    + " | Rate: $" + ext.getHourlyRate() + "/hr");
        }
    }

    // TASK 2 — Generate immutable PerformanceReport for an employee
    static PerformanceReport generateReport(Employee e) {
        // Simulated average score based on salary tier
        double average = e.getSalary() >= 5000 ? 9.2 : 8.1;
        String feedback = average >= 9.0 ? "Excellent performance" : "Good performance";
        return new PerformanceReport(e.getId(), average, feedback);
    }
}