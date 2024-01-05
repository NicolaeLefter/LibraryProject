package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

   @PostMapping("/save")
    public ResponseEntity<Object> addUser(@RequestBody User user){
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).body("Utilizatorul a fost adaugat cu succes!");
    }
    @GetMapping("/get/{username}")
    public Optional<User> getUserOrders(@PathVariable String username){
        return userService.getByUsername(username);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll());
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUserById(@RequestBody User user,
                                               @PathVariable Integer id){
       
       return ResponseEntity.status(HttpStatus.OK).body(userService.updateUserById(user,id));
    }
}
