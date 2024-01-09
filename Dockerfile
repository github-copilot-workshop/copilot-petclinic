# Use the official maven/Java 17 image to create a build artifact.
FROM maven:3.8.3-openjdk-17 as builder

# Set the working directory in the image to /app
WORKDIR /app

# Copy the pom.xml file to download dependencies
COPY pom.xml .

# Download dependencies as specified in pom.xml
RUN mvn dependency:go-offline -B

# Copy the rest of the working directory contents into the image
COPY src ./src

# Build a release artifact.
RUN mvn package -DskipTests

# Use OpenJDK 17 for the runtime stage
FROM openjdk:17-jdk-alpine

# Set the working directory in the image to /app
WORKDIR /app

# Copy the jar file from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose port 8080 for the application
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]