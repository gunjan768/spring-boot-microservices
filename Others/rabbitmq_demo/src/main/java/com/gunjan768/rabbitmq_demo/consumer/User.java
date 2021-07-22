package com.gunjan768.rabbitmq_demo.consumer;

import com.gunjan768.rabbitmq_demo.config.MessagingConfig;
import com.gunjan768.rabbitmq_demo.dto.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class User {

    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(OrderStatus orderStatus) {
        System.out.println("Message received from queue : " + orderStatus);
    }
}