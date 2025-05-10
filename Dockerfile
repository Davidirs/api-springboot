
FROM eclipse-temurin:17-jdk-alpine
# Instalar Maven
RUN apt-get update && apt-get install -y maven
VOLUME /tmp
# Ejecutar comando mvn package
RUN mvn package
# Copiar archivo JAR
COPY target/*.jar app.jar
# Establecer comando de ejecución
ENTRYPOINT ["java","-jar","/app.jar"]