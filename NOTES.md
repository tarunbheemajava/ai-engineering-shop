> Personal notes on what this project is, how it was built, and how to use it day-to-day.


**The AI Engineering Shop is a setup where AI (Claude) writes your code fast, but your existing tools (GitHub, CI) and your human judgment keep it safe.**


## The Simple Mental Model

```
You (Developer)
    │
    │ "build me a new API.."
    ▼
Claude
    │ writes code, tests, PR
    ▼
GitHub Actions (the quality gate)
    │ lint → build → test
    ▼
You again (the decision maker)
    │ review PR on GitHub
    ▼
Merge → Done
```

---

## Who Does What?

| Who | Role | New or Existing? |
|-----|------|-----------------|
| **You** | Tell Claude what to build, review PRs, click Merge | Existing  |
| **Claude** | Writes code, writes tests, commits, pushes, opens PRs | AI |
| **GitHub** | Stores code, shows PRs | Existing |
| **GitHub Actions (CI)** | Runs lint + build + tests automatically on every PR | Existing |
| **BMAD** | The rulebook that tells Claude how to behave | New |


---

## What Is BMAD? 

BMAD is a **rulebook for Claude**. Without it, Claude might:
- Change 50 files at once
- Skip writing tests
- Ignore your coding standards
- Try to push straight to production

With BMAD, Claude always:
1. Makes small, scoped changes (one feature at a time)
2. Follows project rules (coding style, naming, etc.)
3. Writes tests for every new feature
4. Gets reviewed before anything merges

The 44+ files in `.claude/skills/` are instruction manuals. When you type `/bmad-quick-dev`, Claude loads the right manual and follows it step by step.

---

## What You Need to Do (Your Daily Workflow)

**You stay in VS Code + Claude the whole time.**

```
┌─────────────────────────────────────────┐
│            VS Code + Claude             │
│                                         │
│  You type: "build me a xyz API"     │
│  Claude: writes the code                │
│  Claude: writes the tests               │
│  Claude: commits to a branch            │
│  Claude: pushes to GitHub               │
│  Claude: opens a PR                     │
│                                         │
│  ── happens automatically ──            │
│  GitHub Actions: runs lint/build/tests  │
│                                         │
│  ── back to you ──                      │
│  You: go to GitHub, click "Merge"       │
│  (or tell Claude to fix something)      │
└─────────────────────────────────────────┘
```

**You never need to:**
- Type git commands manually
- Write code yourself
- Remember Maven commands
- Write PR descriptions

**You only need to:**
- Talk to Claude in plain English
- Review the PR on GitHub
- Click Merge (or say "fix it")

---

## Useful Slash Commands (Type These in Claude Chat)

| Command | What it does |
|---------|-------------|
| `/bmad-quick-dev` | Claude builds a small feature following all the rules |
| `/bmad-code-review` | Claude reviews code like a senior developer |
| `/bmad-create-story` | Claude writes a detailed story spec before coding |
| `/bmad-dev-story` | Claude implements a story from a spec file |
| `/shop-status` | Claude tells you where the project stands |
| `/shop-onboarding` | Claude explains the whole project from scratch |
| `/shop-dev-cycle` | Claude explains the full development workflow |

> Tip: You don't have to use slash commands — just say what you want in plain English and Claude figures it out. The skills just make Claude more disciplined.


---

## The Full Dev Cycle (Step by Step)

| Step | Who | What |
|------|-----|------|
| A | You | Tell Claude what to build (keep it small — one feature) |
| B | Claude | Creates a feature branch (`feat/xxx`, `fix/xxx`) |
| C | Claude | Writes the code + unit tests |
| D | Claude | Runs `./mvnw test` locally — must be green |
| E | Claude | Runs `/bmad-code-review` — finds issues before PR |
| F | Claude | Opens PR with summary, scope, validation, risk, rollback |
| G | GitHub Actions | Automatically runs lint, build, tests |
| H | **You** | Review on GitHub — merge if happy, or tell Claude to fix |


---

## Branch Policy

| Branch | Use |
|--------|-----|
| `main` | Protected — only merged PRs with green CI |
| `feat/*` | New features |
| `fix/*` | Bug fixes |
| `chore/*` | Maintenance |
| `refactor/*` | Code refactors |

---

## Key Files to Know

| File | What it is |
|------|-----------|
| `README.md` | Project overview |
| `_bmad-output/project-context.md` | The AI rulebook — coding standards, commands, "never do" list |
| `.github/workflows/ci.yml` | CI pipeline definition |
| `pom.xml` | Maven build config |
| `.claude/skills/` | 44+ BMAD instruction manuals for Claude |

