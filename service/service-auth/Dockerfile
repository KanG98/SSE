# Use the official Gradle image to create a build artifact.
# https://hub.docker.com/_/gradle
FROM gradle:8.6.0-jdk17 AS build

# Set the working directory in the image to /app
WORKDIR /app

# Copy the build.gradle file to our app directory
COPY ./service/service-auth/build.gradle ./build.gradle

# Copy the rest of the application
COPY ./service/service-auth/src ./src

COPY ./data/auth ./data/auth

COPY ./foundation ./foundation

COPY ./service/service-auth/settings.gradle ./settings.gradle

# Build the application
RUN gradle clean build -x test

# Use eclipse-temurin for runtime
# https://hub.docker.com/_/eclipse-temurin
FROM eclipse-temurin:latest

# Set the working directory in the image to /app
WORKDIR /app

# Copy the jar to our app directory
COPY --from=build /app/build/libs/*.jar ./service-auth.jar

# Copy auth data to the volume

EXPOSE 50231

# Run the application
CMD ["java", "-jar", "./service-auth.jar"]
