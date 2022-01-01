FROM openjdk:11

COPY target/friendsify-recommendations-service-1.0-SNAPSHOT.jar /spring-boot.jar
ENV POSTGRES_DB=host.docker.internal
ENV POSTGRES_pASS=host.docker.internal

EXPOSE 9006

ENTRYPOINT ["java","-jar","spring-boot.jar"]
