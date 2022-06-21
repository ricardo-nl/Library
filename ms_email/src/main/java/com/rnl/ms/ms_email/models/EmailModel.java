package com.rnl.ms.ms_email.models;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.rnl.ms.ms_email.enums.StatusEmail;

import lombok.Data;

@Entity  // This tells Hibernate to make a table out of this class
@Data    // This tells lombok to generate getters and setters
@Table(name = "TB_EMAIL")   // This tells Hibernate to name the table
public class EmailModel {

    // This is the primary key
     /*
     * Para microservices, o tipo do id deve ser UUID, para evitar problemas
     * de conflitos dentro da arquitetura.
     * Long seria um identificador para uma tabela, UUID eh um identificador
     * global para toda a aplicacao.
     */ 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID emailId;              
    // Outros campos
    private String ownerRef;           // Referencia do proprietario do email
    private String emailFrom;          // Email de origem
    private String emailTo;            // Email de destino
    private String subject;            // Assunto
    @Column(columnDefinition = "TEXT") // This tells Hibernate to use a TEXT field
    private String text;                 // Corpo do email. Mais do que 255 caracteres 
    private LocalDateTime sendDateEmail; // Data de envio do email
    private StatusEmail statusEmail;     // Status do email
}
