# Week 3 - User Story: Corporate Talent Hub

## Objective
Evolve static storage into a dynamic architecture using the Java Collections Framework.
Comparing collection handling from Legacy Java 8/11 with modern Java 21 LTS capabilities
for ordering and sequence management.

## Tasks
- **Task 1** - Migration to ArrayList and HashMap (Legacy 8/11): dynamic storage with add, list, search by ID, and remove operations
- **Task 2** - Initialization and Factory Methods (Legacy 9/11): immutable configuration data using List.of() and Map.of() with safety analysis
- **Task 3** - Sequenced Collections (Java 21): getFirst(), getLast(), and reversed() compared against legacy manual index access
- **Task 4** - Advanced Filtering and Type Inference: removeIf() for score-based filtering, var usage comparison, and final salary report

## Files
| File | Description |
|---|---|
| `Main.java` | Entry point — legacy switch menu integrating all tasks |
| `Employee.java` | Employee model with setter validation for salary and score ranges |
| `TalentManager.java` | Task 1 — ArrayList and HashMap management with CRUD operations |
| `ConfigData.java` | Task 2 — Immutable collections using List.of() and Map.of() |
| `SequencedDemo.java` | Task 3 — Legacy vs Java 21 sequenced access comparison |
| `FilterReport.java` | Task 4 — removeIf filtering and average salary report with var |

## How to Run
```powershell
cd week3/user-story
javac *.java
java Main
```

## Java Version
Java 21+

## Gitflow Branch
`feature/week3-user-story`