package com.fbugraaydin.springsecurity.service

import com.fbugraaydin.springsecurity.UserDetailRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl : UserDetailsService {

    @Autowired
    private lateinit var userDetailRepository: UserDetailRepository

    override fun loadUserByUsername(username: String): UserDetails {
        val userName = userDetailRepository.findByUsername(username)
        return userName?.toDto() ?: throw UsernameNotFoundException("User not found: $username")
    }

}