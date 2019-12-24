FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/housescraper-0.0.1.jar target/app.jar
RUN touch target/app.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=docker","target/app.jar"]