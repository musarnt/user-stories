
/*

TASK 4: Interface with abstract method and default method (Java 8+)

Legacy note (pre-Java 8): interfaces could only declare abstract methods.
Any change to an interface broke all implementing classes.
Java 8+ evolution: default methods allow new behavior to be added to an
interface without forcing every implementing class to override it.

*/
public interface Promotable {

    // Abstract method: each implementing class must define its own bonus logic
    double calculateBonus();

    // Default method: shared log behavior — classes inherit this for free
    // without needing to override it, preserving backward compatibility
    default void logPromotion() {
        System.out.println("[LOG] Promotion process triggered. Bonus calculated: $" + calculateBonus());
    }
}
