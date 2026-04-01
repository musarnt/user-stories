import java.util.Objects;

/**
 * EmpleadoLegacy - Day 6
 *
 * Java 8 style class — demonstrates the verbosity of traditional data models.
 * Compare this 50+ line class with the single line Record in day 5:
 *
 * Modern (Java 17+):
 * public record Empleado(String nombre, int id, double salarioBase, boolean esRemoto) {}
 *
 * Legacy (Java 8):
 * Everything below — constructor, getters, toString, equals, hashCode — all manual.
 */

public class EmpleadoLegacy {

    // Fields must be declared manually
    private final String nombre;
    private final int id;
    private final double salarioBase;
    private final boolean esRemoto;

    // Constructor must be written manually
    public EmpleadoLegacy(String nombre, int id, double salarioBase, boolean esRemoto) {
        this.nombre = nombre;
        this.id = id;
        this.salarioBase = salarioBase;
        this.esRemoto = esRemoto;
    }

    // Getters must be written manually — note: getNombre() instead of nombre()
    public String getNombre() { return nombre; }
    public int getId() { return id; }
    public double getSalarioBase() { return salarioBase; }
    public boolean isEsRemoto() { return esRemoto; }

    // toString must be written manually
    // Without this, printing the object shows something like: EmpleadoLegacy@1a2b3c
    @Override
    public String toString() {
        return "EmpleadoLegacy{nombre='" + nombre + "', id=" + id +
               ", salarioBase=" + salarioBase + ", esRemoto=" + esRemoto + "}";
    }

    // equals must be written manually
    // Without this, two objects with identical data are considered different
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmpleadoLegacy that = (EmpleadoLegacy) o;
        return id == that.id && Objects.equals(nombre, that.nombre);
    }

    // hashCode must be written manually
    // Without this, objects fail to work correctly in HashMap and HashSet
    @Override
    public int hashCode() {
        return Objects.hash(nombre, id);
    }
}