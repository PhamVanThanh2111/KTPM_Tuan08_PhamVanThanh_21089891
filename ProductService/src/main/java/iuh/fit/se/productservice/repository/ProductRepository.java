package iuh.fit.se.productservice.repository;

import iuh.fit.se.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}