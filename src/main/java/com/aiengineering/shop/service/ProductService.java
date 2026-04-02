package com.aiengineering.shop.service;

import com.aiengineering.shop.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    // In-memory store for now
    private List<Product> products = new ArrayList<>();
    private Long nextId = 1L;

    public ProductService() {
        // seed some data
        products.add(new Product(nextId++, "Laptop", 999.99, "High performance laptop"));
        products.add(new Product(nextId++, "Mouse", 29.99, "Wireless mouse"));
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Optional<Product> getById(Long id) {
        return products.stream()
            .filter(p -> p.getId().equals(id))
            .findFirst();
    }

    public Product create(Product product) {
        product.setId(nextId++);
        products.add(product);
        return product;
    }

    public boolean delete(Long id) {
        return products.removeIf(p -> p.getId().equals(id));
    }
}
