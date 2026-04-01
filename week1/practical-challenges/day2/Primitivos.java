/**
 * Primitivos - Practical Challenge Day 2
 *
 * Demonstrates:
 * 1. All 8 primitive types with valid values
 * 2. Overflow behavior with byte
 * 3. Text Block for system report
 * 4. Record immutability
 */

public class Primitivos {

    // Inner record — modern Java 17+ way to define immutable data
    record Empresa(String nombre, int sedes) {}

    public static void main(String[] args) {

        // =========================================================
        // 1. ALL 8 PRIMITIVE TYPES
        // =========================================================

        byte nivel = 5;                    // 8-bit: -128 to 127
        short anio = 2024;                 // 16-bit: -32,768 to 32,767
        int empleadoId = 1001;             // 32-bit: standard integer
        long cedula = 1234567890L;         // 64-bit: L suffix required
        float rendimiento = 92.5f;         // 32-bit decimal: f suffix required
        double salario = 3500000.00;       // 64-bit decimal: default for decimals
        char departamento = 'T';           // single character: single quotes
        boolean activo = true;             // true or false only

        System.out.println("=== Primitive Types ===");
        System.out.println("nivel: " + nivel);
        System.out.println("anio: " + anio);
        System.out.println("empleadoId: " + empleadoId);
        System.out.println("cedula: " + cedula);
        System.out.println("rendimiento: " + rendimiento);
        System.out.println("salario: " + salario);
        System.out.println("departamento: " + departamento);
        System.out.println("activo: " + activo);

        // =========================================================
        // 2. OVERFLOW ANALYSIS
        // =========================================================

        // byte max value is 127. Adding 1 causes overflow.
        // In binary, 127 is 01111111. Adding 1 flips all bits to 10000000,
        // which in two's complement represents -128.
        // This is not a bug — it is defined behavior in statically typed languages.
        byte n = 127;
        n = (byte) (n + 1);
        System.out.println("\n=== Overflow Analysis ===");
        System.out.println("byte 127 + 1 = " + n); // prints -128

        // =========================================================
        // 3. TEXT BLOCK — system report
        // =========================================================

        String reporte = """
                ╔══════════════════════════════════╗
                ║   CORPORATE TALENT HUB           ║
                ║   System Report - Week 1         ║
                ║   Active employees: 1            ║
                ╚══════════════════════════════════╝
                """;
        System.out.println(reporte);

        // =========================================================
        // 4. RECORD IMMUTABILITY
        // =========================================================

        Empresa empresa = new Empresa("Talent Corp", 5);
        System.out.println("Empresa: " + empresa.nombre);

        // Uncommenting the line below causes a COMPILE ERROR:
        // cannot assign a value to final variable nombre
        // Records are immutable — fields are final by design.
        // empresa.nombre = "Otra empresa"; // ← compile error
    }
}