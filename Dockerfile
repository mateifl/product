# Use official Eclipse Temurin JDK 21 image
FROM eclipse-temurin:21-jre

# Set working directory inside the container
WORKDIR /app

# Copy the Spring Boot jar into the container
COPY target/product-0.0.1.jar app.jar

# Expose the port your service runs on
EXPOSE 8081

# Run the jar
ENTRYPOINT ["java","-jar","app.jar"]