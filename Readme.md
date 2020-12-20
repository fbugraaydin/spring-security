## Spring Security with JWT by using Kotlin
This project is a restful backend project that authenticate and authorize users as their have different roles by using JWT standard.  

## Tech / Framework
- [Spring](https://spring.io/)
- [Kotlin](https://kotlinlang.org/)
- [Gradle](https://gradle.org/)

## Prerequisites
- Used in-memory-db h2 to store default users that inserted while application runs(in SpringSecurityApplication)
- Import postman collection **postman-collection.json** from project root directory.
- Run application on port :8080 or must change postman's port too.

## Features
- Generate JWT token by a username & password
- Authenticate user
- Authorize user by roles as below:

|    #    | "/" | "/authenticate" | "/admin" | "/user" |
|:-------:|:---:|:---------------:|:--------:|:-------:|
|  ADMIN  |  ✓  |        -        |     ✓    |    ✓    |
| DEFAULT |  ✓  |        -        |     x    |    ✓    |

## Licence
Developed by © [Fuat Buğra AYDIN](https://www.linkedin.com/in/fuatbugraaydin/)