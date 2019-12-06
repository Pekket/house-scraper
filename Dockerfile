FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} /app.jar
COPY run_app.sh /
RUN chmod 777 /run_app.sh
RUN apk update && apk add bash
ENTRYPOINT [ "sh", "-c", "/run_app.sh" ]