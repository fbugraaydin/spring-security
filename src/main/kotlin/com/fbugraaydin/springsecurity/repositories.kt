package com.fbugraaydin.springsecurity

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserDetailRepository: CrudRepository<UserEntity,Long>{
    fun findByUserName(userName:String):UserEntity?
}

@Repository
interface RoleRepository:CrudRepository<RoleEntity,Long>