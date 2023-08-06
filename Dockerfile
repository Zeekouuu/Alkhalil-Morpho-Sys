FROM openjdk:17
WORKDIR /app
COPY target/tafssir-0.0.1-SNAPSHOT.jar ./tafssir.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "tafssir.jar"]
