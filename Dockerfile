FROM openjdk:17
WORKDIR /app
COPY target/*.jar wordGenerator.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "wordGenerator.jar"]
