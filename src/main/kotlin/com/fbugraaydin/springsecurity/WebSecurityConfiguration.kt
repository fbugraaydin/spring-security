package com.fbugraaydin.springsecurity

import com.fbugraaydin.springsecurity.filters.JwtFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfiguration : WebSecurityConfigurerAdapter() {

    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private lateinit var userDetailService: UserDetailsService

    @Autowired
    private lateinit var jwtFilter: JwtFilter

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailService).passwordEncoder(getPasswordEncoder())
    }

    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/authenticate", "/").permitAll()
                .antMatchers("/admin").hasAuthority(ADMIN)
                .antMatchers("/user").hasAnyAuthority(ADMIN, DEFAULT)
                .anyRequest().authenticated()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
    }

    @Bean
    fun getPasswordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    @Throws(Exception::class)
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

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