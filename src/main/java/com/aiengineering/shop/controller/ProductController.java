package com.aiengineering.shop.controller;

import com.aiengineering.shop.model.Product;
import com.aiengineering.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAll() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        Product product = productService.getById(id);
        if (product == null) {
            return null;
        }
        return product;
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        return productService.create(product);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        boolean deleted = productService.delete(id);
        if (deleted) {
            return "Deleted";
        }
        return "Not found";
    }
}
