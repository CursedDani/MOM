#!/bin/bash

# Start Queue Management Service
if [ -f "queue-management-app/pom.xml" ]; then
    echo "Starting Queue Management Service..."
    mvn -f queue-management-app/pom.xml spring-boot:run &
else
    echo "Error: queue-management-app/pom.xml not found!"
fi

# Start Topic Management Service
if [ -f "topic-management-app/pom.xml" ]; then
    echo "Starting Topic Management Service..."
    mvn -f topic-management-app/pom.xml spring-boot:run &
else
    echo "Error: topic-management-app/pom.xml not found!"
fi