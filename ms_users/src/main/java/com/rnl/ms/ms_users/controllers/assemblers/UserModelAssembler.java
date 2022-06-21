package com.rnl.ms.ms_users.controllers.assemblers;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.rnl.ms.ms_users.controllers.UserController;
import com.rnl.ms.ms_users.models.UserModel;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<UserModel, EntityModel<UserModel>> {

    @Override
    public EntityModel<UserModel> toModel(UserModel userModel) {        
        return EntityModel.of(userModel, 
        linkTo(methodOn(UserController.class).getById(userModel.getUserId())).withSelfRel(),
        linkTo(methodOn(UserController.class).getAll()).withRel("users"));  
    }        
}
