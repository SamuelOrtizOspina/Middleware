package com.playground.correo.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "app.messaging.startup-verify", havingValue = "true", matchIfMissing = true)
public class MessagingStartupVerifier implements ApplicationRunner {

    private final AmqpAdmin amqpAdmin;
    private final Queue correoQueue;

    public MessagingStartupVerifier(AmqpAdmin amqpAdmin, Queue correoQueue) {
        this.amqpAdmin = amqpAdmin;
        this.correoQueue = correoQueue;
    }

    @Override
    public void run(ApplicationArguments args) {
        amqpAdmin.declareQueue(correoQueue);
    }
}
