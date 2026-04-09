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

### User Stories — Plain Java (all weeks)

    cd weekX/user-story
    javac *.java
    java Main

---

## Branching Strategy (Gitflow)

This project follows Gitflow conventions:

| Branch | Purpose |
|---|---|
| `main` | Final delivery — stable code only |
| `develop` | Integration branch — all completed weeks |
| `feature/weekX-user-story` | Active development branch per week |

**Workflow per week:**

    develop → feature/weekX-user-story → develop → main (final delivery)

---

## Tech Stack

- Java 21 (Eclipse Temurin)
- VS Code with Extension Pack for Java

---

## Weekly Progress

| Week | Topic | Status |
|---|---|---|
| Week 1 | Architecture, primitives, Records, operator precedence | Done |
| Week 2 | Control flow, switch expressions, matrices, exceptions | Done |
| Week 3 | | Pending |
| Week 4 | | Pending |
| Week 5 | | Pending |

---

## Language Convention

- **Code, variables, and comments:** English