package com.gunjan768.rabbitmq_demo.publisher;

import com.gunjan768.rabbitmq_demo.config.MessagingConfig;
import com.gunjan768.rabbitmq_demo.dto.Order;
import com.gunjan768.rabbitmq_demo.dto.OrderStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderPublisher {

    @Autowired
    private RabbitTemplate template;

    @PostMapping("/{restaurantName}")
    public String bookOrder(@RequestBody Order order, @PathVariable String restaurantName) {
        order.setOrderId(UUID.randomUUID().toString());

        OrderStatus orderStatus = new OrderStatus(
                order, "PROCESS", "order placed successfully in " + restaurantName
        );

        // We are informing RabbitMQ to publish my message to the Exchange using Routing Key
        template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, orderStatus);
        return "Success !!";
    }
}