/*
TASK 1: Sealed class hierarchy (Java 17/21)

Legacy style (Java 8/11): abstract class with open inheritance.
Any class in any package could extend Person freely:

public abstract class Person { ... }

Problem: the domain is unprotected. Any class - intended or not -
could extend Person without the API designer ever intending it.
This makes exhaustive pattern matching impossible and weakens domain control.

Modern style (Java 17/21): sealed class with explicit permits clause.
Only Employee and ExternalConsultant are authorized to extend Person.
The compiler enforces this at compile time — no other class can extend Person,
making the type hierarchy closed, auditable, and safe for pattern matching.
*/
public sealed class Person permits Employee, ExternalConsultant {

    // private: only accessible within Person itself
    private final int id;
    private final String name;

    public Person(int id, String name) {
        if (id <= 0) throw new IllegalArgumentException("ID must be positive.");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name cannot be blank.");
        this.id = id;
        this.name = name;
    }

    public int getId()       { return id; }
    public String getName()  { return name; }

    @Override
    public String toString() {
        return "Person[id=" + id + ", name=" + name + "]";
    }
}