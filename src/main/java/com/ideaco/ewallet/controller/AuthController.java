package com.ideaco.ewallet.controller;

import com.ideaco.ewallet.exception.EmailExistedException;
import com.ideaco.ewallet.exception.LoginException;
import com.ideaco.ewallet.exception.PhoneNumberException;
import com.ideaco.ewallet.exception.UsernameExistedException;
import com.ideaco.ewallet.response.LoginResponse;
import com.ideaco.ewallet.response.RegisterResponse;
import com.ideaco.ewallet.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> registerUser(@RequestParam String username,
                                                         @RequestParam String email,
                                                         @RequestParam String phoneNumber,
                                                         @RequestParam String password){
        RegisterResponse response = new RegisterResponse();
        try {
            response.setData(authService.registerUser(username, email, phoneNumber, password));
            response.setMessage("success");
            response.setSuccess(true);
            response.setErrorCode("00");
            return ResponseEntity.ok().body(response);
        } catch (UsernameExistedException e){
            response.setMessage("username existed");
            response.setSuccess(false);
            response.setErrorCode("01");
            return ResponseEntity.badRequest().body(response);
        } catch (EmailExistedException e){
            response.setMessage("email existed");
            response.setSuccess(false);
            response.setErrorCode("02");
            return ResponseEntity.badRequest().body(response);
        } catch (PhoneNumberException e){
            response.setMessage("phone number existed");
            response.setSuccess(false);
            response.setErrorCode("03");
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            response.setMessage("something went wrong");
            response.setSuccess(false);
            response.setErrorCode("04");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/login")
    private ResponseEntity<LoginResponse> loginUser(@RequestParam String phoneNumber,
                                                    @RequestParam String password){
        LoginResponse response = new LoginResponse();
        try {
            response.setData(authService.login(phoneNumber, password));
            response.setMessage("success");
            response.setSuccess(true);
            response.setErrorCode("00");
            return ResponseEntity.ok().body(response);
        } catch (LoginException e){
            response.setMessage("phone number or password is incorrect");
            response.setSuccess(false);
            response.setErrorCode("01");
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            response.setMessage("something went wrong");
            response.setSuccess(false);
            response.setErrorCode("02");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
