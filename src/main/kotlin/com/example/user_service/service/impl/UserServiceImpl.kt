package com.example.user_service.service.impl

import com.example.user_service.database.repository.UserDao
import com.example.user_service.dto.KafkaMessageDto
import com.example.user_service.model.request.UserLogin
import com.example.user_service.model.request.UserRequestRegister
import com.example.user_service.model.response.UserResponse
import com.example.user_service.service.KafkaService
import com.example.user_service.service.UserService
import com.example.user_service.util.hasher.HashService
import com.example.user_service.util.mapper.UserMapper
import org.springframework.stereotype.Service

@Service
class UserServiceImpl (
    private val dao: UserDao,
    private val  mapper: UserMapper,
    private val hash: HashService,
    private val mailService: KafkaService
): UserService {
    override fun register(request: UserRequestRegister): UserResponse {
        val entity = mapper.createRequestToEntity(request)
        val hashLine = hash.getUserHash(UserLogin(request.login, request.password))
        println("Отправка сообщения в кафку:\n $hashLine")
        mailService.sendMessage(KafkaMessageDto(
            entity.email,
            hashLine
        ))

        return mapper.entityToResponse(dao.save(entity))
    }

    override fun getAll(): List<UserResponse>  = dao.findAll().map { mapper.entityToResponse(it) }

    override fun confirmEmail(message: String): String {
        val userData = hash.getUserData(message)
        println(userData.login)
        val user = dao.findByLogin(userData.login) ?:  throw Exception("Неверный логин")
        if (hash.getFromHash(user.password) != userData.password) throw Exception("Неверный пароль")
        if (user.isConfirmed) return "Почта уже подтверждена"
        user.isConfirmed = true
        dao.save(user)
        return "Подтверждение почты прошло успешно"
    }

    override fun login(request: UserLogin): UserResponse {
        val entity = dao.findByLogin(request.login) ?: throw Exception("Нет пользователя с таким логином")
        if (entity.password != hash.getFromHash(request.password)) throw Exception("Неверный пароль")
        return mapper.entityToResponse(entity)
    }

}