FROM openjdk:12-jdk-alpine
COPY spring-security.jar spring-security.jar
CMD ["java","-jar","spring-security.jar"]