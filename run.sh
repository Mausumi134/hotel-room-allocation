#!/bin/bash

# Make mvnw executable (in case permissions are not set)
chmod +x ./mvnw

# Build the application
./mvnw clean package -DskipTests

# Start the application
java -jar target/room-allocation-1.0.0.jar