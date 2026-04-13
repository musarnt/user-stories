/* 
TASK 3: Sequenced Collections — The leap to Java 21 (LTS)
    Java 21 introduced the SequencedCollection interface, adding methods
    that make ordered access explicit and safe: getFirst(), getLast(), reversed().

    Before Java 21, accessing the first and last elements required manual indexing,
    which is error-prone: get(0) on an empty list throws IndexOutOfBoundsException,
    and get(list.size() - 1) is verbose and easy to miscalculate.

    Java 21 improvement:
    - getFirst() / getLast() are self-documenting — intent is clear at a glance.
    - reversed() returns a view in reverse order without modifying the original list
      or requiring manual loop logic / Collections.reverse() which mutates in place.
    - All three methods throw NoSuchElementException on empty lists,
      which is semantically more accurate than IndexOutOfBoundsException. 
*/

import java.util.ArrayList;

public class SequencedDemo {

    public void demonstrate(ArrayList<Employee> employeeList) {

        if (employeeList.isEmpty()) {
            System.out.println("\nNo employees to demonstrate. Add some first.");
            return;
        }

        // --- Legacy syntax (Java 8/11): manual index access ---

        System.out.println("\n=== Legacy Access (Java 8/11) ===\n");

        // First element: always index 0
        var first = employeeList.get(0);
        System.out.println("First employee: " + first);

        // Last element: requires size() - 1 calculation
        // Risk: if someone changes the list between size() and get(), index could be wrong
        var last = employeeList.get(employeeList.size() - 1);
        System.out.println("Last employee:  " + last);

        // --- Modern syntax (Java 21): Sequenced Collections ---

        System.out.println("\n=== Modern Access (Java 21) ===\n");

        // getFirst() — no index needed, reads like natural language
        var firstModern = employeeList.getFirst();
        System.out.println("First employee: " + firstModern);

        // getLast() — eliminates the size() - 1 pattern entirely
        var lastModern = employeeList.getLast();
        System.out.println("Last employee:  " + lastModern);

        /*  reversed() — returns a reversed VIEW of the list, original stays unchanged
            No need for Collections.reverse() which would mutate the list,
            or manual descending loops with error-prone index math.
        */
        System.out.println("\n=== Reversed List (Java 21) ===\n");
        var reversed = employeeList.reversed();
        for (var employee : reversed) {
            System.out.println(employee);
        }
    }
}