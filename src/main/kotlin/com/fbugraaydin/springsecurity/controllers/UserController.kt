package com.fbugraaydin.springsecurity.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    fun home(): ResponseEntity<String> {
        return ResponseEntity.ok("Welcome to homepage")
    }

    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    fun user(): ResponseEntity<String> {
        return ResponseEntity.ok("Welcome to User Page")
    }

    @GetMapping("/admin")
    @ResponseStatus(HttpStatus.OK)
    fun admin(): ResponseEntity<String> {
        return ResponseEntity.ok("Welcome to Admin Page")
    }
}