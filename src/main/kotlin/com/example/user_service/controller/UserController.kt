package com.example.user_service.controller

import com.example.user_service.model.request.UserLogin
import com.example.user_service.model.request.UserRequestRegister
import com.example.user_service.model.response.UserRegistrationResponse
import com.example.user_service.model.response.UserResponse
import com.example.user_service.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("user")
class UserController(
    private val service: UserService
) {

    @PostMapping("/register")
    fun register(@RequestBody request: UserRequestRegister): UserRegistrationResponse {
        return service.register(request)
    }

    @GetMapping("/login")
    fun login(@RequestBody request: UserLogin): UserResponse{
        return service.login(request)
    }

    @PostMapping("/confirm/{message}")
    fun confirmEmail(@PathVariable message:String ):String{
        return service.confirmEmail(message)
    }
}