/**
 * ArchitectureNotes - Task 1
 *
 * This class has no runtime logic. Its purpose is to document
 * the architectural decisions and Java ecosystem context
 * for the Corporate Talent Hub system.
 */

public class ArchitectureNotes {

    /*
     * JAVA ECOSYSTEM: LEGACY vs MODERN LTS
     *
     * JAVA 8 (Legacy - 2014):
     * - Introduced lambdas and the Stream API (functional style)
     * - Introduced Optional to handle nulls more safely
     * - Still widely used in enterprise, but considered legacy
     * - Very verbose: simple data classes required constructors,
     *   getters, setters, equals(), hashCode(), toString() manually
     * - Garbage Collector: used CMS (Concurrent Mark Sweep),
     *   which caused stop-the-world pauses
     *
     * JAVA 17 (LTS - 2021) and JAVA 21 (LTS - 2023):
     * - Modern approach: less boilerplate, more expressive code
     * - Introduced Records: immutable data classes in one line
     * - Introduced Text Blocks: multi-line strings without escapes
     * - Introduced Pattern Matching: smarter type checking
     * - Sealed Classes: controlled class hierarchies
     * - Garbage Collector: G1GC as default (lower pause times),
     *   ZGC available for ultra-low latency workloads
     *
     * KEY DIFFERENCE IN PHILOSOPHY:
     * Java 8 forced developers to write a lot of ceremony code.
     * Java 17/21 focuses on expressiveness — write less, mean more.
     */

    /*
     * JVM AND GARBAGE COLLECTOR - MEMORY MANAGEMENT
     *
     * HOW THE JVM MANAGES OBJECTS:
     *
     * 1. STACK (method memory):
     *    - Stores primitive values and object references
     *    - Each method call gets its own stack frame
     *    - Automatically freed when the method returns
     *    - Very fast, but limited in size
     *
     * 2. HEAP (object memory):
     *    - Where all objects actually live (new Employee(), etc.)
     *    - Shared across the entire program
     *    - Managed by the Garbage Collector
     *
     * HOW THE GARBAGE COLLECTOR WORKS:
     *    - It tracks which objects are still reachable
     *      (referenced by something in the stack or other objects)
     *    - Objects with no references become eligible for collection
     *    - GC runs automatically — the developer does not call it
     *
     * JAVA 8 GC (CMS - Concurrent Mark Sweep):
     *    - Caused "stop-the-world" pauses: the app froze briefly
     *      while GC cleaned memory
     *    - Problem: unpredictable pause times under heavy load
     *
     * JAVA 17/21 GC (G1GC default + ZGC optional):
     *    - G1GC divides heap into regions and collects incrementally
     *    - Much shorter and more predictable pause times
     *    - ZGC targets sub-millisecond pauses for large heaps
     *    - Result: better performance for enterprise systems
     *    - like Corporate Talent Hub under concurrent user load
     */
}