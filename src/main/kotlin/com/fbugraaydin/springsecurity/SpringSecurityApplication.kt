package com.fbugraaydin.springsecurity

import com.fbugraaydin.springsecurity.util.ADMIN
import com.fbugraaydin.springsecurity.util.DEFAULT
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootApplication
class SpringSecurityApplication{
	@Bean
	fun databaseInitializer(userRepository: UserDetailRepository, roleRepository: RoleRepository) = ApplicationRunner {
		val encoder = BCryptPasswordEncoder()

		val adminRole = roleRepository.save(RoleEntity(role = ADMIN))
		val defaultRole = roleRepository.save(RoleEntity(role = DEFAULT))

		userRepository.save(UserEntity(username = "admin", password = encoder.encode("pass"), isActive = true, roles = mutableListOf(adminRole)))
		userRepository.save(UserEntity(username = "user1", password = encoder.encode("pass1"), isActive = true, roles = mutableListOf(defaultRole)))
		userRepository.save(UserEntity(username = "user2", password = encoder.encode("pass2"), isActive = true, roles = mutableListOf(defaultRole)))

	}
}

fun main(args: Array<String>) {
	runApplication<SpringSecurityApplication>(*args)
}
