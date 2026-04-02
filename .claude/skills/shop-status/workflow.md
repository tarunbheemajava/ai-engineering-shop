# Shop Status Workflow

**Goal:** Give the user a clear picture of the project's current state, what has been completed, and what comes next.

## Steps

### 1. Gather Current State

Read these files to understand the project:

- `README.md` — project overview and next steps checklist
- `_bmad-output/project-context.md` — tech stack, rules, commands
- `.github/workflows/ci.yml` — CI pipeline configuration
- `pom.xml` — dependencies and build plugins

Run these commands:
- `git log --oneline -10` — recent commits
- `git branch -a` — branches
- `git status` — working tree state
- `./mvnw test -q` — verify tests still pass

### 2. Report

Present a summary covering:

| Section | What to report |
|---------|---------------|
| **Completed** | List everything that has been built and merged |
| **In Progress** | Open branches, open PRs |
| **Build Health** | Does `./mvnw test` pass? How many tests? |
| **Next Steps** | What should be tackled next from the README checklist |

### 3. Recommend

Based on the current state, suggest the single best next action — keeping scope bounded per the BMAD policy.
