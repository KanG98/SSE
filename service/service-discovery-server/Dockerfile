# Use the official Gradle image to create a build artifact.
# https://hub.docker.com/_/gradle
FROM gradle:7.4.2-jdk17 AS build

# Set the working directory in the image to /app
WORKDIR /app

# Copy the build.gradle file to our app directory
COPY ./build.gradle ./build.gradle

# Copy the rest of the application
COPY ./src ./src

# Build the application
RUN gradle build -x test

# Use OpenJDK for runtime
# https://hub.docker.com/_/openjdk
FROM openjdk:17-jdk-slim

# Set the working directory in the image to /app
WORKDIR /app

# Copy the jar to our app directory
COPY --from=build /app/build/libs/*.jar ./service-discovery-server.jar

# Run the application
CMD ["java", "-jar", "./service-discovery-server.jar"]