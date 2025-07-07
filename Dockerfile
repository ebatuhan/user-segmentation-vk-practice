FROM eclipse-temurin:21-jdk-jammy

# Copy fat jar from target/
COPY target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]
