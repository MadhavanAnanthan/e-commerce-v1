package com.e_commerce.monolith.common.exception.handler;

import com.e_commerce.monolith.common.exception.PasswordMismatchException;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//@RestControllerAdvice = @ControllerAdvice + @ResponseBody
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<String> handlePasswordMismatch(PasswordMismatchException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
