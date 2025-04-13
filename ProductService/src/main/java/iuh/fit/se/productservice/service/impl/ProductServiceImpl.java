package iuh.fit.se.productservice.service.impl;

import iuh.fit.se.productservice.model.Product;
import iuh.fit.se.productservice.repository.ProductRepository;
import iuh.fit.se.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Autowired
    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Optional<Product> getProductById(String id) {
        return repository.findById(id);
    }

    public Product createProduct(Product product) {
        return repository.save(product);
    }

    public Product updateProduct(String id, Product product) {
        Product oldProduct = repository.findById(id).get();
        oldProduct.setName(product.getName());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setDescription(product.getDescription());

        return repository.save(oldProduct);
    }

    public void deleteProduct(String id) {
        repository.deleteById(id);
    }
}
