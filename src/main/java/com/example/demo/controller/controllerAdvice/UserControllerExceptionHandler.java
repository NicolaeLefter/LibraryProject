package com.example.demo.controller.controllerAdvice;

import com.example.demo.exception.userException.UserNotFoundException;
import com.example.demo.exception.userException.UserUpdateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class UserControllerExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> userNotFoundException(UserNotFoundException exception) {

        Map<String, Object> map = new HashMap<>();

        map.put("message", exception.getMessage());

        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(UserUpdateException.class)
    public ResponseEntity<Object> userUpdateException(UserUpdateException exception){

        Map<String, Object> map = new HashMap<>();

        map.put("message", exception.getMessage());

        map.put("LocalDataTime", LocalDateTime.now());

        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }
}
