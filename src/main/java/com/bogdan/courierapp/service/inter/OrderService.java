package com.bogdan.courierapp.service.inter;

import com.bogdan.courierapp.dto.OrderDto;
import com.bogdan.courierapp.entity.Order;

import java.util.List;

public interface OrderService {

    Order getOrderById(String id);

    List<Order> getAllOrdersByRestaurant(String restaurantId);

    Order createOrder(OrderDto orderDto);

    void deleteOrderById(String orderId);
}
