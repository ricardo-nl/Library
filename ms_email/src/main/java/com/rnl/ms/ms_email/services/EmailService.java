package com.rnl.ms.ms_email.services;

import java.time.LocalDateTime;

import com.rnl.ms.ms_email.enums.StatusEmail;
import com.rnl.ms.ms_email.models.EmailModel;
import com.rnl.ms.ms_email.repositories.EmailRepository;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service     // Bean do Spring do tipo Service
public class EmailService {

    @Autowired         // Injecao de dependencia
    EmailRepository emailRepository;

    @Autowired
    private JavaMailSender emailSender;
    
    private static Logger logger = LogManager.getLogger(EmailService.class);

    /**
     * A fim de testar a implementacao de um microservice, vamos simplificar
     * e utilizar o smtp do gmail. Se quiser uma implementacao de envio de
     * email mais robusta, utilizar, por exemplo o Amazon Simple Email 
     * Service (SES).
     * 
     * TODO - esconder a senha do email no application.properties
     * TODO - implementar envio de email mais robusto com SES ao inves de gmail
     *  
     * @param emailModel
     */
    public EmailModel sendEmail(EmailModel emailModel) {

        // Setar a data de envio
        emailModel.setSendDateEmail(LocalDateTime.now());

        try{
            // Criar o objeto de email
            SimpleMailMessage message = new SimpleMailMessage();
            // Preencher os dados do email
            message.setFrom(emailModel.getEmailFrom());
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());
            // Enviar o email
            emailSender.send(message);
            // Setar o status do email
            emailModel.setStatusEmail(StatusEmail.SENT);
        } catch(MailException e) {
            // TODO implementar algum sistema de re-tentativa de envio de email
            logger.log(Level.ERROR, "Erro ao enviar email", new Exception());          
            emailModel.setStatusEmail(StatusEmail.ERROR);
        } finally {
            // Salvar o email no banco
            return emailRepository.save(emailModel);
        }
    }    
}
