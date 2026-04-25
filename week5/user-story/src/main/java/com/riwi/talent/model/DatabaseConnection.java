package com.riwi.talent.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

// Utility class: manages the JDBC connection to the H2 in-memory database.
// Never instantiated — all methods are static.
public class DatabaseConnection {

    // H2 in-memory database URL.
    // "mem:talentdb" means the database lives in RAM (no files on disk).
    // "DB_CLOSE_DELAY=-1" keeps the database alive for the entire JVM lifetime.
    private static final String URL = "jdbc:h2:mem:talentdb;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    // Returns a new Connection to the database.
    // The caller must close it — the recommended way is try-with-resources.
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Creates the employees table on application startup.
    public static void initializeSchema() {

        // LEGACY approach (Java 8 and earlier) — connections had to be closed manually:
        //
        //   Connection conn = null;
        //   Statement stmt = null;
        //   try {
        //       conn = DriverManager.getConnection(URL, USER, PASSWORD);
        //       stmt = conn.createStatement();
        //       stmt.execute("CREATE TABLE ...");
        //   } catch (SQLException e) {
        //       e.printStackTrace();
        //   } finally {
        //       // Easy to forget — if skipped, the connection stayed open forever (memory leak)
        //       if (stmt != null) try { stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
        //       if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        //   }
        //
        // The problem: if the developer forgot to close, the connection remained open
        // in the database pool, consuming memory. With hundreds of requests, this
        // exhausted all available connections — a classic memory leak.
        //
        // MODERN approach (Java 17+): try-with-resources closes Connection and Statement
        // automatically when the block exits, even if an exception occurs.
        // The compiler generates the finally block for us — it cannot be forgotten.

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute("""
                    CREATE TABLE IF NOT EXISTS employees (
                        id     INT AUTO_INCREMENT PRIMARY KEY,
                        name   VARCHAR(100) NOT NULL,
                        role   VARCHAR(100) NOT NULL,
                        salary DOUBLE       NOT NULL
                    )
                    """);

            System.out.println("Database schema initialized successfully.");

        } catch (SQLException e) {
            System.out.println("Error initializing schema: " + e.getMessage());
        }
    }

    // Private constructor: prevents instantiation of this utility class
    private DatabaseConnection() {}
}