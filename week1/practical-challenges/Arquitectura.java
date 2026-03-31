public class Arquitectura {
    public static void main(String[] args) {
        System.out.println("Compilación con JDK y ejecución con JVM");

    }
}

/**
 * Arquitectura - Practical Challenge
 *
 * Demonstrates the manual Java compilation and execution lifecycle:
 * Source code (.java) → Bytecode (.class) → JVM execution
 *
 * KEY FINDING:
 * After deleting this .java file, the program still runs using only
 * the .class file. The JVM is the component responsible for reading
 * and executing the bytecode — it does not need the source code.
 *
 * BYTECODE (javap -c output analysis):
 * getstatic     → loads System.out (the object that prints to console)
 * ldc           → loads the String literal into memory
 * invokevirtual → calls the println() method with that String
 * return        → ends the method execution
 *
 * CONCLUSION:
 * Java distributes .class files, not .java files.
 * Any machine with a JVM installed can run the same .class file
 * regardless of the operating system — this is "Write Once, Run Anywhere".
 */