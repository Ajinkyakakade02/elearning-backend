# Dockerfile for Spring Boot Application
FROM FROM openjdk:17-alpine

# Set working directory
WORKDIR /app

# Copy the jar file
COPY target/elearning-backend-1.0.0.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the application
# Use environment variables for configuration
ENTRYPOINT ["java", "-jar", "app.jar"]