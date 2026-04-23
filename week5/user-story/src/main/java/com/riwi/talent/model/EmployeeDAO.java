package com.riwi.talent.model;

import java.util.List;

// DAO interface: defines the contract for all database operations on Employee.
// Separating the interface from the implementation allows us to swap the
// underlying database (H2, PostgreSQL, MySQL) without changing other layers.
public interface EmployeeDAO {
    void insert(Employee employee);
    List<Employee> findAll();
    void update(Employee employee);
    void delete(int id);

    // Returns employees as immutable records — used for read-only reporting
    List<EmployeeRecord> findAllAsRecords();
}