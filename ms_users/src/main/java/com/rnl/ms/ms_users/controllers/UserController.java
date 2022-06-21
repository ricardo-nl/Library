package com.rnl.ms.ms_users.controllers;

import javax.validation.Valid;

import com.rnl.ms.ms_users.controllers.assemblers.UserModelAssembler;
import com.rnl.ms.ms_users.dtos.UserDto;
import com.rnl.ms.ms_users.exceptions.UserNotFoundException;
import com.rnl.ms.ms_users.models.UserModel;
import com.rnl.ms.ms_users.services.UserService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*; // for HATEOAS
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")                
public class UserController {

    @Autowired
    private UserService service;
    @Autowired
    private UserModelAssembler assembler;

    @PostMapping("/add")
    public EntityModel<UserModel> create(@RequestBody @Valid UserDto userDto) {
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);      
        UserModel savedUser = service.createUser(userModel);
        return assembler.toModel(savedUser);          
    }   
    
    @GetMapping("/{id}")
    public EntityModel<UserModel> getById(@PathVariable UUID id) {
        UserModel userModel = service.getUserById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        return assembler.toModel(userModel);        
    }

    @GetMapping("/all")
    public CollectionModel<EntityModel<UserModel>> getAll() {      
        // List of resources, fetch them all and create the links for each one of them
        List<EntityModel<UserModel>> allUsers = service.getAllUsers()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());      
        // Create a link to this .all method
        return CollectionModel.of(allUsers, linkTo(methodOn(UserController.class).getAll()).withSelfRel());
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable UUID id) {        
        service.deleteUser(id);               
    }

    @PutMapping("/{id}")
    public EntityModel<UserModel> update(@RequestBody @Valid UserDto userDto, @PathVariable UUID id) {
        // Tenta localizar o user 
        UserModel userModel = service.getUserById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        // Atualiza os dados do user
        BeanUtils.copyProperties(userDto, userModel);
        // Salva o user
        UserModel savedUser = service.saveUser(userModel);
        // Retorna o user atualizado
        return assembler.toModel(savedUser);
    }   
}

