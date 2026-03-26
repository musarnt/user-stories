/**
 * Employee - Task 2
 *
 * Traditional Java 8 style class.
 * Demonstrates all 8 primitive types used in a real business model.
 *
 * ANALYSIS - Class vs Record:
 * This class requires manual constructors, getters, and potentially
 * toString(), equals(), hashCode() — a lot of boilerplate code.
 * A Record (Java 17+) generates all of that automatically in one line,
 * and adds IMMUTABILITY: once created, its values cannot be changed.
 * Immutability makes code safer — no unexpected modifications.
 */

public class Employee {

    // PRIMITIVE TYPES

    // byte: 8-bit integer (-128 to 127). Used for small numeric categories.
    byte employeeLevel;

    // short: 16-bit integer (-32,768 to 32,767). Used for years.
    short hireYear;

    // int: 32-bit integer. Standard choice for IDs and counters.
    int employeeId;

    // long: 64-bit integer. Required for large numbers like national IDs.
    // The suffix L tells Java this is a long literal, not an int.
    long nationalId;

    // float: 32-bit decimal. Suffix f is required, otherwise Java reads it as double.
    // Used for percentages where high precision is not critical.
    float performanceScore;

    // double: 64-bit decimal. Default type for decimals in Java.
    // Preferred for money and salary — more precise than float.
    double baseSalary;

    // char: stores a single character. Uses single quotes, not double quotes.
    char departmentInitial;

    // boolean: true or false. No other values possible.
    boolean isActive;

    // --- STRING (not a primitive, it's an object) ---
    // String is included because it's the standard for text in Java.
    // It lives in the Heap, unlike the primitives above.
    String fullName;

    // --- BONUS: used in Task 3 ---
    double monthlyBonus;

    // Constructor: initializes all fields when creating an Employee object
    public Employee(byte employeeLevel, short hireYear, int employeeId,
                    long nationalId, float performanceScore, double baseSalary,
                    char departmentInitial, boolean isActive,
                    String fullName, double monthlyBonus) {

        this.employeeLevel = employeeLevel;
        this.hireYear = hireYear;
        this.employeeId = employeeId;
        this.nationalId = nationalId;
        this.performanceScore = performanceScore;
        this.baseSalary = baseSalary;
        this.departmentInitial = departmentInitial;
        this.isActive = isActive;
        this.fullName = fullName;
        this.monthlyBonus = monthlyBonus;
    }
}