# AI Engineering Shop — Development Cycle

**Goal:** Define the repeatable process for every code change in this project.

This project uses the **BMAD Method** with Claude Code. Every change — whether made by AI or human — follows this cycle.

---

## The Cycle

```
Step A: Scope   →   Step B: Branch   →   Step C: Implement   →   Step D: Validate
                                                                        │
Step G: Merge   ←   Step F: CI Green  ←   Step E: PR + Review  ←───────┘
```

---

## Step A — Scope a Bounded Change

**Rule:** One ticket, one story, one defect, or one refactor target. Never "go improve the whole module."

Use one of:
- `bmad-quick-dev` — for bug fixes, narrow enhancements, local refactors, test additions
- `bmad-dev-story` — for larger story-based features with acceptance criteria

---

## Step B — Create a Feature Branch

Branch naming convention (from `_bmad-output/project-context.md`):

| Prefix | Use |
|--------|-----|
| `feat/<name>` | New features |
| `fix/<name>` | Bug fixes |
| `chore/<name>` | Maintenance, dependency updates |
| `refactor/<name>` | Code refactors |

```bash
git checkout main
git pull origin main
git checkout -b feat/<ticket>-short-name
```

---

## Step C — Implement

Follow these rules from `project-context.md`:

- Use constructor injection (not field `@Autowired`)
- DTOs as Java records where possible
- Use `Optional` for nullable return types
- SLF4J/Logback for logging (never `System.out.println`)
- All new business logic must include unit tests
- All REST endpoints must have proper HTTP status codes and error responses

---

## Step D — Validate Locally

**Before creating a PR, run all of these:**

```bash
# Lint
./mvnw checkstyle:check

# Build
./mvnw package -DskipTests

# Tests
./mvnw test
```

All must pass. No exceptions.

---

## Step E — BMAD Review + Open PR

### 1. Run BMAD code review

Use `bmad-code-review` skill. Human triages findings into:
- **Must fix** before PR
- **Follow-up ticket** for later
- **Ignore** (false positive)

### 2. Open PR with this template

```markdown
## Summary
What changed and why

## Scope
What is included / excluded

## Validation
- [ ] lint (`./mvnw checkstyle:check`)
- [ ] build (`./mvnw package`)
- [ ] unit tests (`./mvnw test`)
- [ ] integration tests (if applicable)

## Risk
Possible regressions

## Rollback
How to revert safely
```

---

## Step F — CI Must Pass

GitHub Actions pipeline (`.github/workflows/ci.yml`) runs:
1. Checkout + Setup Java 17
2. `chmod +x mvnw`
3. Checkstyle lint
4. Build
5. Unit tests
6. Upload test reports

**CI is the source of truth.** AI opinion is not quality. Passing pipeline + human approval is quality.

---

## Step G — Human Reviews and Merges

- AI-generated code must be human-reviewed before merge
- Only merge from green PRs
- Never deploy directly from AI-generated local changes
- Release flow: AI generates → human reviews → CI validates → merge

---

## Policies

1. **AI writes code, not standards** — standards live in project-context, lint rules, CI, branch protections
2. **Every AI change is bounded** — one ticket/story/defect per PR
3. **BMAD review is mandatory, but not final** — human judgment is final
4. **CI is the source of truth** — passing pipeline + human approval = quality
5. **Release from merge, not from chat** — no direct push to production
