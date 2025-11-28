# note-management-system-282274-282127

Spring Boot Notes backend exposing CRUD REST API with in-memory H2 database.

How to run:
- In the notes_backend folder, run: ./gradlew bootRun
- The app listens on the preview port configured by the environment (default embedded server port). In the Kavia preview environment, use the provided URL.

API docs:
- Swagger UI: /swagger-ui.html (configured via springdoc)
- OpenAPI JSON: /api-docs (configured via springdoc)

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
