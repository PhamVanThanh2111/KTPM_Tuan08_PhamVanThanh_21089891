package iuh.fit.se.customerservice.repository;

import iuh.fit.se.customerservice.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
}
