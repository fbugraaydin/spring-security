package com.fbugraaydin.springsecurity.controllers

import com.fbugraaydin.springsecurity.AuthRequest
import com.fbugraaydin.springsecurity.AuthResponse
import com.fbugraaydin.springsecurity.JwtTokenUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import kotlin.jvm.Throws

@RestController
class AuthenticationController {

    @Autowired
    private val jwtTokenUtil: JwtTokenUtil? = null

    @Autowired
    private val authenticationManager: AuthenticationManager? = null

    @Autowired
    private val userDetailsService: UserDetailsService? = null


    @PostMapping("/create-token")
    @Throws(AuthenticationException::class)
    fun createToken(@RequestBody authRequest: AuthRequest):AuthResponse {

        val authentication = authenticationManager!!.authenticate(UsernamePasswordAuthenticationToken(authRequest.username, authRequest.password))
        SecurityContextHolder.getContext().authentication = authentication

        val userDetail = userDetailsService!!.loadUserByUsername(authRequest.username)
        return AuthResponse(jwtTokenUtil!!.generateToken(userDetail))
    }
}