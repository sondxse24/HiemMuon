# Giai đoạn build với Maven
FROM maven:3.9.6-eclipse-temurin-21 as build
WORKDIR /app

COPY . .
COPY src ./src

RUN mvn clean package -DskipTests

# Giai đoạn chạy app
FROM eclipse-temurin:21-jdk
WORKDIR /app

COPY --from=build /app/target/HiemMuon-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
