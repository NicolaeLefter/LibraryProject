package com.example.demo.service;

import com.example.demo.entity.Order;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public List<Order> getUserOrders(Integer userId){
        return orderRepository.findByUser_id(userId);
    }

    public void saveOrder(Order order){
        order.setOrderDate(LocalDateTime.now());
        orderRepository.save(order);
    }
}
