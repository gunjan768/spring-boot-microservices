package com.gunjan768.rabbitmq_demo.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// There is an Exchange which is connected to the multiple Queues with Routing keys. Publisher will send message to the Exchange along
// with the Routing key. At the very first, we will bind the Exchange to Queues using Routing keys. Message sent to the Exchange (with
// the Routing key) will be sent to the Queue to which this Routing key is bound. From there (queue), consumer will consume the message

/**
 * Publisher (assume swiggy or zomato) will send (or publish) the message (like order). It will send to the Exchange with the Routing key
 * so that it can be sent to the Queue to which this Routing Key binds to.
 * Consumer will consume from the Queue
 */
@Configuration
public class MessagingConfig {

    public static final String QUEUE = "emilia_queue";
    public static final String EXCHANGE = "emilia_exchange";
    public static final String ROUTING_KEY = "emilia_routingKey";

    // Created Queue
    @Bean
    public Queue queue() {
        return new Queue(QUEUE);
    }

    // Created Exchange
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    // Now here comes binding of Exchange and Queue through Routing key
    @Bean
    public Binding binding(Queue queue, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue)
                .to(topicExchange)
                .with(ROUTING_KEY);
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
     }
}
