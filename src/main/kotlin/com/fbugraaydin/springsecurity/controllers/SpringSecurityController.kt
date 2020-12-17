package com.fbugraaydin.springsecurity.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SpringSecurityController {

    @GetMapping("/")
    fun home(): String{
        return "<h3> Welcome to homepage</h3>"
    }

    @GetMapping("user")
    fun user():String{
        return "<h3> Welcome to General User Page </h3>"
    }

    @GetMapping("admin")
    fun admin():String{
        return "<h3> Welcome to Admin Page </h3>"
    }
}