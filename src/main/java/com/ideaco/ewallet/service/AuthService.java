package com.ideaco.ewallet.service;

import com.ideaco.ewallet.exception.EmailExistedException;
import com.ideaco.ewallet.exception.LoginException;
import com.ideaco.ewallet.exception.PhoneNumberException;
import com.ideaco.ewallet.exception.UsernameExistedException;
import com.ideaco.ewallet.model.UserModel;
import com.ideaco.ewallet.repository.UserRepository;
import com.ideaco.ewallet.response.LoginResponse;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String registerUser(String username, String email, String phoneNumber, String password) throws UsernameExistedException, EmailExistedException, PhoneNumberException {

        Optional<UserModel> usernameOpt = userRepository.findByUsername(username);
        Optional<UserModel> emailOpt = userRepository.findByEmail(email);
        Optional<UserModel> phoneNumberOpt = userRepository.findByPhoneNumber(phoneNumber);

        if (usernameOpt.isPresent()) throw new UsernameExistedException();
        if (emailOpt.isPresent()) throw new EmailExistedException();
        if (phoneNumberOpt.isPresent()) throw new PhoneNumberException();

        UserModel userModel = new UserModel();
        userModel.setEmail(email);
        userModel.setUsername(username);
        userModel.setPhoneNumber(phoneNumber);
        userModel.setPassword(password);
        userRepository.save(userModel);

        return "Register Success";
    }

    public LoginResponse.UserSession login(String phoneNumber, String password) throws LoginException {
        Optional<UserModel> userOpt = userRepository.findByPhoneNumber(phoneNumber);

        if (userOpt.isEmpty()) throw new LoginException();

        if (!userOpt.get().getPassword().equals(password)) throw new LoginException();

        return new LoginResponse.UserSession(
                userOpt.get().getId(),
                userOpt.get().getUsername(),
                userOpt.get().getEmail(),
                userOpt.get().getPhoneNumber()
        );
    }
}
