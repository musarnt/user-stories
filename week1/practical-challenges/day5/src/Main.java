import com.startup.models.Empleado;
import com.startup.models.Empresa;
import com.startup.utils.CalculadoraNomina;

/**
 * Main - Day 5
 *
 * Entry point of the micro-payroll system.
 * Demonstrates Records, Text Blocks, short-circuit operators,
 * salary calculation, and overflow behavior.
 */
public class Main {

    public static void main(String[] args) {

        // Text Block (Java 17+) — multi-line string without \n
        String banner = """
                ******************************************
                * CORPORATE TALENT HUB v1.0              *
                * Gestionando el futuro del codigo       *
                ******************************************
                """;
        System.out.println(banner);

        // Instantiate Employee Record
        Empleado dev = new Empleado("Coder Java", 101, 3000.0, true);
        System.out.println("Employee: " + dev);

        // Short-circuit operator &&:
        // If esRemoto() is false, Java does not evaluate id() % 2 == 0
        // This saves processing — the second condition is only checked when needed
        if (dev.esRemoto() && dev.id() % 2 == 0) {
            System.out.println("Employee " + dev.nombre() + " assigned to Virtual Even Hub.");
        } else {
            System.out.println("Employee " + dev.nombre() + " assigned to Virtual Odd Hub.");
        }

        // Calculate net salary
        double neto = CalculadoraNomina.calcularNeto(dev.salarioBase(), 500);
        System.out.println("Net salary: $" + neto);

        // Overflow demonstration
        CalculadoraNomina.demostrarOverflow();

        // Immutability demonstration — uncommenting causes compile error:
        // cannot assign a value to final variable salarioBase
        // dev.salarioBase = 5000; // ← compile error

        // Instantiate Company Record
        Empresa empresa = new Empresa("Talent Corp", 3);
        System.out.println("Company: " + empresa);
    }
}