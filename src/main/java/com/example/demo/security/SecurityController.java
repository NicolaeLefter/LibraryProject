package com.example.demo.security;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SecurityController {

    @GetMapping("/afisare")
    @PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
    public String afisare(){

        return "Hello, World!";

    }




}
