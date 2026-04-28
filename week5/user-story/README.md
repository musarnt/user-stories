# Corporate Talent Hub

A console-based employee management system developed for a mid-sized company looking to digitize its HR operations. The system allows the HR team to register, update, and query employee records through a console interface backed by a relational database.

This implementation represents the foundational backend layer of the platform, built with Java 17 and JDBC, designed to scale toward a full REST API with Spring Boot in future iterations.

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

## Technology Strategy

The technology choices in this project were made as a coherent stack, not as isolated decisions.

H2 was selected over an external database engine because portability was a priority: the system must run on any machine without configuration. JDBC was chosen over a higher-level ORM such as Hibernate because the project scope requires understanding raw SQL and connection lifecycle management — abstractions would hide the concepts this implementation is meant to demonstrate.

Java 17 records complement JDBC by eliminating mutable intermediate objects during read operations. The combination reduces boilerplate, prevents accidental data mutation in the report layer, and makes the mapping from ResultSet to data structure explicit and traceable.

PreparedStatement combined with input validation at the view layer creates two independent security barriers: one at the UI level (rejecting empty or invalid values before they reach the database) and one at the SQL level (preventing injection regardless of what values arrive). Neither layer depends on the other, which means the system remains secure even if one barrier is bypassed.

MVC separates these concerns so that each technology operates within its designated layer. JDBC stays in the model, validation stays in the view, and the controller remains free of both — making the codebase maintainable and each layer independently testable.

---

## Limitations and Future Improvements

The current implementation covers the core functional requirements. The following improvements are identified for future iterations:

The in-memory H2 database does not persist data between executions. Migrating to a file-based H2 configuration or a production database such as PostgreSQL would require changing only the connection URL and Maven dependency, with no changes to the DAO or controller layers.

Each database operation opens and closes a new connection. In a production environment with concurrent users, a connection pool such as HikariCP would significantly reduce overhead by reusing existing connections instead of creating new ones on every request.

Input validation is currently handled at the console layer. In a REST API context, this responsibility would move to a dedicated validation layer using annotations such as those provided by Jakarta Bean Validation, keeping validation rules close to the data model rather than the interface.

The DAO interface is designed to support alternative implementations without modifying the controller or view. A future iteration could introduce a Spring Boot REST layer on top of the existing model and controller, reusing the business logic while replacing only the view layer.

---

## CRUD Operations

| Operation | SQL Statement                     | Method                             |
|-----------|-----------------------------------|------------------------------------|
| Insert    | INSERT INTO employees             | EmployeeDAOImpl.insert()           |
| Read all  | SELECT id, name, role, salary     | EmployeeDAOImpl.findAll()          |
| Update    | UPDATE employees SET ... WHERE id | EmployeeDAOImpl.update()           |
| Delete    | DELETE FROM employees WHERE id    | EmployeeDAOImpl.delete()           |
| Report    | SELECT ... ORDER BY salary DESC   | EmployeeDAOImpl.findAllAsRecords() |