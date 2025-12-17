package com.ideaco.ewallet.repository;

import com.ideaco.ewallet.model.UserModel;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Integer> {
    Optional<UserModel> findByUsername(String username);
    Optional<UserModel> findByEmail(String email);
    Optional<UserModel> findByPhoneNumber(String phoneNumber);
}
