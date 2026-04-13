/*  TASK 2: Initialization and Factory Methods (Legacy Java 9/11)
    List.of() and Map.of() were introduced in Java 9 and became standard in Java 11.

    Why are these safer than a traditional ArrayList?
        1. IMMUTABILITY: once created, elements cannot be added, removed, or replaced.
        Calling .add() or .remove() throws UnsupportedOperationException at runtime.
        This guarantees that configuration data stays consistent throughout execution.
        2. NULL REJECTION: List.of() and Map.of() reject null values immediately,
        throwing NullPointerException at creation time — not later during usage.
        3. THREAD SAFETY: immutable collections can be shared across threads
        without synchronization, since no thread can modify them.
        Trade-off: if you need to add or remove elements dynamically,
        you must use ArrayList or HashMap instead — immutability is the cost of safety. 
*/

import java.util.List;
import java.util.Map;

public class ConfigData {

    // Immutable list of technologies the company works with.
    // Any call to techStack.add("Go") would throw UnsupportedOperationException.
    private final List<String> techStack = List.of(
            "Java",
            "Spring Boot",
            "PostgreSQL",
            "Docker",
            "AWS"
    );

    // Immutable map of company locations: code -> city name.
    // Map.of() creates an unmodifiable map with up to 10 key-value pairs.
    // For more than 10 entries, use Map.ofEntries() instead.
    private final Map<String, String> locations = Map.of(
            "HQ", "Medellin",
            "BR1", "Bogota",
            "BR2", "Cali"
    );

    public void showConfigData() {
        System.out.println("\n=== Technology Stack ===\n");
        for (var tech : techStack) {
            System.out.println("  - " + tech);
        }

        System.out.println("\n=== Company Locations ===\n");
        for (var entry : locations.entrySet()) {
            System.out.printf("  [%s] %s%n", entry.getKey(), entry.getValue());
        }
    }
}