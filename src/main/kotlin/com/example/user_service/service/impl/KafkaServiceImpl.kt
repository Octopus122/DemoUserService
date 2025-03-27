package com.example.user_service.service.impl

import com.example.user_service.dto.KafkaMessageDto
import com.example.user_service.service.KafkaService
import org.hibernate.service.spi.InjectService
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType


@Service
class KafkaServiceImpl(
    private val kafkaTemplate: KafkaTemplate<String, KafkaMessageDto>
) : KafkaService {
    @Value("\${topic.send-order}")
    private val topic: String = ""

    override fun sendMessage(message: KafkaMessageDto) {
        val kafkaTask = kafkaTemplate.send(topic, "1", message)
        if (kafkaTask.isDone) println("Is done")
        if (kafkaTask.isCompletedExceptionally) println("CompletedExceptionaly")
        if (kafkaTask.isCancelled) println("Cancelled")
    }
}