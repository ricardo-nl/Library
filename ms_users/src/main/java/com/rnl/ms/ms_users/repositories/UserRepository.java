package com.rnl.ms.ms_users.repositories;

import java.util.UUID;
import com.rnl.ms.ms_users.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {
}