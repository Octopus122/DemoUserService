package com.example.user_service.database.repository

import com.example.user_service.database.entity.User
import org.apache.kafka.common.security.auth.Login
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserDao : CrudRepository<User, Long>{
    fun findByLogin(login: String): User?
}