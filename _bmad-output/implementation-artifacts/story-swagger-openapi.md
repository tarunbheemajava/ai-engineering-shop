# Story: Add Swagger/OpenAPI Documentation

Status: in-review

## Story

As a developer or API consumer,
I want interactive API documentation via Swagger UI and a machine-readable OpenAPI 3.0 spec,
so that I can discover, understand, and test the AI Engineering Shop REST endpoints without reading source code.

## Acceptance Criteria

1. Swagger UI is accessible at `/swagger-ui.html` and renders all documented endpoints
2. OpenAPI 3.0 JSON specification is available at `/api-docs`
3. All existing endpoints (health, products CRUD) are documented with tags, summaries, and parameter descriptions
4. Build (`./mvnw package`), tests (`./mvnw test`), and lint (`./mvnw checkstyle:check`) all pass
5. No existing functionality is broken by the addition of Swagger/OpenAPI support

## Endpoints Documented

| Method | Path | Tag | Summary |
|--------|------|-----|---------|
| GET | `/api/health` | Health | Check service health |
| GET | `/api/products` | Products | List all products |
| GET | `/api/products/{id}` | Products | Get product by ID |
| POST | `/api/products` | Products | Create a product |
| DELETE | `/api/products/{id}` | Products | Delete a product |

## Tasks / Subtasks

- [x] Task 1: Add springdoc-openapi dependency (AC: #1, #2)
  - [x] Add `springdoc-openapi-starter-webmvc-ui:2.8.6` to `pom.xml`
- [x] Task 2: Create OpenAPI configuration (AC: #2)
  - [x] Create `OpenApiConfig.java` with API metadata (title, description, version, contact)
- [x] Task 3: Annotate controllers (AC: #3)
  - [x] Add `@Tag` and `@Operation` annotations to `HealthController`
  - [x] Add `@Tag`, `@Operation`, `@ApiResponse`, and `@Parameter` annotations to `ProductController`
- [x] Task 4: Configure springdoc paths (AC: #1, #2)
  - [x] Add `springdoc.swagger-ui.path` and `springdoc.api-docs.path` to `application.yml`
  - [x] Configure `operationsSorter: method` for consistent endpoint ordering
- [x] Task 5: Validate (AC: #4, #5)
  - [x] Build passes
  - [x] All tests pass
  - [x] Checkstyle passes
  - [x] Manual verification of Swagger UI and `/api-docs` endpoint

## Dev Notes

### Implementation Details

- **Dependency**: `org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.6` -- the Spring Boot 3.x compatible starter that bundles Swagger UI and the OpenAPI spec generator.
- **Configuration class**: `OpenApiConfig.java` in `com.aiengineering.shop.config` -- defines a `@Bean` of type `OpenAPI` with project-level metadata (title: "AI Engineering Shop API", version: "0.0.1-SNAPSHOT").
- **Controller annotations**: Used `@Tag` at class level for grouping, `@Operation` for endpoint summaries/descriptions, `@ApiResponse` for response code documentation, and `@Parameter` for path variable descriptions.
- **YAML config**: `springdoc.swagger-ui.path=/swagger-ui.html` and `springdoc.api-docs.path=/api-docs` set the access paths; `operationsSorter: method` sorts endpoints by HTTP method.

### Project Structure Notes

- New config package: `src/main/java/com/aiengineering/shop/config/OpenApiConfig.java`
- Modified controllers: `HealthController.java`, `ProductController.java` (annotation-only changes)
- Modified config: `src/main/resources/application.yml` (added `springdoc` section)
- Modified build: `pom.xml` (added springdoc dependency)

### Branch

`feat/swagger-openapi`

### References

- [Source: pom.xml] -- springdoc-openapi-starter-webmvc-ui 2.8.6 dependency
- [Source: src/main/java/com/aiengineering/shop/config/OpenApiConfig.java] -- API metadata bean
- [Source: src/main/java/com/aiengineering/shop/controller/HealthController.java] -- @Tag, @Operation annotations
- [Source: src/main/java/com/aiengineering/shop/controller/ProductController.java] -- @Tag, @Operation, @ApiResponse, @Parameter annotations
- [Source: src/main/resources/application.yml] -- springdoc path configuration
- [Source: _bmad-output/project-context.md#Critical Implementation Rules] -- "All API changes require updated OpenAPI/Swagger docs"

## Dev Agent Record

### Agent Model Used

Claude Opus 4.6

### Completion Notes List

- All five endpoints documented with OpenAPI annotations
- Swagger UI serves at /swagger-ui.html, OpenAPI JSON at /api-docs
- No test changes required -- annotations do not affect controller behavior
- Aligns with project-context.md rule: "All API changes require updated OpenAPI/Swagger docs"

### File List

- `pom.xml` (modified)
- `src/main/java/com/aiengineering/shop/config/OpenApiConfig.java` (new)
- `src/main/java/com/aiengineering/shop/controller/HealthController.java` (modified)
- `src/main/java/com/aiengineering/shop/controller/ProductController.java` (modified)
- `src/main/resources/application.yml` (modified)
