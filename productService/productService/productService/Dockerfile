FROM eclipse-temurin:21-jre
# Set working directory inside the container
WORKDIR /app
# Copy the built Spring Boot jar into the image
COPY target/productService-0.0.1-SNAPSHOT.jar app.jar
# Expose the Port
EXPOSE 8082
# Start the spring boot app
ENTRYPOINT ["java", "-jar", "app.jar"]