package com.rnl.ms.ms_email.repositories;

import java.util.UUID;

import com.rnl.ms.ms_email.models.EmailModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<EmailModel, UUID> {
}
