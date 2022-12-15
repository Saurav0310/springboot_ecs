FROM openjdk:8
RUN apt-get update
RUN apt-get install -y maven
WORKDIR /springbootapp
COPY . .
COPY entry.sh /entry.sh
RUN chmod 755 /entry.sh
RUN mvn clean install
EXPOSE 8090
ENTRYPOINT ["/bin/bash", "/entry.sh"]
