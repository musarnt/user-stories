# User Stories - Corporate Talent Hub

Java learning project developed at Riwi coding bootcamp.
Each week covers a user story, practical challenges, and research topics
based on the Moodle course content.

---

## Project Structure
```
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
```

---

## How to Run the Code

Each week contains plain Java files. No build tool required.

**Compile:**
```bash
cd weekX/user-story
javac FileName.java
```

**Run:**
```bash
java FileName
```

---

## Branching Strategy (Gitflow)

This project follows Gitflow conventions:

| Branch | Purpose |
|---|---|
| `main` | Final delivery — stable code only |
| `develop` | Integration branch — all completed weeks |
| `feature/weekX-user-story` | Active development branch per week |

**Workflow per week:**
```
develop → feature/weekX-user-story → develop → main (final delivery)
```

---

## Tech Stack

- Java 21 (Eclipse Temurin)
- No build tool (Week 1) — Maven introduced in later weeks

---

## Weekly Progress

| Week | Topic | Status |
|---|---|---|
| Week 1 | Architecture, primitives, Records, operator precedence | ✅ Done |
| Week 2 | | ⬜ Pending |
| Week 3 | | ⬜ Pending |
| Week 4 | | ⬜ Pending |
| Week 5 | | ⬜ Pending |