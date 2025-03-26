package com.example.user_service.util.mailValidator

import org.springframework.stereotype.Component
import java.util.regex.Pattern

@Component
class MailValidator {
    var emailPattern: Pattern =
        Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")
    fun validateEmail(email: String): Boolean{
        return emailPattern.matcher(email).matches()
    }
}