FROM openjdk:8

MAINTAINER Jackson Ramalho "jacksoncarloslr@gmail.com"

EXPOSE 8081

WORKDIR /usr/local/bin/

COPY ./target/buscacep-0.0.1-SNAPSHOT.jar buscacep-restapi.jar

CMD ["java","-jar","buscacep-restapi.jar"]