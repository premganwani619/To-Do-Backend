# Use a base image with Maven and OpenJDK 21
FROM maven:3.9.6-eclipse-temurin-21 AS builder

# Copy the application's source code into the container
COPY . .

# Build the application without running tests
RUN mvn clean package -DskipTests

# Use a smaller JDK image for the final image
FROM eclipse-temurin:21-jre-jammy

# Copy the built JAR file from the builder stage into the final image and rename it
COPY --from=builder /target/todo-app-0.0.1-SNAPSHOT todoapp.jar

# Command to run the application
CMD ["java", "-jar", "todoapp.jar"]
