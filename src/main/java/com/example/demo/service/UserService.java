package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.exception.bookException.BookNotFoundException;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Optional<User> getByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public User updateUserById(User user, Integer id){

        User utilizator = userRepository.findById(id).orElseThrow(()->
               new BookNotFoundException("Nu a fost gasita cartea cu id-ul" + id));

        utilizator.setUsername(user.getUsername());
        utilizator.setPassword(user.getPassword());
        userRepository.save(utilizator);

        return utilizator;
    }

}
