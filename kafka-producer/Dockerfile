FROM openjdk:11.0.12-jre-buster
RUN apt update && apt install -y curl && rm -rf /var/lib/apt/lists/*
VOLUME /tmp
ARG JAR_FILE=build/libs/producer-kafka.jar
COPY ${JAR_FILE} producer-kafka.jar
ENTRYPOINT ["java","-jar","producer-kafka.jar"]