# User Stories - Corporate Talent Hub

Java learning project developed at Riwi coding bootcamp.
Each week covers a user story based on the Moodle course content.

---

## Project Structure

    user-stories/
    ├── week1/
    │   └── user-story/   # Weekly user story implementation
    ├── week2/
    │   └── user-story/
    ├── week3/
    │   └── user-story/
    ├── week4/
    │   └── user-story/
    └── week5/
        └── user-story/

---

## How to Run the Code

### Weeks 1 to 4 — Plain Java

    cd weekX/user-story
    javac *.java
    java Main

### Week 5 — Maven project

    cd week5/user-story
    mvn compile exec:java "-Dexec.mainClass=com.riwi.App"

---

## Branching Strategy (Gitflow)

This project follows Gitflow conventions:

| Branch | Purpose |
|---|---|
| `main` | Final delivery — stable code only |
| `develop` | Integration branch — all completed weeks |
| `feature/weekX-user-story` | Active development branch per week |

**Workflow per week:**

    develop -> feature/weekX-user-story -> develop -> main (final delivery)

---

## Tech Stack

- Java 17 (weeks 1-4), Maven + H2 (week 5)
- VS Code with Extension Pack for Java

---

## Weekly Progress

| Week | Topic | Status |
|---|---|---|
| Week 1 | JVM architecture, primitives, records, operator precedence | Done |
| Week 2 | Control flow, switch expressions, matrices, exceptions | Done |
| Week 3 | Collections, generics, functional interfaces | Done |
| Week 4 | Sealed classes, interfaces, pattern matching, inheritance | Done |
| Week 5 | JDBC, MVC architecture, H2 database, CRUD | Done |

---

## Language Convention

- **Code, variables, and comments:** English
- **READMEs:** English