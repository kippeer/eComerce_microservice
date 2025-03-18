package com.example.ecommerce.mappers;

import com.example.ecommerce.dtos.OrderDTO;
import com.example.ecommerce.models.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    
    public OrderDTO toDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setUserId(order.getUser().getId());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setCreatedAt(order.getCreatedAt());
        dto.setStatus(order.getStatus());
        return dto;
    }
    
    public Order toEntity(OrderDTO dto) {
        Order order = new Order();
        order.setId(dto.getId());
        order.setTotalAmount(dto.getTotalAmount());
        order.setCreatedAt(dto.getCreatedAt());
        order.setStatus(dto.getStatus());
        return order;
    }
}