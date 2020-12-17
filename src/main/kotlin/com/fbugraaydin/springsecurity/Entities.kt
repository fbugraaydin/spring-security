package com.fbugraaydin.springsecurity

import javax.persistence.*

@Entity
@Table(name = "user")
data class UserEntity(
        @Id @GeneratedValue var id: Long? = null,
        var username: String,
        var password: String,
        var isActive: Boolean,
        @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        var roles: MutableSet<RoleEntity>? = mutableSetOf()
) {
    fun toDto(): User {
        return User(userName = this.username,
                encPassword = this.password,
                isActive = this.isActive,
                roles = this.roles!!.map { Role(it.role) }.toMutableList())

    }

}

@Entity
@Table(name = "role")
data class RoleEntity(
        @Id @GeneratedValue
        var id: Long? = null,
        var role: String,

        @ManyToOne(fetch = FetchType.EAGER, optional = false)
        @JoinColumn(name = "user_id", nullable = false)
        var user: UserEntity? = null
)
