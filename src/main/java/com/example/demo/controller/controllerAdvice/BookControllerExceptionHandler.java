package com.example.demo.controller.controllerAdvice;

import com.example.demo.exception.bookException.BookNotFoundException;
import com.example.demo.exception.bookException.BookUpdateExeption;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class BookControllerExceptionHandler {


    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<Object> bookNotFoundException(BookNotFoundException exception) {

        Map<String, Object> map = new HashMap<>();
        map.put("message", exception.getMessage());

        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookUpdateExeption.class)
    public ResponseEntity<Object> bookUpdateException(BookUpdateExeption exeption) {

        Map<String, Object> map = new HashMap<>();

        map.put("message", exeption.getMessage());
        map.put("LocalDataTime", LocalDateTime.now());

        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }
}
