# Use the official Maven image for the build stage
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app

# Copy the Maven project files
COPY pom.xml .
COPY src ./src

# Package the application, skipping tests
RUN mvn clean package -DskipTests

# Use the official OpenJDK slim image for the runtime stage
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the packaged JAR file from the build stage
COPY --from=build /app/target/SongsLibrary-0.0.1-SNAPSHOT.jar ./SongsLibrary.jar

# Expose the port the application will run on (if needed)
EXPOSE 8080

# Define the command to run the application
ENTRYPOINT ["java", "-jar", "SongsLibrary.jar"]