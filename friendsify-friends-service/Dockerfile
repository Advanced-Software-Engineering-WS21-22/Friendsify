FROM openjdk:11

COPY target/friendsify-friends-service-0.0.1-SNAPSHOT.jar /spring-boot.jar
ENV POSTGRES_DB=host.docker.internal
ENV POSTGRES_PASS=host.docker.internal
ENV DB_PORT=host.docker.internal
ENV DB_HOST=host.docker.internal

EXPOSE 9003

ENTRYPOINT ["java","-jar","spring-boot.jar"]
