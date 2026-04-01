package com.riwi;

/**
 * App - Practical Challenge Day 4
 *
 * Demonstrates Helpful NullPointerExceptions (Java 14+)
 * and Maven project lifecycle.
 */
public class App {
    public static void main(String[] args) {

        // Assigning null to a String object
        String employeeName = null;

        // Java 14+ Helpful NullPointerException:
        // When this runs, Java tells you exactly what was null:
        // "Cannot invoke String.length() because employeeName is null"
        //
        // Java 8 only said: NullPointerException — no details at all.
        System.out.println(employeeName.length()); // intentional NPE
    }
}