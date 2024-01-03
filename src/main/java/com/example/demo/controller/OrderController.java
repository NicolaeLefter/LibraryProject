package com.example.demo.controller;

import com.example.demo.entity.Order;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderService orderService;

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(orderRepository.findAll());
    }
    @PostMapping("/save")
    public ResponseEntity<Object> addOrder(@RequestBody Order order){
        orderService.saveOrder(order);
        return  ResponseEntity.status(HttpStatus.OK).body("Order adaugat cu succes!");
    }
    @GetMapping("/get/{userId}")
    public ResponseEntity<Object> getUserOrders(@PathVariable Integer userId){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getUserOrders(userId));
    }
}
