# Use an official OpenJDK runtime as a base image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app
RUN apt-get update && \
    apt-get install -y maven && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*
RUN mkdir /app/football-standings
# Copy football-standingsapp.jar
COPY football-standings /app/football-standings/
RUN ls /app/football-standings 
# Expose the port your app runs on (optional, for documentation)
RUN cd /app/football-standings/ && mvn clean package -DskipTests
RUN ls /app/football-standings/target/ 
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/football-standings/target/football-standings-0.0.1-SNAPSHOT.jar"]

