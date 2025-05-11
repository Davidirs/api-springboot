FROM maven:3.6.0-jdk-11 AS build

# Copiar código fuente
COPY src /app/src
COPY pom.xml /app/pom.xml

# Compilar proyecto
WORKDIR /app
RUN mvn package

# Crear imagen de Java
FROM eclipse-temurin:17-jdk-alpine

# Copiar archivo JAR compilado
COPY --from=build /app/target/api-asistencia-0.0.1-SNAPSHOT.jar /app.jar

# Establecer comando de ejecución
ENTRYPOINT ["java","-jar","/app.jar"]