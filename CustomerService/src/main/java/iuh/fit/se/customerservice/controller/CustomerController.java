package iuh.fit.se.customerservice.controller;

import iuh.fit.se.customerservice.model.Customer;
import iuh.fit.se.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService service;

    @Autowired
    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return service.findAll();
    }

    @PostMapping
    public Customer add(@RequestBody Customer c) {
        return service.save(c);
    }

    @GetMapping("/{id}")
    public Customer get(@PathVariable String id) {
        return service.get(id);
    }
}
