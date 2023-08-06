FROM openjdk:17
WORKDIR /app
COPY target/solr-0.0.1-SNAPSHOT.jar ./solr.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "solr.jar"]
