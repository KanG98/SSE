## How to deploy spring boot application with docker, and continuous deployment using Github Action and WebHooks

1. Add github action to repo
   ```
   name: Main - Testing
   on:
    push:
    branches:
    - 'main'

    jobs:
    artifact:
    name: Test main branch - GitHub Packages
    runs-on: ubuntu-latest
    steps:
    - uses: actions/checkout@v1
    - name: Set up JDK 17
      uses: actions/setup-java@v1
      with:
      java-version: 17.0.1
    - name: Gradle assemble
      run: ./gradlew clean assemble
    - name: Gradle check
      run: ./gradlew clean check
    ```
2. Install docker in server
    ```
    sudo apt-get update
    sudo apt-get remove docker docker-engine docker.io
    sudo apt install docker.io
    sudo systemctl start docker
    sudo systemctl enable docker
   ```

3. Create Dockerfile
    ```
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
   ```
   
4. Build and push to docker hub repo
    ```
   sudo docker login -u myusername -p mypassword 
   // remove ~/.docker/config.json if necessary
   sudo docker login -u myusername -p mypassword
   sudo docker build -t yankang198/sse_product_service_dev . --platform linux/amd64    sudo docker push example/my-repository
   sudo docker push yankang198/sse_product_service_dev
   ```
5. In the server
    ```
   sudo docker run -d --name=sse_product_service_dev yankang198/sse_product_service_dev:latest -p 50121:50121
   sudo docker pull yankang198/sse_product_service_dev:latest
   sudo docker run -p 50121:50121 yankang198/sse_product_service_dev:latest
   sudo docker container ls -a
   ```
    
