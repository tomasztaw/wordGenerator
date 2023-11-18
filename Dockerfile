FROM openjdk:17
WORKDIR /app
#COPY target/*.jar wordGenerator.jar
COPY target/*.jar wordGenerator-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "wordGenerator.jar"]
