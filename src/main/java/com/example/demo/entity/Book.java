package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "carti")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Introduceti titlul cartii!")
    @Column
    private String title;
    @NotEmpty(message = "Introduceti autorulul cartii!")
    @Column
    private String author;
    @Min(2)
    @Column
    private Double price;
    @Column
    private Integer stocQuantity;


}
