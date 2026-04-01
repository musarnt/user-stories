/**
 * MainLegacy - Day 6
 *
 * Demonstrates Java 8 legacy style vs Java 17+ modern style.
 *
 * KEY DIFFERENCES:
 * 1. String concatenation with + and \n vs Text Blocks
 * 2. Verbose getters (getNombre()) vs Record accessors (nombre())
 * 3. Manual toString() vs auto-generated Record toString()
 */

public class MainLegacy {

    public static void main(String[] args) {

        // Legacy string — manual concatenation with \n
        // Compare with the clean Text Block in day5/Main.java
        String banner = "******************************************\n" +
                        "* CORPORATE TALENT HUB v0.1 (LEGACY)    *\n" +
                        "* El codigo era mas largo entonces...    *\n" +
                        "******************************************";
        System.out.println(banner);

        // Creating object — same as Record but requires 50+ lines of boilerplate
        EmpleadoLegacy emp = new EmpleadoLegacy("Dev Antiguo", 99, 2000.0, false);

        // Legacy getters — verbose compared to Record accessors
        // Record:  dev.nombre()      dev.id()
        // Legacy:  emp.getNombre()   emp.getId()
        System.out.println("Empleado: " + emp.getNombre() + " (ID: " + emp.getId() + ")");
        System.out.println("Salario: " + emp.getSalarioBase());
        System.out.println("Remoto: " + emp.isEsRemoto());

        // toString() — works because we wrote it manually in EmpleadoLegacy
        // Without it, this would print: EmpleadoLegacy@1a2b3c
        System.out.println("toString: " + emp);

        // equals() — works because we wrote it manually
        EmpleadoLegacy emp2 = new EmpleadoLegacy("Dev Antiguo", 99, 2000.0, false);
        System.out.println("emp.equals(emp2): " + emp.equals(emp2)); // true
        System.out.println("emp == emp2: " + (emp == emp2));         // false — different references
    }
}