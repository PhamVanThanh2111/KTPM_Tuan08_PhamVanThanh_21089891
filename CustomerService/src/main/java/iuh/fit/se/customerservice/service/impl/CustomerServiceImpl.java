package iuh.fit.se.customerservice.service.impl;

import iuh.fit.se.customerservice.model.Customer;
import iuh.fit.se.customerservice.repository.CustomerRepository;
import iuh.fit.se.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void delete(String id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Customer save(Customer c) {
        return customerRepository.save(c);
    }

    @Override
    public Customer get(String id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public Customer update(String id, Customer c) {
        Customer oldCustomer = customerRepository.findById(id).get();
        oldCustomer.setName(c.getName());
        oldCustomer.setEmail(c.getEmail());
        oldCustomer.setPhone(c.getPhone());
        oldCustomer.setAddress(c.getAddress());

        return oldCustomer;
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }
}
