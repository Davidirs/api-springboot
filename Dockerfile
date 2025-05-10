
FROM eclipse-temurin:17-jdk-alpine
# Instalar Maven
RUN apk update && apk add maven
VOLUME /tmp
# Ejecutar comando mvn package
RUN mvn package
# Copiar archivo JAR
COPY target/*.jar app.jar
# Establecer comando de ejecución
ENTRYPOINT ["java","-jar","/app.jar"]