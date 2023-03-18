FROM openjdk:17-oracle

EXPOSE 8081

COPY target/springBootDemo-0.0.1-SNAPSHOT.jar prodapp.jar

CMD ["java", "-jar", "prodapp.jar"]