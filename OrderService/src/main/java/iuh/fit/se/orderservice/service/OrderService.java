package iuh.fit.se.orderservice.service;

import iuh.fit.se.orderservice.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> getAllOrders();

    Optional<Order> getOrderById(String id);

    Order createOrder(Order order);

    void deleteOrder(String id);
}
