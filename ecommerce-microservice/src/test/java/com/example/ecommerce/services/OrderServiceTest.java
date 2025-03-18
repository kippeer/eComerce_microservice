package com.example.ecommerce.services;

import com.example.ecommerce.dtos.OrderDTO;
import com.example.ecommerce.enums.OrderStatus;
import com.example.ecommerce.exceptions.ResourceNotFoundException;
import com.example.ecommerce.mappers.OrderMapper;
import com.example.ecommerce.models.Order;
import com.example.ecommerce.repositories.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllOrders_Success() {
        // Arrange
        Order order1 = new Order();
        Order order2 = new Order();
        List<Order> orders = Arrays.asList(order1, order2);

        OrderDTO orderDTO1 = new OrderDTO();
        OrderDTO orderDTO2 = new OrderDTO();
        List<OrderDTO> expectedDTOs = Arrays.asList(orderDTO1, orderDTO2);

        when(orderRepository.findAll()).thenReturn(orders);
        when(orderMapper.toDTO(order1)).thenReturn(orderDTO1);
        when(orderMapper.toDTO(order2)).thenReturn(orderDTO2);

        // Act
        List<OrderDTO> result = orderService.getAllOrders();

        // Assert
        assertEquals(expectedDTOs.size(), result.size());
        verify(orderRepository).findAll();
    }

    @Test
    void createOrder_Success() {
        // Arrange
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setTotalAmount(BigDecimal.valueOf(100));
        orderDTO.setUserId(1L);

        Order order = new Order();
        order.setTotalAmount(BigDecimal.valueOf(100));
        order.setStatus(OrderStatus.PENDING);

        when(orderMapper.toEntity(any(OrderDTO.class))).thenReturn(order);
        when(orderRepository.save(any(Order.class))).thenReturn(order);
        when(orderMapper.toDTO(any(Order.class))).thenReturn(orderDTO);

        // Act
        OrderDTO result = orderService.createOrder(orderDTO);

        // Assert
        assertNotNull(result);
        assertEquals(orderDTO.getTotalAmount(), result.getTotalAmount());
        verify(orderRepository).save(any(Order.class));
    }

    @Test
    void updateOrderStatus_Success() {
        // Arrange
        Long orderId = 1L;
        String newStatus = "CONFIRMED";
        
        Order order = new Order();
        order.setId(orderId);
        order.setStatus(OrderStatus.PENDING);

        OrderDTO expectedDTO = new OrderDTO();
        expectedDTO.setId(orderId);
        expectedDTO.setStatus(OrderStatus.CONFIRMED);

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
        when(orderRepository.save(any(Order.class))).thenReturn(order);
        when(orderMapper.toDTO(any(Order.class))).thenReturn(expectedDTO);

        // Act
        OrderDTO result = orderService.updateOrderStatus(orderId, newStatus);

        // Assert
        assertNotNull(result);
        assertEquals(OrderStatus.CONFIRMED, result.getStatus());
    }
}