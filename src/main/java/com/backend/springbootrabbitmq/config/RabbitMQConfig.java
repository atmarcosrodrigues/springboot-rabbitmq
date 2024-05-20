package com.backend.springbootrabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.user.name}")
    private String userQueue;

    @Value("${rabbitmq.exchange.user.name}")
    private String userExchange;

    @Value("${rabbitmq.routing.user.key}")
    private String routingJsonKey;

    // spring bean for queue (store user json messages)
    @Bean
    public Queue userJsonQueue(){
        return new Queue(userQueue);
    }

    // spring bean for rabbitmq exchange
    @Bean
    public TopicExchange userExchange(){
        return new TopicExchange(userExchange);
    }

    // binding between json queue and exchange using routing key
    @Bean
    public Binding jsonBinding(){
        return BindingBuilder
                .bind(userJsonQueue())
                .to(userExchange())
                .with(routingJsonKey);
    }

    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}