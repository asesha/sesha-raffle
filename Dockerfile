FROM openjdk:latest

EXPOSE 8080

ENV DB_URL=DB_URL DB_USERNAME=DB_USERNAME DB_PASSWORD=DB_PASSWORD FILE_UPLOAD_LIMIT=FILE_UPLOAD_LIMIT JWT_SECRET=JWT_SECRET

ARG JAR_FILE=target/*.jar

ADD ${JAR_FILE} raffle-api.jar

RUN mkdir uploads

COPY target/*.jar application.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/application.jar"]
