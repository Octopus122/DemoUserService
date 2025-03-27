package com.example.user_service.service.impl

import com.example.user_service.database.repository.UserDao
import com.example.user_service.dto.KafkaMessageDto
import com.example.user_service.exception.CantSendConfirmMessageException
import com.example.user_service.exception.EmailAlreadyConfirmedException
import com.example.user_service.exception.NotFoundException
import com.example.user_service.exception.WrongLoginDataException
import com.example.user_service.model.request.UserLogin
import com.example.user_service.model.request.UserRequestRegister
import com.example.user_service.model.response.SendConfirmationResponse
import com.example.user_service.model.response.UserRegistrationResponse
import com.example.user_service.model.response.UserResponse
import com.example.user_service.service.KafkaService
import com.example.user_service.service.UserService
import com.example.user_service.util.hasher.HashService
import com.example.user_service.util.mapper.UserMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException.NotFound

@Service
class UserServiceImpl (
    private val dao: UserDao,
    private val  mapper: UserMapper,
    private val hash: HashService,
    private val mailService: KafkaService
): UserService {
    override fun register(request: UserRequestRegister): UserRegistrationResponse {
        val entity = mapper.createRequestToEntity(request)
        val hashLine = hash.getUserHash(UserLogin(request.login, request.password))
        println("Отправка сообщения в кафку:\n $hashLine")
        try {
            mailService.sendMessage(KafkaMessageDto(
                entity.email,
                hashLine
            ))
        }
        catch (e: Exception){
            return mapper.registerEntityToResponse(dao.save(entity), false)
        }
        return mapper.registerEntityToResponse(dao.save(entity), true)
    }

    override fun getAll(): List<UserResponse>  = dao.findAll().map { mapper.entityToResponse(it) }

    override fun confirmEmail(message: String): String {
        val userData = hash.getUserData(message)
        println(userData.login)
        val user = dao.findByLogin(userData.login) ?:  throw WrongLoginDataException("Неверный логин")
        if (hash.getFromHash(user.password) != userData.password) throw WrongLoginDataException("Неверный пароль")
        if (user.isConfirmed) throw EmailAlreadyConfirmedException("Почта уже подтверждена")
        user.isConfirmed = true
        dao.save(user)
        return "Подтверждение почты прошло успешно"
    }

    override fun login(request: UserLogin): UserResponse {
        val entity = dao.findByLogin(request.login) ?: throw NotFoundException("Нет пользователя с таким логином")
        if (entity.password != hash.getHash(request.password)) throw WrongLoginDataException("Неверный пароль")
        return mapper.entityToResponse(entity)
    }

    override fun sendConfirmation(request: UserLogin): SendConfirmationResponse {
        val entity = dao.findByLogin(request.login) ?: throw NotFoundException("Нет пользователя с таким логином")
        if (entity.password != hash.getHash(request.password)) throw WrongLoginDataException("Неверный пароль")
        try{
            mailService.sendMessage(
                KafkaMessageDto(
                    entity.email,
                    hash.getUserHash(UserLogin(request.login, request.password))
                )
            )
        }
        catch (e: Exception){
            return SendConfirmationResponse(
                "Ошибка при отправке письма",
                false)
        }
        return SendConfirmationResponse(
            "Письмо отправлено на почту",
            true)
    }

}