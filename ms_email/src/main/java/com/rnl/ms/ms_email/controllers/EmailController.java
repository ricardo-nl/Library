package com.rnl.ms.ms_email.controllers;

import javax.validation.Valid;

import com.rnl.ms.ms_email.dtos.EmailDto;
import com.rnl.ms.ms_email.models.EmailModel;
import com.rnl.ms.ms_email.services.EmailService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController   // Bean do Spring do tipo Controller e Rest
public class EmailController {

    @Autowired         // Injecao de dependencia
    EmailService emailService;

    /**
     * Metodo que recebe um email e o encaminha para o 
     * servico de envio de email.
     * 
     * @param emailDto - email a ser enviado
     * @Valid - valida os campos do email.
     * @return ResponseEntity<EmailModel> - resposta do envio de email
     */
    @PostMapping("/sending-email")    // Endpoint para enviar email por POST
    public ResponseEntity<EmailModel> sendingEmail(@RequestBody @Valid EmailDto emailDto) {
        // Transforma o dto em um model para salvar no banco
        EmailModel emailModel = new EmailModel();
        // Copia os dados do dto para o model
        BeanUtils.copyProperties(emailDto, emailModel);
        // Passa o model para o service, que vai tanto salvar no banco, como enviar o email
        emailService.sendEmail(emailModel);
        // Retornar para o client o model que foi salvo no banco, e o http status created
        return new ResponseEntity<>(emailModel, HttpStatus.CREATED);
    }
}
