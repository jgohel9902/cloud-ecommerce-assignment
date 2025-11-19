FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy jar from target folder
COPY target/*.jar app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar"]
