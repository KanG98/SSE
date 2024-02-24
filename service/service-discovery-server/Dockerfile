# Use the official maven/Java 8 image to create a build artifact.
# https://hub.docker.com/_/maven
FROM maven:3.8.4-openjdk-17-slim AS build

# Set the working directory in the image to /app
WORKDIR /app

# Copy the pom.xml file to our app directory
COPY ./service-discovery-server/pom.xml ./pom.xml

# Build all dependencies for offline use
RUN mvn dependency:go-offline -B

# Copy the rest of the application
COPY ./service-discovery-server/src ./src

# Package the application
RUN mvn package -DskipTests

# Use OpenJDK for runtime
# https://hub.docker.com/_/openjdk
# Consider using the official openjdk:17-jdk-alpine image if you need a smaller footprint
FROM openjdk:17-jdk-alpine

# Set the working directory in the image to /app
WORKDIR /app

# Copy the jar to our app directory
COPY --from=build /app/target/*.jar ./service-discovery-server.jar

# Run the application
CMD ["java", "-jar", "./service-discovery-server.jar"]
