# Use OpenJDK 17 runtime image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the pre-built JAR file
COPY target/mvc-app-0.0.1-SNAPSHOT.jar app.jar

# Create directory for SQLite database
RUN mkdir -p /app/data

# Expose port 8080
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]