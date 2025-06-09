# Stage 1: Build project với Maven
FROM maven:3.9.0-eclipse-temurin-21 AS build
WORKDIR /app

# Copy file pom và source code
COPY pom.xml .
COPY src ./src

# Build và tạo jar (bỏ qua test để nhanh hơn, có thể bỏ -DskipTests nếu muốn chạy test)
RUN mvn clean package -DskipTests

# Stage 2: Tạo image chạy jar từ openjdk slim
FROM openjdk:21-jdk-slim
WORKDIR /app

# Copy jar từ stage build sang
COPY --from=build /app/target/Hiem-Muon.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
