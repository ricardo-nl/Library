package com.rnl.ms.ms_email.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * Object that represents an email, with all its fields.
 * Este objeto vai ser recebido pelos metodos da api.
 * 
 * Aqui no dto sao feitas as validacoes de entrada, 
 * usando o spring-boot-starter-validation.
 * 
 * @author Rnl
 */
@Data
public class EmailDto {

    @NotBlank   // This tells Spring Boot to validate that the field is not blank
    private String ownerRef;      
    @NotBlank
    @Email      // This tells Spring Boot to validate that the field is an email
    private String emailFrom;          
    @NotBlank
    @Email 
    private String emailTo;            
    @NotBlank
    private String subject;            
    @NotBlank
    private String text;     
    
    /* 
     * id eh gerado pela base
     * data de envio e status sao gerenciados pela aplicacao 
     * e nao fazem parte do dto
     */     
}
