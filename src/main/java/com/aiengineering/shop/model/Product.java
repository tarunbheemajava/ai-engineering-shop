package com.aiengineering.shop.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Product in the engineering shop")
public class Product {

    @Schema(description = "Product ID", example = "1")
    private Long id;

    @Schema(description = "Product name", example = "Laptop")
    private String name;

    @Schema(description = "Product price", example = "999.99")
    private double price;

    @Schema(description = "Product description", example = "High performance laptop")
    private String description;

    public Product() { }

    public Product(Long id, String name, double price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
