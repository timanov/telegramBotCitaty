# For Java 11
FROM adoptopenjdk/openjdk11:alpine-jre

# Refer to Maven build -> finalName
ARG JAR_FILE=target/telegramBotCitaty-1.0-SNAPSHOT.jar

# cd /opt/app
WORKDIR /opt/app

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]