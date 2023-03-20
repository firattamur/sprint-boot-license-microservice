# Java runtime base image
FROM openjdk:17-jdk-slim

# Maintainer info
LABEL maintainer="firattamur <ftamur16@ku.edu.tr>"

# Application JAR file
ARG JAR_FILE

# Add the application's JAR to the container
COPY ${JAR_FILE} app.jar

# Run the JAR file
ENTRYPOINT ["java","-jar","/app.jar"]