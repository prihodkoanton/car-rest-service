FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=*.jar
COPY target/carrestservice-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
