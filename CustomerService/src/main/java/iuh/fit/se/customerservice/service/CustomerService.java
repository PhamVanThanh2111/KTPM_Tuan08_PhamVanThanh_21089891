package iuh.fit.se.customerservice.service;

import iuh.fit.se.customerservice.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer save(Customer c);

    Customer get(String id);

    void delete(String id);

    Customer update(String id, Customer c);

    List<Customer> findAll();
}
