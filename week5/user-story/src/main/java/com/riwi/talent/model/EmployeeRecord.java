package com.riwi.talent.model;

// Java 17+ record: immutable structure for transferring employee data.
// Unlike a traditional POJO, it does not need manual getters, setters, or constructors.
// The compiler generates them automatically from the declared components.
public record EmployeeRecord(
        int id,
        String name,
        String role,
        double salary
) {}