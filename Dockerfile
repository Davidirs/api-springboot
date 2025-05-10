FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
RUN mvn package
COPY target/api-asistencia-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"] 