FROM maven:3.8.6 AS build
ENV JAVA_HOME=/usr/lib/jvm/java-17
ENV MAVEN_HOME=/usr/share/maven

# Instalar Java 17
RUN apt-get update && apt-get install -y openjdk-17-jdk

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