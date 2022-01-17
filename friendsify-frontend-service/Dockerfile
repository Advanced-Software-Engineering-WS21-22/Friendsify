FROM openjdk:11

COPY target/friendsify-frontend-service-0.0.1-SNAPSHOT.jar /spring-boot.jar
#ENV EMAIL_HOST=host.docker.internal
#ENV PERSON_HOST=host.docker.internal
#ENV FRIENDS_HOST=host.docker.internal
#ENV JOKE_HOST=host.docker.internal
#ENV BIRTHDAY_HOST=host.docker.internal
#ENV RECOMMENDATIONS_HOST=host.docker.internal

EXPOSE 9000

ENTRYPOINT ["java","-jar","spring-boot.jar"]
