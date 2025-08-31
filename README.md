# community-residents-management-system
A Spring Boot Practise Project

### Project Overview: Residents Management System (RMS)
The RMS is a CRUD-based web application built with Spring Boot for managing residents in Malawi's hierarchical structure (communities → townships → cities/districts). It allows lower-level users (e.g., community leaders) to register/update residents, while higher-level users (e.g., district officials) access aggregated stats for development planning. The system will use Spring Boot for the backend, JPA/Hibernate for database interactions (e.g., PostgreSQL or H2 for dev), Spring Security for roles, and REST APIs for frontend integration.

Key Technologies:
- Backend: Spring Boot 3.x, Spring Data JPA, Spring Security.
- Frontend: Reactjs
- Database: PostgreSQL.
- Testing: JUnit, Mockito.
- Deployment: Docker for containerization.

Project Goals:
- Provide secure, role-based access.
- Enable hierarchical data visibility.
- Support stats like population counts, demographics.
- Ensure scalability for Malawi's governance structure.

### User Stories
Format: As a [user], I want [feature] so that [benefit].

1. **As a community leader**, I want to register a new resident with details like name, national ID, age, gender, community, township, and district, so that the resident is added to the local database and visible at higher levels.
   
2. **As a community leader**, I want to update or delete a resident's details, so that changes (e.g., address moves or errors) are reflected accurately without affecting higher-level aggregates.

3. **As a township official**, I want to view a list of all residents in my township, including search by community or name, so that I can monitor local population trends.

4. **As a district official**, I want to generate reports on aggregated stats (e.g., total residents per township, average age, gender distribution), so that I can inform development planning like resource allocation.

5. **As an administrator**, I want to manage user roles and permissions (e.g., assign 'COMMUNITY_LEADER' or 'DISTRICT_OFFICIAL'), so that access is controlled securely.

6. **As any authenticated user**, I want to log in securely with username/password, so that unauthorized access is prevented.

7. **As a district official**, I want to export reports in CSV/PDF format, so that I can share data with government stakeholders.

8. **As a system user**, I want input validation and error handling (e.g., unique national ID), so that data integrity is maintained.


### Modules and Definitions of Done (DoD)
The system is divided into modules for modular development. Each module has a **Definition of Done (DoD)**: criteria that must be met before considering it complete. This includes implementation, testing, documentation, and integration.

#### 1. **Authentication and Authorization Module**
   - **Description**: Handles user login, roles (e.g., COMMUNITY_LEADER, TOWNSHIP_OFFICIAL, DISTRICT_OFFICIAL, ADMIN), and security (JWT or session-based).
   - **Key Components**: User entity, Spring Security config, login/register endpoints.
   - **DoD**:
     - User registration and login APIs implemented and tested (200 OK for success, 401 for invalid credentials).
     - Role-based access enforced (e.g., @PreAuthorize annotations).
     - Passwords hashed (BCrypt).
     - Unit tests cover 80%+ code (happy path, edge cases like invalid login).
     - Integration tests with mock users.
     - Documentation: API docs (Swagger/OpenAPI) for auth endpoints.
     - No security vulnerabilities (e.g., scanned with OWASP tools if available).
     - Integrated with other modules (e.g., protects CRUD endpoints).

#### 2. **Resident Management Module (CRUD)**
   - **Description**: Core CRUD operations for residents, including validation (e.g., unique national ID).
   - **Key Components**: Resident entity, Repository, Service, Controller.
   - **DoD**:
     - CRUD endpoints implemented: POST /residents (create), GET /residents/{id} (read), PUT /residents/{id} (update), DELETE /residents/{id} (delete).
     - Validation: Fields like national ID unique, age > 0.
     - Hierarchical filters: e.g., GET /residents?community=XYZ.
     - Unit tests: Cover all operations, including failures (e.g., duplicate ID → 400 Bad Request).
     - Integration tests: End-to-end with database.
     - Documentation: API specs with examples.
     - Performance: Handles 1,000+ records without issues.
     - Integrated with auth: Only authorized roles can perform actions.

#### 3. **Location Management Module**
   - **Description**: Manages hierarchical locations (communities, townships, districts) as entities or enums, ensuring residents are linked correctly.
   - **Key Components**: Location entities.
   - **DoD**:
     - Endpoints for listing/adding locations if needed (e.g., GET /locations/districts).
     - Residents linked to locations via foreign keys or fields.
     - Data seeding script for initial Malawi districts/townships.
     - Unit tests: Validate location hierarchies (e.g., community belongs to township).
     - Integration: Residents CRUD uses location data.
     - Documentation: Schema diagram and API docs.
     - No orphaned data: Deleting a location cascades appropriately (or prevents if residents exist).

#### 4. **Reporting and Analytics Module**
   - **Description**: Aggregates data for stats, with queries for counts, demographics.
   - **Key Components**: Custom JPA queries or Spring Data methods for aggregates (e.g., count by district).
   - **DoD**:
     - Endpoints: GET /reports/district/{district} (returns JSON with stats like total residents, gender split).
     - Export functionality: GET /reports/export?format=csv.
     - Handles large datasets (e.g., pagination for lists).
     - Unit tests: Mock data for stats calculations.
     - Integration tests: Verify aggregates match database.
     - Documentation: Report formats and examples.
     - Secured: Only higher roles access.
     - Accuracy: Stats recalculated on CRUD changes (e.g., via triggers or services).

#### 5. **Database and Infrastructure Module**
   - **Description**: Sets up persistence, migrations, and deployment.
   - **Key Components**: application.properties, Flyway/Liquibase for migrations, Dockerfiles.
   - **DoD**:
     - Database schema defined (entities mapped correctly).
     - Migrations scripted and tested.
     - Dev/prod profiles (H2 for dev, PostgreSQL for prod).
     - Docker container builds and runs without errors.
     - Backup/restore scripts if needed.
     - Tests: Database integration tests pass.
     - Documentation: Setup guide (e.g., how to run locally).

#### 6. **Testing and CI/CD Module**
   - **Description**: Ensures quality through automated tests and pipelines.
   - **Key Components**: JUnit tests, GitHub Actions or Jenkins for CI.
   - **DoD**:
     - Overall code coverage > 80%.
     - All unit, integration, and end-to-end tests pass.
     - CI pipeline configured (build, test on push).
     - No critical bugs in manual QA.
     - Documentation: Test plan and results.

### Project-Wide Definition of Done (DoD)
The entire project is considered complete when:
- All modules are implemented, integrated, and tested as per their individual DoDs.
- System runs end-to-end: e.g., register user → login → CRUD resident → view report.
- Performance: Handles 10,000+ residents with < 2s response times.
- Security: Audited for common vulnerabilities (e.g., SQL injection, XSS).
- Documentation: Full README with setup, architecture diagram, API docs (Swagger), and user guide.
- Deployment: Deployable to a server (e.g., via Docker Compose), with environment variables for configs.
- Acceptance Testing: User stories validated (e.g., manual demo simulating roles).
- No open issues: All bugs fixed, code reviewed.
- Version 1.0 tagged in Git.
