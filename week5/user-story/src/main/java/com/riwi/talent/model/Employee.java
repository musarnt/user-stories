package com.riwi.talent.model;

// Employee: mutable entity class used for insert and update operations.
// We use a class (not a record) here because we need to modify fields
// during data entry before sending them to the database.
public class Employee {

    private int id;
    private String name;
    private String role;
    private double salary;

    // Constructor with id — used when reading from the database
    public Employee(int id, String name, String role, double salary) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.salary = salary;
    }

    // Constructor without id — used when inserting a new employee
    // (the database auto-generates the id)
    public Employee(String name, String role, double salary) {
        this.name = name;
        this.role = role;
        this.salary = salary;
    }

    public int getId()       { return id; }
    public String getName()  { return name; }
    public String getRole()  { return role; }
    public double getSalary(){ return salary; }

    public void setId(int id)          { this.id = id; }
    public void setName(String name)   { this.name = name; }
    public void setRole(String role)   { this.role = role; }
    public void setSalary(double s)    { this.salary = s; }
}