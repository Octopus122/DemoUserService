package com.example.user_service.resolvers

import com.example.user_service.dto.ErrorMessageDto
import com.example.user_service.dto.ErrorMessageWithUserDto
import com.example.user_service.exception.CantSendConfirmMessageException
import com.example.user_service.exception.EmailAlreadyConfirmedException
import com.example.user_service.exception.WrongLoginDataException
import com.example.user_service.exception.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class BasicExceptionResolver {

    @ExceptionHandler(WrongLoginDataException::class)
    fun wrongLoginDataHandleException(exception: WrongLoginDataException): ResponseEntity<ErrorMessageDto> {
        return ResponseEntity<ErrorMessageDto>(
            ErrorMessageDto(exception.message),
            HttpStatus.NOT_ACCEPTABLE
        )
    }
    @ExceptionHandler(NotFoundException::class)
    fun notFoundHandlerException(exception: NotFoundException): ResponseEntity<ErrorMessageDto> {
        return ResponseEntity<ErrorMessageDto>(
            ErrorMessageDto(exception.message),
            HttpStatus.NOT_FOUND
        )
    }
    @ExceptionHandler(Exception::class)
    fun unknownException(exception: Exception): ResponseEntity<ErrorMessageDto>{
        return ResponseEntity<ErrorMessageDto>(
            ErrorMessageDto("Что-то пошла не так"),
            HttpStatus.INTERNAL_SERVER_ERROR
        )
    }
}