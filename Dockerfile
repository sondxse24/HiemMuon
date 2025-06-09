FROM openjdk:21-jdk-slim

EXPOSE 8080

COPY target/Hiem-Muon.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]