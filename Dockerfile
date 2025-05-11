FROM ubuntu:latest as build
# Instalar dependencias necesarias
run apt-get update && apt-get install -y maven
# Copiar el archivo pom.xml y el directorio src
COPY . .
# Compilar la aplicación
RUN ./mvnw clean package -DskipTests
# Crear una nueva imagen basada en OpenJDK 17
FROM openjdk:17-jdk-slim

# Exponer el puerto que utilizará la aplicación
EXPOSE 8080

# Copiar archivo JAR compilado
COPY --from=build /target/api-asistencia-0.0.1-SNAPSHOT.jar /app.jar

# Establecer comando de ejecución
ENTRYPOINT ["java","-jar","/app.jar"]