package iuh.fit.se.orderservice.controller;

import iuh.fit.se.orderservice.model.Order;
import iuh.fit.se.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin("*")
public class OrderController {
    private final OrderService service;
    private final RestTemplate restTemplate;

    @Autowired
    public OrderController(OrderService service, RestTemplate restTemplate) {
        this.service = service;
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public List<Order> getOrders() {
        return service.getAllOrders();
    }

    @PostMapping
    public Order create(@RequestBody Order o) {
        o.getItems().forEach(item -> {
            String productId = item.getProductId();
            String url = "http://localhost:8081/products/" + productId;
            Object product = restTemplate.getForObject(url, Object.class);
            if (product == null) {
                throw new RuntimeException("Product not found with id: " + productId);
            }
        });
        return service.createOrder(o); // Gửi message RabbitMQ trong service
    }

    @GetMapping("/{id}")
    public Order get(@PathVariable String id) {
        return service.getOrderById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }
}
