package com.example.user_service.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("welcome")
class HelloController {
    @GetMapping("/unauthorized")
    fun sayHello():String = "Hello, user! This page can be opened without authorization!"
    @GetMapping("/authorized")
    fun sayHelloAuth():String = "Hello, user! This page can't be opened without authorization!"
    @GetMapping("/admin")
    fun sayHelloToAdmin():String = "Hello, admin!"
}