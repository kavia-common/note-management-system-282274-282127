# note-management-system-282274-282127

Spring Boot Notes backend exposing CRUD REST API with in-memory H2 database.

How to run:
- In the notes_backend folder, run: ./gradlew bootRun
- The app listens on the preview port configured by the environment (default embedded server port). In the Kavia preview environment, use the provided URL.

API docs:
- Docs redirect: /docs (redirects to Swagger UI)
- Swagger UI: /swagger-ui.html (configured via springdoc)
- OpenAPI JSON: /api-docs (configured via springdoc, also available at /v3/api-docs)
  Tip: If a tool expects the default path, try /v3/api-docs to compare responses. The JSON must include the top-level field "openapi": "3.x.y".

Health & Diagnostics:
- GET /health -> Returns {"status":"UP", "database": {...}} - Always returns 200 OK even if DB check fails
  - Use this endpoint for basic health monitoring and to verify the application is running
  - The database field includes optional DB connectivity info but won't fail the endpoint
  - Logs detailed diagnostics at INFO/WARN level for DB connection status

Diagnostics Logging:
- API docs requests (/api-docs, /v3/api-docs) are logged with detailed structured information
- Check application logs for entries prefixed with:
  - API_DOCS_REQUEST_START: Request initiated
  - API_DOCS_REQUEST_SUCCESS: Successful completion with timing
  - API_DOCS_REQUEST_FAILURE: HTTP 4xx/5xx responses with details
  - API_DOCS_REQUEST_ERROR: Exceptions during request processing
  - API_DOCS_EXCEPTION: Detailed exception logging with full stack traces
- Logs include: path, method, status, duration_ms, exception_type, exception_message, stack_trace, remote_addr, user_agent
- MDC fields available: path, method, remote_addr, user_agent, exception_type

Endpoints:
- POST /api/notes -> create note (body: { "title": "t", "content": "c" })
- GET /api/notes -> list notes (optional ?page=&size=)
- GET /api/notes/{id} -> get note
- PUT /api/notes/{id} -> update note
- DELETE /api/notes/{id} -> delete

Validation:
- Title is required and must not be blank. 400 is returned with fieldErrors map.
- Unknown ID returns 404.

H2:
- H2 console enabled at /h2-console (dev). Schema auto-generated via JPA (ddl-auto=create-drop).
- In-memory URL: jdbc:h2:mem:notesdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE to prevent shutdown warnings and ensure DB stays alive during app lifecycle.
