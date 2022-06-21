package com.rnl.ms.ms_users.configs;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Configuracao do RabbitMQ.
 */
@SpringBootApplication
@EnableScheduling
public class MessagingConfig {

    public static final String QUEUE_NAME = "ms_email_queue";
    public static final String EXCHANGE_NAME = "library_exchange";
    public static final String ROUTING_KEY = "green";
    
    @Bean
    public TopicExchange appExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue appQueueGeneric() {
        return new Queue("generic_queue");
    }

    @Bean
    public Queue appQueueSpecific() {
        return new Queue(QUEUE_NAME);
    }

    @Bean
    public Binding declareBindingGeneric() {
        return BindingBuilder.bind(appQueueGeneric()).to(appExchange()).with("blue");
    }

    @Bean
    public Binding declareBindingSpecific() {
        return BindingBuilder.bind(appQueueSpecific()).to(appExchange()).with(ROUTING_KEY);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
