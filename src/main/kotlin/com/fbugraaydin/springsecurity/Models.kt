package com.fbugraaydin.springsecurity

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class User(val userName: String, val encPassword: String, val isActive: Boolean, val roles:MutableList<Role>) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return roles
    }

    override fun getPassword(): String = this.encPassword

    override fun getUsername(): String = this.userName

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true

}

class Role(private var role:String) : GrantedAuthority {
    override fun getAuthority(): String {
        return this.role
    }
}