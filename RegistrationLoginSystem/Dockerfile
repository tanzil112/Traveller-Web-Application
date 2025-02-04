# Use an official OpenJDK runtime as the base image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the Maven project files (pom.xml and other necessary files)
COPY ./RegistrationLoginSystem/pom.xml ./ 

# Install Maven
RUN apt-get update && apt-get install -y maven

# Copy the rest of the application code (including source files)
COPY ./RegistrationLoginSystem/ ./ 

# Build the application
RUN mvn clean package -DskipTests

# Set the entry point to run the application
CMD ["java", "-jar", "target/RegistrationLoginSystem-0.0.1-SNAPSHOT.jar"]
