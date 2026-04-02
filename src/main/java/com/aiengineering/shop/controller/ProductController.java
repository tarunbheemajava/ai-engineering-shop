package com.aiengineering.shop.controller;

import com.aiengineering.shop.model.Product;
import com.aiengineering.shop.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Products", description = "Product management operations")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    @Operation(summary = "List all products",
               description = "Returns a list of all products in the store")
    public List<Product> getAll() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product by ID",
               description = "Returns a single product by its ID")
    @ApiResponse(responseCode = "200", description = "Product found")
    public Product getById(
            @Parameter(description = "Product ID") @PathVariable Long id) {
        Product product = productService.getById(id);
        if (product == null) {
            return null;
        }
        return product;
    }

    @PostMapping
    @Operation(summary = "Create a product",
               description = "Creates a new product and returns it with a generated ID")
    @ApiResponse(responseCode = "200", description = "Product created")
    public Product create(@RequestBody Product product) {
        return productService.create(product);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a product",
               description = "Deletes a product by its ID")
    @ApiResponse(responseCode = "200", description = "Product deleted or not found")
    public String delete(
            @Parameter(description = "Product ID") @PathVariable Long id) {
        boolean deleted = productService.delete(id);
        if (deleted) {
            return "Deleted";
        }
        return "Not found";
    }
}
