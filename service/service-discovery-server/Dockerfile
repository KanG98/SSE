# Use the official Gradle image to create a build artifact.
# https://hub.docker.com/_/gradle
FROM gradle:gradle:8.6.0-jdk17 AS build

# Set the working directory in the image to /app
WORKDIR /app

# Copy the build.gradle file to our app directory
COPY ./build.gradle ./build.gradle

# Copy the rest of the application
COPY ./src ./src

# Build the application
RUN gradle build -x test

# Use eclipse-temurin for runtime
# https://hub.docker.com/_/eclipse-temurin
FROM eclipse-temurin:17.0.10_7-jre-jammy

# Set the working directory in the image to /app
WORKDIR /app

# Copy the jar to our app directory
COPY --from=build /app/build/libs/*.jar ./service-discovery-server.jar

EXPOSE 8761

# Run the application
CMD ["java", "-jar", "./service-discovery-server.jar"]