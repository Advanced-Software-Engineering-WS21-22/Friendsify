FROM openjdk:11

COPY target/friendsify-person-service-0.0.1-SNAPSHOT.jar /spring-boot.jar
ENV POSTGRES_DB=host.docker.internal
ENV POSTGRES_pASS=host.docker.internal
ENV DB_PORT=host.docker.internal

EXPOSE 9001

ENTRYPOINT ["java","-jar","spring-boot.jar"]
