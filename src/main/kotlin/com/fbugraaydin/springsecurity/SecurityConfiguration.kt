package com.fbugraaydin.springsecurity

import com.fbugraaydin.springsecurity.filters.JwtFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.BeanIds
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfiguration : WebSecurityConfigurerAdapter() {

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private lateinit var userDetailService: UserDetailsService

    @Autowired
    private lateinit var jwtFilter: JwtFilter

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailService)
    }

    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin").hasRole(ADMIN)
                .antMatchers("/user").hasAnyRole(ADMIN, DEFAULT)
                .antMatchers("/create-token","/").permitAll()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
    }

    @Bean
    fun getPasswordEncoder(): PasswordEncoder = NoOpPasswordEncoder.getInstance()

    @Bean(name = [BeanIds.AUTHENTICATION_MANAGER])
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    @Bean
    fun databaseInitializer(userRepository: UserDetailRepository, roleRepository: RoleRepository) = ApplicationRunner{
        val user1 = userRepository.save(UserEntity(username = "User Admin", password = "pass1", isActive = true))
        val user2 = userRepository.save(UserEntity(username = "Default User", password = "pass2", isActive = true))

        roleRepository.save(RoleEntity(role = ADMIN, user = user1))
        roleRepository.save(RoleEntity(role = DEFAULT, user = user2))
    }

}