# User Stories - Corporate Talent Hub

Java learning project developed at Riwi coding bootcamp.
Each week covers a user story, practical challenges, and research topics
based on the Moodle course content.

---

## Project Structure

    user-stories/
    ├── week1/
    │   ├── user-story/           # Weekly user story implementation
    │   ├── practical-challenges/ # Daily coding challenges
    │   └── research-topics/      # Research and investigation
    ├── week2/
    │   └── ...
    ├── week3/
    │   └── ...
    ├── week4/
    │   └── ...
    └── week5/
        └── ...

---

## How to Run the Code

### User Stories — Plain Java (all weeks)

    cd weekX/user-story
    javac *.java
    java Main

### Practical Challenges — Plain Java (Week 1)

    cd week1/practical-challenges/dayX
    javac FileName.java
    java FileName

### Practical Challenges — Maven (Week 2+)

    cd weekX/practical-challenges/dayX
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

    develop → feature/weekX-user-story → develop → main (final delivery)

---

## Tech Stack

- Java 21 (Eclipse Temurin)
- Maven 3.9.x (Week 2+)
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
- **Research topics (research-topics/):** Spanish