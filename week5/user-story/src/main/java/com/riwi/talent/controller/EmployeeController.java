package com.riwi.talent.controller;

import com.riwi.talent.model.Employee;
import com.riwi.talent.model.EmployeeDAO;
import com.riwi.talent.model.EmployeeDAOImpl;
import com.riwi.talent.model.EmployeeRecord;

import java.util.List;

// EmployeeController: mediator between the View and the Model.
// The view never talks directly to the DAO — it always goes through the controller.
// This means: if the database changes, only the model layer changes.
// If the UI changes (console to web), only the view layer changes.
// The controller stays stable because its job is just coordination.
public class EmployeeController {

    // Depends on the interface, not the implementation.
    // If we ever switch from H2 to PostgreSQL, we only change this one line.
    private final EmployeeDAO dao;

    public EmployeeController() {
        this.dao = new EmployeeDAOImpl();
    }

    public void addEmployee(String name, String role, double salary) {
        dao.insert(new Employee(name, role, salary));
    }

    public List<Employee> getAllEmployees() {
        return dao.findAll();
    }

    public void updateEmployee(int id, String name, String role, double salary) {
        dao.update(new Employee(id, name, role, salary));
    }

    public void deleteEmployee(int id) {
        dao.delete(id);
    }

    public String generateReport() {
        List<EmployeeRecord> records = dao.findAllAsRecords();

        // Text Block (Java 15+): multi-line string literal.
        // Indentation is stripped automatically — the format is readable in source code.
        StringBuilder report = new StringBuilder();
        report.append("""
                ================================================
                       CORPORATE TALENT HUB - FINAL REPORT
                ================================================
                """);

        if (records.isEmpty()) {
            report.append("No employees registered in the system.\n");
        } else {
            for (EmployeeRecord r : records) {
                // record components are accessed via auto-generated methods: r.id(), r.name(), etc.
                report.append(String.format("""
                        ID     : %d
                        Name   : %s
                        Role   : %s
                        Salary : $%.2f
                        ------------------------------------------------
                        """, r.id(), r.name(), r.role(), r.salary()));
            }
        }

        return report.toString();
    }
}