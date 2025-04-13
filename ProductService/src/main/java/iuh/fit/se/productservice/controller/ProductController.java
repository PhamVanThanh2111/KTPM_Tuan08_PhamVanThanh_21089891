package iuh.fit.se.productservice.controller;

import iuh.fit.se.productservice.model.Product;
import iuh.fit.se.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<Product> getProducts() {
        return service.getAllProducts();
    }

    @PostMapping
    public Product add(@RequestBody Product p) {
        return service.createProduct(p);
    }

    @GetMapping("/{id}")
    public Product get(@PathVariable String id) {
        System.out.println("Get product with id: " + id);
        return service.getProductById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable String id, @RequestBody Product p) {
        return service.updateProduct(id, p);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.deleteProduct(id);
    }
}

