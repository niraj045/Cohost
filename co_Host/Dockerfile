# Use official OpenJDK 17 runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven configuration files
COPY pom.xml .
COPY mvnw .
COPY mvnw.cmd .
COPY .mvn .mvn

# Copy the source code
COPY src src

# Give execution permissions to mvnw
RUN chmod +x mvnw

# Build the application (skip tests for faster build)
RUN ./mvnw clean package -DskipTests

# Expose the port the app runs on
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "target/co_Host-0.0.1-SNAPSHOT.jar"]