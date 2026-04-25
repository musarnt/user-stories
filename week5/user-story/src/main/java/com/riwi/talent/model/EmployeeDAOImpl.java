package com.riwi.talent.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Physical implementation of EmployeeDAO.
// All SQL queries use PreparedStatement to prevent SQL injection attacks.
//
// SQL Injection example (what PreparedStatement prevents):
//   If we built SQL like: "DELETE FROM employees WHERE id = " + id
//   An attacker could pass id = "1 OR 1=1" which would delete ALL rows.
//   PreparedStatement treats user input strictly as data, never as SQL code.
public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public void insert(Employee employee) {
        // "?" placeholders are replaced with actual values by the driver —
        // they can never be interpreted as SQL syntax.
        String sql = "INSERT INTO employees (name, role, salary) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, employee.getName());
            stmt.setString(2, employee.getRole());
            stmt.setDouble(3, employee.getSalary());
            stmt.executeUpdate();

            System.out.println("Employee inserted successfully.");

        } catch (SQLException e) {
            System.out.println("Error inserting employee: " + e.getMessage());
        }
    }

    @Override
    public List<Employee> findAll() {
        String sql = "SELECT id, name, role, salary FROM employees";
        List<Employee> employees = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // ResultSet is a cursor: it moves row by row through the query results.
            while (rs.next()) {
                employees.add(new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("role"),
                        rs.getDouble("salary")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving employees: " + e.getMessage());
        }

        return employees;
    }

    @Override
    public void update(Employee employee) {
        String sql = "UPDATE employees SET name = ?, role = ?, salary = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, employee.getName());
            stmt.setString(2, employee.getRole());
            stmt.setDouble(3, employee.getSalary());
            stmt.setInt(4, employee.getId());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                System.out.println("No employee found with id: " + employee.getId());
            } else {
                System.out.println("Employee updated successfully.");
            }

        } catch (SQLException e) {
            System.out.println("Error updating employee: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM employees WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                System.out.println("No employee found with id: " + id);
            } else {
                System.out.println("Employee deleted successfully.");
            }

        } catch (SQLException e) {
            System.out.println("Error deleting employee: " + e.getMessage());
        }
    }

    @Override
    public List<EmployeeRecord> findAllAsRecords() {
        // Maps each database row directly to an immutable EmployeeRecord.
        // With a traditional POJO we would instantiate a class and call setters —
        // mutable and verbose. With a record, one constructor call per row is enough.
        // The immutability also guarantees the report data cannot be accidentally modified.
        String sql = "SELECT id, name, role, salary FROM employees ORDER BY salary DESC";
        List<EmployeeRecord> records = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                records.add(new EmployeeRecord(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("role"),
                        rs.getDouble("salary")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving employee records: " + e.getMessage());
        }

        return records;
    }
}