package com.rnl.ms.ms_email.consumers;

import com.rnl.ms.ms_email.dtos.EmailDto;
import com.rnl.ms.ms_email.models.EmailModel;
import com.rnl.ms.ms_email.services.EmailService;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * O pattern Producer-Consumer eh um padrao de simultaneidade onde uma ou mais
 * threads produtoras produzem objetos que vao para uma fila, e entao consumidos
 * por uma ou mais threads consumidoras
 * 
 * Este eh um consumer que vai escutar a fila de email do RabbitMQ.
 */
@Component // Bean do Spring do tipo Component
public class EmailConsumer {

    @Autowired
    private EmailService emailService; // Injecao de dependencia de EmailService

    /**
     * Escuta as mensagens da fila de email do RabbitMQ, processa a mensagem,
     * e envia o email para o emailService.
     * 
     * RabbitListener eh uma annotation do Spring que define que este metodo
     * eh o um listener de filas do RabbitMQ.
     * 
     * Nao eh necessario criar uma fila no broker https://www.cloudamqp.com/. As
     * classes do RabbitMQ, com as configuracoes ja feitas, vao criar as filas.
     * 
     * Se nao definir um Exchange, usa o default. Exchanges sao os elementos da
     * arquitetura do protocolo AMQP que recebem as mensagens e as encaminham
     * para filas de acordo com os bindings e o tipo declarado da exchange.
     * 
     * Para publicar uma mensagem nesta fila, e assim simular que eh um outro
     * microservice utilizando este microservice de email, ir em
     * https://api.cloudamqp.com/ -> RabbitMQ Manager -> Queues -> Clicar no nome
     * da queue (ms.email) -> Publish Message, e no Payload, colocar um json
     * de EmailDto, ex:
     * 
     * {
     * "ownerRef":"Ricardo Lara",
     * "emailFrom":"rnlsystems00@gmail.com",
     * "emailTo":"naberegny@gmail.com",
     * "subject":"Teste de microservice",
     * "text":"Este email foi para um teste de microservice de email. []´s"
     * }
     * 
     * // TODO inserir por exemplo tratamentos, caso a mensagem nao seja enviada, 
     *         implementar circuit breaker e resiliencia para sempre garantir as 
     *         entregas, represar mensagens em filas de erro e lidar com elas 
     *         usando RabbitMQ, etc.
     * @param emailDto
     */
    @RabbitListener(queues = "${spring.rabbitmq.queue}") // Escuta a fila de email
    public void listen(@Payload EmailDto emailDto) {
        // Cria um objeto do tipo EmailModel com os dados do emailDto
        EmailModel emailModel = new EmailModel();
        BeanUtils.copyProperties(emailDto, emailModel);
        // Envia o email para o emailService
        emailService.sendEmail(emailModel);
        // Imprime na console que o email foi enviado
        System.out.println("Email Status: " + emailModel.getStatusEmail().toString());
    }
}
