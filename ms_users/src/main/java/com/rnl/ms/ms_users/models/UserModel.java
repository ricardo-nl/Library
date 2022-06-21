package com.rnl.ms.ms_users.models;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "TB_USER")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userId;
    @Column(nullable = false, unique = false, length = 64)
    private String name;
    @Column(nullable = false, unique = true, length = 64)
    private String email;
    @Column(nullable = false, unique = false, length = 64)
    private String password;
}