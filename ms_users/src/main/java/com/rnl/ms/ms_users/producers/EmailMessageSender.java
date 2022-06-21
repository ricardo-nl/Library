package com.rnl.ms.ms_users.producers;

import com.rnl.ms.ms_users.configs.MessagingConfig;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * O pattern Producer-Consumer eh um padrao de simultaneidade onde uma ou mais
 * threads produtoras produzem objetos que vao para uma fila, e entao consumidos
 * por uma ou mais threads consumidoras
 * 
 * Este eh um consumer que vai escutar a fila de email do RabbitMQ.
 */
@Component // Bean do Spring do tipo Component
public class EmailMessageSender {

    @Autowired
    RabbitTemplate rabbitTemplate;

    private static Logger logger = LogManager.getLogger(EmailMessageSender.class);

    public void sendMessage(CustomMessage message) {
        logger.log(Level.INFO, "Enviando mensagem", message);    
        rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE_NAME, MessagingConfig.ROUTING_KEY, message);
    }
}
