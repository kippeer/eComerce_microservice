package com.example.ecommerce.services;

import com.example.ecommerce.dtos.OrderDTO;
import com.example.ecommerce.enums.OrderStatus;
import com.example.ecommerce.exceptions.ResourceNotFoundException;
import com.example.ecommerce.mappers.OrderMapper;
import com.example.ecommerce.models.Order;
import com.example.ecommerce.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private OrderMapper orderMapper;
    
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    public OrderDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        return orderMapper.toDTO(order);
    }
    
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = orderMapper.toEntity(orderDTO);
        order.setStatus(OrderStatus.PENDING);
        order = orderRepository.save(order);
        return orderMapper.toDTO(order);
    }
    
    public OrderDTO updateOrderStatus(Long id, String status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        
        order.setStatus(OrderStatus.valueOf(status.toUpperCase()));
        order = orderRepository.save(order);
        return orderMapper.toDTO(order);
    }
}