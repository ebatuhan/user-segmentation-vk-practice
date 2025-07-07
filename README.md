# User Segmentation Spring Boot Project for VK Education

---

## Start Application with Docker Compose

Make sure you have Java JDK 21, Docker & Docker Compose, Maven 3.6+ (or use the included mvnw wrapper)
To build images (if needed) and start both containers, run:

```bash
./mvnw clean package -DskipTests
docker compose up --build
```

App URL: http://localhost:8080

API documentation: http://localhost:8080/doc.html
