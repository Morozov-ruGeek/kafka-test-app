FROM openjdk:11.0.12-jre-buster
RUN apt update && apt install -y curl && rm -rf /var/lib/apt/lists/*
VOLUME /tmp
ARG JAR_FILE=build/libs/consumer-kafka.jar
COPY ${JAR_FILE} consumer-kafka.jar
ENTRYPOINT ["java","-jar","consumer-kafka.jar"]