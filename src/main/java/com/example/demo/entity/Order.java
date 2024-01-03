package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "book_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToMany
    @JoinTable(
            name = "order_book",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> bookList;

    private LocalDateTime orderDate;

    private Double totalAmount;


}
