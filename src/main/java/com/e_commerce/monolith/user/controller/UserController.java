package com.e_commerce.monolith.user.controller;

import com.e_commerce.monolith.user.dto.AuthResponse;
import com.e_commerce.monolith.user.dto.LoginRequestDTO;
import com.e_commerce.monolith.user.dto.SignupRequestDTO;
import com.e_commerce.monolith.user.model.Users;
import com.e_commerce.monolith.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users/v1")
@CrossOrigin(origins = "http://localhost:5173/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    private ResponseEntity<String> login(@RequestBody LoginRequestDTO loginRequestDTO){
        String result ="";
        Optional<Users> existingUser = Optional.ofNullable(userService.userAlreadyHasAccount(loginRequestDTO));
        if (existingUser.isPresent()) {
            Users users = existingUser.get();
            boolean checkpw = BCrypt.checkpw(loginRequestDTO.getPassword(), users.getPassword());
            if(checkpw){
                result= "Success";
            }else {
                result= "Failure";
            }

        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/register")
    private ResponseEntity<AuthResponse> registerUser(@RequestBody SignupRequestDTO signupRequestDTO) {
        ResponseEntity<AuthResponse> returnResponse;

            String result = "";
            returnResponse = null;
        AuthResponse response = new AuthResponse();

            Optional<Users> existingUser = Optional.ofNullable(userService.userAlreadyHasAccount(signupRequestDTO));
            if (existingUser.isPresent()) {
                result = "Account already exists, Please login!";
                returnResponse = ResponseEntity.status(HttpStatus.CONFLICT).body(response);
            } else {
                Optional<Users> registerUser = Optional.ofNullable(userService.registerUser(signupRequestDTO));
                if (registerUser.isPresent()) {
                    Users registeredUser = registerUser.get();
                    result = "Account successfully registered for this email - " + registeredUser.getEmail();

                    AuthResponse.User user = new AuthResponse.User();
                    user.setId("1");
                    user.setEmail(registeredUser.getEmail());
                    user.setPhone("99963");
                    user.setFirstName(registeredUser.getFirstName());
                    user.setFirstName(registeredUser.getLastName());
                    response.setUser(user);
                    response.setString12("ok from backend");
                    returnResponse = ResponseEntity.status(HttpStatus.CREATED).body(response);
                }
            }
        return returnResponse;
    }
}
