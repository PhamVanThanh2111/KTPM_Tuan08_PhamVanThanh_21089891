package iuh.fit.se.orderservice.repository;

import iuh.fit.se.orderservice.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}
