FROM gradle:jdk17 AS build
RUN mkdir -p /workspace
WORKDIR /workspace
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN ./gradlew build --no-daemon

FROM openjdk:17-jdk-slim
COPY --from=build  /home/gradle/src/service/service-product/build/libs/service-product-0.0.1-SNAPSHOT.jar /app/service-product-0.0.1-SNAPSHOT.jar
EXPOSE 50121
ENTRYPOINT ["java","-jar","/app/service-product-0.0.1-SNAPSHOT.jar"]