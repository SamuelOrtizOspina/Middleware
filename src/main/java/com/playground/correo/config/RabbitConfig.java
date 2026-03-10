package com.playground.correo.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue correoQueue(@Value("${app.messaging.queue-name:java:/jms/queue/CorreoQueue}") String queueName) {
        return new Queue(queueName, true);
    }
}
