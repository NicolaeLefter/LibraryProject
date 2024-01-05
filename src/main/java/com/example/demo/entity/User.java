package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "utilizator")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Introduceti numele utilizatorului!")
    @Column
    private  String username;
    @NotEmpty(message = "Introduceti parola utilizatorului!")
    @Column
    private String password;


    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

}
