package com.upnextdev.datagen.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.upnextdev.datagen.entity.AuditEmail;
import com.upnextdev.datagen.mq.RabbitMQConfig;

@Service
public class FauxMessageProducerService {

	private final RabbitTemplate rabbitTemplate;
	
	public FauxMessageProducerService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String auditEmailLog) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, auditEmailLog);
    }
}
