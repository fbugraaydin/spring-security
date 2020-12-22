FROM alpine/git as clone
WORKDIR /app
RUN git clone https://github.com/fbugraaydin/spring-security.git

FROM gradle:6.7.1-jdk11 AS build
WORKDIR /app
COPY --from=clone /app/spring-security /app
RUN gradle build

FROM openjdk:12-jdk-alpine
WORKDIR /app
COPY --from=build /app/build/libs/spring-security.jar /app
EXPOSE 8080
CMD ["java","-jar","spring-security.jar"]