package com.rnl.ms.ms_users.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.rnl.ms.ms_users.models.UserModel;

public interface UserService {

    public UserModel createUser(UserModel user);
    public Optional<UserModel> getUserById(UUID id);
    public List<UserModel> getAllUsers();
    public void deleteUser(UUID id);
    public UserModel saveUser(UserModel user);    
}
