# Week 4 - Corporate Talent Hub: Advanced OOP Architecture

## User Story

Refactor the talent management system to support different employee profiles (Developers, Managers)
by applying the four pillars of OOP. The solution compares legacy Java 8/11 patterns with modern
Java 17/21 features including Sealed Classes, Records, and Pattern Matching.

---

## Tasks Implemented

### Task 1 - Sealed vs Open Inheritance

Defines the type hierarchy using a sealed class to protect the business domain.

- Legacy (Java 8/11): open abstract class — any class could extend `Person` freely.
- Modern (Java 17/21): `Person` is sealed and only permits `Employee` and `ExternalConsultant`.
- Technical comment inside `Person.java` explains why sealed classes offer stronger API design safety.

### Task 2 - Immutable Data Modeling with Records

Creates lightweight, immutable report structures for end-of-month performance summaries.

- Legacy (Java 8/11): manual POJO with constructor, getters, and `toString` — shown as a comment in `PerformanceReport.java`.
- Modern (Java 17/21): `PerformanceReport` record with auto-generated constructor, accessors, `equals()`, `hashCode()`, and `toString()`.

### Task 3 - Polymorphism and Pattern Matching (Java 21)

Implements role-specific behavior eliminating repetitive casting code.

- Subclasses: `Developer` (attribute: `primaryLanguage`) and `Manager` (attribute: `monthlyBudget`).
- Legacy (Java 8/11): `instanceof` check followed by manual cast — shown as a comment in `Main.java`.
- Modern (Java 17/21): Pattern Matching for `instanceof` — binding variable declared and cast in one step.

### Task 4 - Interface Abstraction and Evolution

Defines behavioral contracts with the ability to evolve without breaking existing code.

- `Promotable` interface with abstract method `calculateBonus()`.
- Default method `logPromotion()` added following Java 8+ evolution — implementing classes inherit it without override.
- All hierarchy attributes use `private` and `protected` modifiers for full encapsulation.

---

## Class Hierarchy

Person (sealed)
├── Employee (non-sealed)
│   ├── Developer (final) implements Promotable
│   └── Manager (final) implements Promotable
└── ExternalConsultant (final)

---

## How to Run

```powershell
cd week4/user-story
javac *.java
java Main
```

---

## Files

| File | Responsibility |
|---|---|
| `Person.java` | Sealed base class — Task 1 |
| `Employee.java` | Non-sealed intermediate class — Task 1 |
| `ExternalConsultant.java` | Leaf class, external profile — Task 1 |
| `Developer.java` | Developer subclass with bonus logic — Task 3, Task 4 |
| `Manager.java` | Manager subclass with bonus logic — Task 3, Task 4 |
| `PerformanceReport.java` | Immutable record for monthly reports — Task 2 |
| `Promotable.java` | Interface with default method — Task 4 |
| `Main.java` | Entry point — demonstrates all four tasks |

---

## Java Features Used

- Sealed classes with `permits` clause (Java 17)
- Records (Java 16+)
- Pattern Matching for `instanceof` (Java 16+)
- Default methods in interfaces (Java 8+)
- `private` / `protected` encapsulation
- Immutable collections with `List.of()`