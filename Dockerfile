FROM openjdk:11
ADD target/readinisgood.jar readingisgood.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","readingisgood.jar"]