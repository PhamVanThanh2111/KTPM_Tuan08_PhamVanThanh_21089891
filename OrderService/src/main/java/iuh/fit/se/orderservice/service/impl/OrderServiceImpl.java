package iuh.fit.se.orderservice.service.impl;

import iuh.fit.se.orderservice.model.Order;
import iuh.fit.se.orderservice.repository.OrderRepository;
import iuh.fit.se.orderservice.service.OrderService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Value("${app.rabbit.queue-name}")
    String queueName;
    @Value("${app.rabbit.exchange-name}")
    String exchangeName;
    @Value("${app.rabbit.routing-key}")
    String routingKey;

    private final OrderRepository orderRepository;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, RabbitTemplate rabbitTemplate) {
        this.orderRepository = orderRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> getOrderById(String id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order createOrder(Order order) {
        order.setCreatedAt(new Date());
        Order savedOrder = orderRepository.save(order);
        rabbitTemplate.convertAndSend(exchangeName, routingKey, savedOrder);
        return savedOrder;
    }

    @Override
    public void deleteOrder(String id) {
        orderRepository.deleteById(id);
    }
}
