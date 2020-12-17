package com.fbugraaydin.springsecurity

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl : UserDetailsService{

    @Autowired
    private lateinit var userDetailRepository: UserDetailRepository

    override fun loadUserByUsername(username: String): UserDetails {
        val userName = userDetailRepository.findByUserName(username)
        return userName?.toDto() ?: throw UsernameNotFoundException("User not found: $username")
    }
}