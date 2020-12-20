package com.fbugraaydin.springsecurity.filters

import com.fbugraaydin.springsecurity.util.HEADER_STRING
import com.fbugraaydin.springsecurity.util.JwtTokenUtil
import com.fbugraaydin.springsecurity.util.TOKEN_PREFIX
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtFilter: OncePerRequestFilter() {

    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private val userDetailsService: UserDetailsService? = null

    @Autowired
    private val jwtTokenUtil: JwtTokenUtil? = null

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val authHeader = request.getHeader(HEADER_STRING)
        var username: String? = null
        var jwt: String? = null

        if (authHeader != null && authHeader.startsWith(TOKEN_PREFIX)) {
            jwt = authHeader.substring(7)
            username = jwtTokenUtil!!.getUsernameFromToken(jwt)
        }

        if (username != null && SecurityContextHolder.getContext().authentication == null) {
            val userDetails = userDetailsService!!.loadUserByUsername(username)

            if (jwtTokenUtil!!.validateToken(jwt!!, userDetails)!!) {
                val authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
                logger.info("authenticated user $username, setting security context")
                SecurityContextHolder.getContext().authentication = authentication
            }
        }

        filterChain.doFilter(request,response)
    }

}