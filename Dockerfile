#FROM gradle:7.6-jdk17 AS builder
#WORKDIR /usr/src/app
#COPY src ./src
#COPY build.gradle .
#RUN ["gradle", "bootJar"]
#
#EXPOSE 8080
#
#FROM openjdk:17
#ARG JAR_FILE=build/libs/*.jar
#COPY --from=builder /usr/src/app/${JAR_FILE} app.jar
##RUN apt-get update; apt-get -y install curl
##RUN microdnf update \
##   && microdnf install curl \
##    && microdnf install jq
#ENTRYPOINT ["java", "-jar", "/app.jar"]

FROM gradle:7.6-jdk17 AS builder

WORKDIR /usr/src/app
COPY build.gradle .
COPY src ./src

RUN gradle bootJar --no-daemon

FROM eclipse-temurin:17-jdk

ARG JAR_FILE=build/libs/*.jar
COPY --from=builder /usr/src/app/${JAR_FILE} app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
