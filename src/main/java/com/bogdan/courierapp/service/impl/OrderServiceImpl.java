package com.bogdan.courierapp.service.impl;

import com.bogdan.courierapp.dto.OrderDto;
import com.bogdan.courierapp.entity.Order;
import com.bogdan.courierapp.exception.ErrorMessage;
import com.bogdan.courierapp.exception.OrderNotFoundException;
import com.bogdan.courierapp.repository.OrderRepository;
import com.bogdan.courierapp.service.inter.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;


    @Override
    public Order getOrderById(String id) {
        return orderRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new OrderNotFoundException(ErrorMessage.ORDER_NOT_FOUND));
    }

    @Override
    public List<Order> getAllOrdersByRestaurant(String restaurantId) {
        return orderRepository.findByRestaurantId(UUID.fromString(restaurantId));
    }

    @Override
    public Order createOrder(OrderDto orderDto) {
        Order order = Order.builder()
                .restaurantId(orderDto.getRestaurantId())
                .status(orderDto.getStatus())
                .placedAt(new Date(System.currentTimeMillis()))
                .build();
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrderById(String orderId) {
        getOrderById(orderId);
        orderRepository.deleteById(UUID.fromString(orderId));
    }
}
