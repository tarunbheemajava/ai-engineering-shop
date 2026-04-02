# AI Engineering Shop

A reference implementation for an AI-assisted software engineering workflow using the **BMAD Method** with **Claude Code**.

## Tech Stack

| Layer | Technology |
|-------|-----------|
| Language | Java 17 (LTS) |
| Framework | Spring Boot 3.4.4 |
| Build | Maven 3.9.x (via Maven Wrapper) |
| Linting | Checkstyle |
| Testing | JUnit 5, MockMvc |
| CI/CD | GitHub Actions |
| AI Workflow | BMAD Method (bmm module) |

## Project Structure

```
ai-engineering-shop/
├── src/
│   ├── main/java/com/aiengineering/shop/
│   │   ├── Application.java              # Spring Boot entry point
│   │   └── controller/
│   │       └── HealthController.java      # GET /api/health
│   └── main/resources/
│       └── application.yml                # Server config (port 8080)
├── src/test/java/com/aiengineering/shop/
│   ├── ApplicationTests.java              # Context load test
│   └── controller/
│       └── HealthControllerTest.java      # Health endpoint test
├── .github/workflows/ci.yml              # CI pipeline
├── _bmad/                                 # BMAD methodology core
├── _bmad-output/
│   └── project-context.md                 # AI engineering contract
├── .claude/skills/                        # 44+ BMAD Claude Code skills
├── config/checkstyle/checkstyle.xml       # Checkstyle rules
└── pom.xml
```

## Quick Start

```bash
# Build
./mvnw clean package

# Run tests
./mvnw test

# Run the app
./mvnw spring-boot:run

# Lint
./mvnw checkstyle:check
```

The app starts on `http://localhost:8080`. Health check: `GET /api/health`.

## Available Endpoints

| Method | Path | Description |
|--------|------|-------------|
| GET | `/api/health` | Service health check (status, name, timestamp) |
| GET | `/actuator/health` | Spring Actuator health |
| GET | `/actuator/info` | Spring Actuator info |

## BMAD Workflow

This project follows the BMAD AI engineering shop pattern:

1. **Implement** a bounded change using `bmad-quick-dev` or `bmad-dev-story`
2. **Validate locally** — lint, build, tests
3. **BMAD review** — run `bmad-code-review` before PR
4. **Open PR** — branch naming: `feat/`, `fix/`, `chore/`, `refactor/`
5. **CI validates** — GitHub Actions must pass
6. **Human reviews and merges** — AI code is never auto-merged

See [_bmad-output/project-context.md](_bmad-output/project-context.md) for full rules.

## Current Status

### Done
- [x] Spring Boot project scaffolding
- [x] Maven build with wrapper
- [x] Checkstyle linting config
- [x] GitHub Actions CI pipeline (lint, build, test)
- [x] BMAD method installed (44+ skills)
- [x] Project context / AI engineering contract
- [x] First feature: `GET /api/health` with unit test

### Open PR
- `feat/health-check-endpoint` — first feature PR to validate CI pipeline

### Next Steps
- [ ] Merge first PR after CI goes green
- [ ] Enable branch protection on `main`
- [ ] Install `gh` CLI for PR creation from terminal
- [ ] Add more REST endpoints (domain features)
- [ ] Add integration tests
- [ ] Add security scan job to CI
- [ ] Add OpenAPI/Swagger documentation
- [ ] Set up Docker containerization

## Branch Policy

| Branch | Purpose |
|--------|---------|
| `main` | Protected. Deploy-ready code only. |
| `feat/*` | New features |
| `fix/*` | Bug fixes |
| `chore/*` | Maintenance |
| `refactor/*` | Code refactors |

## Contributing (AI-Assisted)

All AI-generated code must follow the rules in [project-context.md](_bmad-output/project-context.md):
- No direct commits to `main`
- Every PR must pass CI
- Human review required before merge
- Scoped changes only (one ticket/story per PR)
