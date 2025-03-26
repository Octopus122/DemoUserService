package com.example.user_service.util.mapper

import com.example.user_service.database.entity.User
import com.example.user_service.model.request.UserRequestRegister
import com.example.user_service.model.request.UserUpdateRequest
import com.example.user_service.model.response.UserResponse
import com.example.user_service.util.hasher.HashService
import org.springframework.stereotype.Component

@Component
class UserMapper (
    private val hash: HashService
) {
    fun entityToResponse(entity: User):UserResponse = UserResponse(
        entity.id,
        entity.login,
        entity.email,
        entity.isConfirmed,
        entity.bio
    )
    fun createRequestToEntity(request: UserRequestRegister): User = User(
        login = request.login,
        email = request.email,
        password = hash.getHash(request.password),
        bio = request.bio
    )
    fun updateRequestToEntity(entity: User, request: UserUpdateRequest): User = entity.apply {
        login = request.login
        bio = request.bio
    }
}