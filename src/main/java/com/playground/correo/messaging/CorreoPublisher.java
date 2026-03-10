package com.playground.correo.messaging;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class CorreoPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final Queue correoQueue;

    public CorreoPublisher(RabbitTemplate rabbitTemplate, Queue correoQueue) {
        this.rabbitTemplate = rabbitTemplate;
        this.correoQueue = correoQueue;
    }

    public void enviar(String mensaje) {
        rabbitTemplate.convertAndSend(correoQueue.getName(), mensaje);
    }
}
