package com.example.user_service.controller

import com.example.user_service.model.response.UserResponse
import com.example.user_service.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("admin")
class AdminController (
    private val service: UserService
){
    @GetMapping("users")
    fun getUsers(): List<UserResponse>{
        return service.getAll()
    }
}