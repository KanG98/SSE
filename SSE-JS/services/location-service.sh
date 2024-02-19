#!/bin/bash

# Create a Docker network
docker network create my-network

# Build and run the Redis container
docker build -t redis:latest -f ./Dockerfile-redis . && docker run --network my-network --name my-redis -d redis

# Build and run the Redis container
docker build -t location-service:node -f ./Dockerfile-location-service . && docker run --network my-network --name location-service -p 9090:9090 -d location-service:node