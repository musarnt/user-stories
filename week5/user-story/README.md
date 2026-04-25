# Corporate Talent Hub

A console-based employee management system built with Java 17, JDBC, and the H2 embedded database. This project demonstrates relational persistence, the MVC architectural pattern, modern Java resource management, and the use of records for immutable data transfer.

---

## Requirements

- Java 17 or higher
- Maven 3.6 or higher

No external database installation required. H2 runs embedded inside the application.

---

## How to Run

Clone the repository and navigate to the project directory:

    cd week5/user-story

Compile and run:

    mvn compile exec:java "-Dexec.mainClass=com.riwi.App"

The application will initialize the database schema automatically and launch the console menu.

---

## Project Structure

    src/main/java/com/riwi/
        App.java                          Entry point
        talent/
            model/
                Employee.java             Mutable entity used for write operations
                EmployeeRecord.java       Immutable record used for read/report operations
                EmployeeDAO.java          Interface defining the CRUD contract
                EmployeeDAOImpl.java      JDBC implementation of the DAO
                DatabaseConnection.java   Utility class managing the JDBC connection
            controller/
                EmployeeController.java   Mediator between view and model
            view/
                ConsoleView.java          All user interaction via Scanner

---

## Architecture: MVC Pattern

The project is organized following the Model-View-Controller pattern. The purpose of this separation is to ensure that each layer has a single, well-defined responsibility.

Model contains everything related to data: the entity classes, the DAO interface, its implementation, and the database connection utility. This layer knows about SQL and JDBC but nothing about the user interface.

View is the only layer that interacts with the user. All Scanner reads and all System.out output happen here. If the interface were replaced by a REST API or a graphical UI, only this layer would change.

Controller acts as a mediator. It receives data from the view, invokes the appropriate model operations, and returns results. It contains no SQL and no user interaction, only coordination logic.

This separation means that changing the database engine, replacing the UI, or modifying business rules each require touching exactly one layer.

---

## Technical Decisions

### H2 Embedded Database

H2 is a relational database engine written entirely in Java. It runs inside the same JVM process as the application, requiring no external server installation. The database is configured in in-memory mode, meaning data persists only for the duration of a single program execution. This makes the project fully portable: any machine with Java and Maven can run it without any additional setup.

Switching to a production database such as PostgreSQL requires changing only two things: the Maven dependency and the connection URL in DatabaseConnection.java. All other code remains unchanged because every layer communicates through JDBC, which provides a uniform interface regardless of the underlying database engine.

### Legacy vs Modern Connection Management

Before Java 7, developers had to close database resources manually using finally blocks. This approach was error-prone: if a developer forgot to close a Connection or Statement, the resource remained open indefinitely, consuming memory and eventually exhausting the connection pool. This is one of the most common causes of memory leaks in Java database applications.

Legacy approach (Java 6 and earlier):

    Connection conn = null;
    Statement stmt = null;
    try {
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
        stmt = conn.createStatement();
        stmt.execute("...");
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        if (stmt != null) try { stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
        if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
    }

Modern approach using try-with-resources (Java 7+, used throughout this project):

    try (Connection conn = DatabaseConnection.getConnection();
         Statement stmt = conn.createStatement()) {
        stmt.execute("...");
    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    }

Try-with-resources works because Connection, Statement, and ResultSet all implement the AutoCloseable interface. The compiler automatically generates the finally block, so resources are always closed when the try block exits, whether normally or through an exception.

### PreparedStatement and SQL Injection Prevention

All SQL queries in this project use PreparedStatement instead of string concatenation. Building SQL queries by concatenating user input directly is a critical security vulnerability. An attacker could input values such as 1 OR 1=1 to manipulate the query logic and access or destroy data.

PreparedStatement separates the SQL structure from the data values using ? placeholders. The JDBC driver sends the query structure and the parameter values separately to the database engine, which means user input is always treated as data and can never be interpreted as SQL syntax.

### Records vs Traditional POJOs

Java 14 introduced records as a language feature, finalized in Java 16. A record is an immutable data carrier whose constructor, accessors, equals, hashCode, and toString methods are generated automatically by the compiler.

This project uses two different structures intentionally. Employee is a traditional mutable class used for insert and update operations, where field values need to be set individually before being sent to the database. EmployeeRecord is an immutable record used exclusively for read operations and report generation. Once a row is read from the database and mapped to an EmployeeRecord, it cannot be accidentally modified by any other part of the code.

With a traditional POJO, reading a row requires instantiating an object and calling multiple setters, leaving the object in a mutable state throughout its lifetime. With a record, the entire row is captured in a single constructor call and the data is guaranteed to remain unchanged. This makes report generation safer and the intent of the code clearer.

### Text Blocks

The final report uses Java Text Blocks, introduced as a standard feature in Java 15. Text blocks allow multi-line string literals to be written with readable indentation directly in source code, without escape sequences for line breaks or quotation marks. The compiler strips the common leading indentation automatically, producing clean output at runtime.

---

## CRUD Operations

| Operation | SQL Statement                     | Method                          |
|-----------|-----------------------------------|---------------------------------|
| Insert    | INSERT INTO employees             | EmployeeDAOImpl.insert()        |
| Read all  | SELECT id, name, role, salary     | EmployeeDAOImpl.findAll()       |
| Update    | UPDATE employees SET ... WHERE id | EmployeeDAOImpl.update()        |
| Delete    | DELETE FROM employees WHERE id    | EmployeeDAOImpl.delete()        |
| Report    | SELECT ... ORDER BY salary DESC   | EmployeeDAOImpl.findAllAsRecords() |