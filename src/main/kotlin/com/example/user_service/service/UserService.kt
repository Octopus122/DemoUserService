package com.example.user_service.service

import com.example.user_service.model.request.UserLogin
import com.example.user_service.model.request.UserRequestRegister
import com.example.user_service.model.response.UserResponse

interface UserService{
    fun register(request: UserRequestRegister): UserResponse
    fun getAll(): List<UserResponse>
    fun confirmEmail(message: String): String
    fun login(request: UserLogin): UserResponse
}