# Use an official OpenJDK 17 image
FROM eclipse-temurin:17-jdk

# Set the working directory
WORKDIR /app

# Copy the Maven project files to the container
COPY . .

# Install Maven
RUN apt update && apt install -y maven

# Verify Java and Maven versions
RUN java -version && mvn -version

# Package the application
RUN mvn clean package -DskipTests

# Set the entry point to run the application
CMD ["java", "-jar", "target/your-app.jar"]
