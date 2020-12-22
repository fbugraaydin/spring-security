## Spring Security with JWT by using Kotlin
This project is a restful backend project that authenticate and authorize users as their have different roles by using JWT standard.  

## Tech / Framework
- [Spring](https://spring.io/)
- [Kotlin](https://kotlinlang.org/)
- [Gradle](https://gradle.org/)

## Prerequisites
- Used in-memory-db h2 to store default users that inserted while application runs(in SpringSecurityApplication)
- Import postman collection **postman-collection.json** from project root directory.
- Application default port :8080 or must change postman's port too.
- *Or you don't need anything thanks to Docker :)  Just go to Let's Run section :)*

## Features
- Generate JWT token by a username & password
- Authenticate user
- Authorize user by roles as below:

|    #    | "/" | "/authenticate" | "/admin" | "/user" |
|:-------:|:---:|:---------------:|:--------:|:-------:|
|  ADMIN  |  ✓  |        -        |     ✓    |    ✓    |
| DEFAULT |  ✓  |        -        |     x    |    ✓    |

## Let's Run

Application is dockerized. Used 2 techniques:

1. Dockerize and run via Gradle by using Palantir plugin. It is used Dockerfile where located in root directory.

    1.1. Locate root directory of project and execute command to prepare docker image:
    ```
    ./gradlew docker
    ```
    1.2. Run docker image
    ```
    ./gradlew dockerRun
    ```

2. Dockerize project by getting current version. The docker image checks out project from GitHub.

    2.1. Pull docker image from DockerHub:
    ```
    docker pull fbugraaydin/spring-security-repo:0.0.1
    ```
    2.2. Run docker image
    ```
    docker run -p 8080:8080 --name spring-security-server spring-security
    ```
    
    **hint**: You can crate your own image from Dockerfile where located in dockerizeviagit/Dockerfile
    
Just send requests to http://localhost:8080/

## Licence
Developed by © [Fuat Buğra AYDIN](https://www.linkedin.com/in/fuatbugraaydin/)