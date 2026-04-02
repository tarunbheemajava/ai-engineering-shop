# AI Engineering Shop — Onboarding Guide

**Goal:** Get any new contributor (human or AI) fully oriented on this project.

---

## What Is This Project?

A **reference implementation** for running an AI-assisted software engineering workflow using:

- **BMAD Method** — a structured methodology that gives AI agents guardrails, review processes, and bounded scope
- **Claude Code** — AI coding assistant with 44+ BMAD skills installed
- **Spring Boot** — the actual application being developed
- **GitHub Actions** — CI pipeline that objectively validates every change

The core idea: **AI writes code, but humans own standards, quality, and merge decisions.**

---

## Architecture Decisions Made

| Decision | Choice | Why |
|----------|--------|-----|
| Build tool | Maven (was Gradle) | Gradle had wrapper issues locally; Maven works out of the box |
| Java version | 17 (not 21) | Only JDK 17 available on dev machine; Spring Boot 3.4 supports both |
| Linting | Checkstyle | Declared in build, configured in `config/checkstyle/checkstyle.xml` |
| CI | GitHub Actions | Standard, free for public repos, runs on PR + push to main/develop |
| AI workflow | BMAD bmm module | Installed via `npx bmad-method install`, generates Claude Code skills |
| Branch policy | `feat/`, `fix/`, `chore/`, `refactor/` | Standard convention, enforced by team discipline |

---

## Project History (Commit Log)

| Commit | What |
|--------|------|
| `5c59163` | Initial scaffolding — Spring Boot app, BMAD install, CI, project-context |
| `209ef8f` | Switch from Gradle to Maven, add Checkstyle config, update CI and project-context |
| `c1b6204` | First feature — `GET /api/health` endpoint with MockMvc unit test |
| `ac0ebe3` | Add README with project status and next steps |
| `bef5298` | Fix CI — add `chmod +x mvnw` for Linux runners |

---

## Key Files to Read First

| File | Purpose |
|------|---------|
| `README.md` | Project overview, quick start, current status |
| `_bmad-output/project-context.md` | Tech stack, build commands, coding rules, "never do" list |
| `.github/workflows/ci.yml` | CI pipeline definition |
| `pom.xml` | Dependencies and build plugins |
| `src/main/resources/application.yml` | Server config (port 8080, actuator endpoints) |

---

## How to Start Contributing

### Prerequisites
- Java 17+
- Git
- GitHub account with SSH key configured

### First run
```bash
git clone git@github.com:tarunbheemajava/ai-engineering-shop.git
cd ai-engineering-shop
./mvnw clean package    # build + test
./mvnw spring-boot:run  # start on localhost:8080
```

### Making changes
Always follow the `shop-dev-cycle` skill workflow:
1. Scope a bounded change
2. Create a feature branch
3. Implement + write tests
4. Validate locally (`./mvnw test`)
5. Run `bmad-code-review`
6. Open PR with summary/scope/validation/risk/rollback
7. Wait for CI green + human approval
8. Merge

---

## Available Custom Skills

| Skill | When to use |
|-------|-------------|
| `shop-status` | "Where are we? What's done? What's next?" |
| `shop-dev-cycle` | "How does our process work? What's the workflow?" |
| `shop-onboarding` | "Explain this project from scratch" (this skill) |
| `bmad-quick-dev` | Implement a small bounded change |
| `bmad-dev-story` | Implement a story-based feature |
| `bmad-code-review` | Review code before PR |
