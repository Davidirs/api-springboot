FROM eclipse-temurin:17-jdk-alpine

# Copiar archivo JAR desde GitHub
COPY https://github.com/Davidirs/api-springboot/raw/main/target/api-asistencia-0.0.1-SNAPSHOT.jar /app.jar

# Establecer comando de ejecución
ENTRYPOINT ["java","-jar","/app.jar"]