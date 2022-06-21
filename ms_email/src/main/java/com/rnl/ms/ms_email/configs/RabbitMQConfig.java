package com.rnl.ms.ms_email.configs;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuracao do RabbitMQ.
 */ 
@Configuration   // Bean do Spring do tipo Configuration
public class RabbitMQConfig {

    /* 
     * Nome da fila. Nome passado de forma dinamica pela propriedade 
     * do application.properties  
     */    
    @Value("${spring.rabbitmq.queue}")
    private String queue;  
    
    /*
     * Precisamos de um Bean da fila, do tipo Queue.
     */ 
    @Bean
    public Queue queue() {
        /*
         * Criar a fila. O ´true´ eh para fila ser durable, ou seja, as mensagens
         * na fila e a propria fila nao se perdem se os servicos forem reiniciados. 
         */
        return new Queue(queue, true);
    }

    /**
     * Precisamos de um conversor de mensagens para receber o payload como um 
     * EmailDto no metodo EmailConsumer.listen
     * 
     * Aqui criamos um conversor global, mas podem ser criados conversores especificos
     * para diferentes listeners.
     */
    @Bean 
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
