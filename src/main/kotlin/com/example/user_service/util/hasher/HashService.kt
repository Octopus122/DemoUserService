package com.example.user_service.util.hasher

import com.example.user_service.database.entity.User
import com.example.user_service.model.request.UserLogin
import org.springframework.stereotype.Component
import java.util.*

@Component
class HashService {
    fun getUserData(hash: String): UserLogin {
        val decoded = String(Base64.getDecoder().decode(hash))
        println("Decoded: $decoded")
        val parsed: List<String> = decoded.split(":")
        return UserLogin(
            login = parsed[0],
            password = parsed[1]
        )
    }
    fun getUserHash(data: UserLogin): String{
        val encoded = Base64.getEncoder().encodeToString("${data.login}:${data.password}".toByteArray())
        return encoded
    }

    fun getHash(data: String): String{
        return Base64.getEncoder().encodeToString(data.toByteArray())
    }

    fun getFromHash(hash: String): String{
        return String(Base64.getDecoder().decode(hash))
    }
}