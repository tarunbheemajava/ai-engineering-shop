# Project Context — AI Engineering Shop

## Technology Stack & Versions

- Java 17 (LTS)
- Spring Boot 3.4.x
- Maven 3.9.x (with Maven Wrapper)
- JUnit 5 + Mockito for unit tests
- Spring Boot Test for integration tests
- Checkstyle for linting
- CI: GitHub Actions
- Container: Docker (optional)

## Package Manager

- Maven with Maven Wrapper (always use `./mvnw`)

## Build & Test Commands

```bash
# Build
./mvnw package

# Run tests
./mvnw test

# Lint (Checkstyle)
./mvnw checkstyle:check

# Run the application
./mvnw spring-boot:run

# Clean build
./mvnw clean package
```

## Branch Naming Convention

- `feat/<ticket>-short-name` — new features
- `fix/<ticket>-short-name` — bug fixes
- `chore/<ticket>-short-name` — maintenance, dependency updates
- `refactor/<ticket>-short-name` — code refactors

## PR Template Expectations

Every PR must include:
- **Summary**: What changed and why
- **Scope**: What is included / excluded
- **Validation**: Checklist (lint, tests, build)
- **Risk**: Possible regressions
- **Rollback**: How to revert safely

## Critical Implementation Rules

- No `@SuppressWarnings` without explicit approval and a comment explaining why
- All new business logic must include unit tests
- All API changes require updated OpenAPI/Swagger docs
- No direct commits to `main`
- Every PR must pass lint, build, and all tests
- AI-generated code must remain human-reviewed before merge
- Use constructor injection, not field injection (`@Autowired` on fields)
- Follow standard Java naming conventions (camelCase methods, PascalCase classes)
- DTOs must be Java records where possible
- Use `Optional` for nullable return types, never return `null` from public methods
- All REST endpoints must have proper HTTP status codes and error responses
- Database migrations via Flyway or Liquibase — never manual DDL

## Never Do

- Never push directly to `main`
- Never skip tests in CI
- Never commit secrets, API keys, or credentials
- Never use `System.out.println` for logging — use SLF4J/Logback
- Never deploy directly from AI-generated local changes
- Never expand scope beyond the assigned ticket/story
