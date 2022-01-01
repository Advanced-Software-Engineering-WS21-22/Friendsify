FROM openjdk:11

COPY target/friendsify-email-service-1.0-SNAPSHOT.jar /spring-boot.jar
ENV POSTGRES_DB=host.docker.internal
ENV POSTGRES_pASS=host.docker.internal

EXPOSE 9002

ENTRYPOINT ["java","-jar","spring-boot.jar"]
