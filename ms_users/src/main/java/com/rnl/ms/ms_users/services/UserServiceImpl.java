package com.rnl.ms.ms_users.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import com.rnl.ms.ms_users.models.UserModel;
import com.rnl.ms.ms_users.producers.CustomMessage;
import com.rnl.ms.ms_users.producers.EmailMessageSender;
import com.rnl.ms.ms_users.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private EmailMessageSender emailMessageSender;

    @Override
    @Transactional
    public UserModel createUser(UserModel user) {
        // Criar o usuario
        UserModel createdUser = repository.save(user);
        // Enviar mensagem para a fila de email

        CustomMessage message = new CustomMessage(
            "MS_USERS",
            "rnlsystems00@gmail.com", 
            user.getEmail(), 
            "Dear " + user.getName() + ", welcome to MS Users!",
            "Your account has been created successfully!");
      
            emailMessageSender.sendMessage(message);
  
        return createdUser;
    }

    @Override
    public Optional<UserModel> getUserById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public List<UserModel> getAllUsers() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void deleteUser(UUID id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public UserModel saveUser(UserModel user) {
        return repository.save(user);
    }
}
