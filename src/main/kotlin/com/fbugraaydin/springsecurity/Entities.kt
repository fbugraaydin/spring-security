package com.fbugraaydin.springsecurity

import javax.persistence.*

@Entity
@Table(name = "user", uniqueConstraints = [
    UniqueConstraint(columnNames = arrayOf("username"))
])
data class UserEntity(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long? = null,

        var username: String,

        var password: String,

        var isActive: Boolean,

        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(name = "user_role",
                joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
                inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")])
        var roles: MutableList<RoleEntity> = mutableListOf()
) {
    fun toDto(): DefaultUser {
        return DefaultUser(userName = this.username,
                encPassword = this.password,
                isActive = this.isActive,
                roles = if (!this.roles.isNullOrEmpty()) this.roles.map { DefaultRole(it.role) }.toMutableList() else mutableListOf()
        )
    }
}

@Entity
@Table(name = "role")
data class RoleEntity(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        var role: String
)
