FROM openjdk:8
RUN apt-get update
RUN apt-get install -y maven
EXPOSE 8090
