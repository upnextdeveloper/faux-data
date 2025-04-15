package com.upnextdev.datagen.mq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
	
	public static final String QUEUE_NAME = "faux_rabbit";

    @Bean
    public Queue senderQueue() {
        return new Queue(QUEUE_NAME, false);
    }

}
