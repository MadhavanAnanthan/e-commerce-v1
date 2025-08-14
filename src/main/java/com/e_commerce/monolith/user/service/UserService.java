package com.e_commerce.monolith.user.service;

import com.e_commerce.monolith.common.exception.PasswordMismatchException;
import com.e_commerce.monolith.user.config.UserMapper;
import com.e_commerce.monolith.user.dto.LoginRequestDTO;
import com.e_commerce.monolith.user.dto.SignupRequestDTO;
import com.e_commerce.monolith.user.model.Users;
import com.e_commerce.monolith.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Users registerUser(SignupRequestDTO signupRequestDTO) {
        if(!Objects.equals(signupRequestDTO.getPassword(), signupRequestDTO.getConfirmPassword())){
            throw new PasswordMismatchException("Password and Confirm Password do not match");
        }
        Users signupedRequestToEntity = userMapper.signupRequestToEntity(signupRequestDTO);
        signupedRequestToEntity.setPassword(bCryptPasswordEncoder.encode(signupRequestDTO.getPassword()));
        Users savedUserData = userRepository.save(signupedRequestToEntity);
        return savedUserData;
    }

    public <T>Users userAlreadyHasAccount(T signupRequestDTO) {
        Users userRepositoryByEmail = new Users();
        try {
            Class<?> aClass = signupRequestDTO.getClass();
            Method getEmail = aClass.getMethod("getEmail");
            String email = (String) getEmail.invoke(signupRequestDTO);
            userRepositoryByEmail = userRepository.findByEmail(email);
        }
        catch (Exception e){
            System.out.println("error " + e.getMessage());
        }
        return userRepositoryByEmail;
    }

}
