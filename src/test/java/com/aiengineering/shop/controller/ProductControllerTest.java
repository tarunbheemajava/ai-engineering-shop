package com.aiengineering.shop.controller;

import com.aiengineering.shop.model.Product;
import com.aiengineering.shop.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;

    @Test
    void getAllReturnsProductList() throws Exception {
        when(productService.getAllProducts()).thenReturn(
            List.of(new Product(1L, "Laptop", 999.99, "High performance laptop"))
        );

        mockMvc.perform(get("/api/products"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name").value("Laptop"))
            .andExpect(jsonPath("$[0].price").value(999.99));
    }

    @Test
    void getByIdReturnsProduct() throws Exception {
        when(productService.getById(1L)).thenReturn(
            Optional.of(new Product(1L, "Laptop", 999.99, "High performance laptop"))
        );

        mockMvc.perform(get("/api/products/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("Laptop"));
    }

    @Test
    void getByIdReturns404WhenNotFound() throws Exception {
        when(productService.getById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/products/99"))
            .andExpect(status().isNotFound());
    }

    @Test
    void createReturns201() throws Exception {
        Product created = new Product(1L, "Mouse", 29.99, "Wireless mouse");
        when(productService.create(any(Product.class))).thenReturn(created);

        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Mouse\",\"price\":29.99,\"description\":\"Wireless mouse\"}"))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.name").value("Mouse"));
    }

    @Test
    void deleteReturns204WhenDeleted() throws Exception {
        when(productService.delete(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/products/1"))
            .andExpect(status().isNoContent());
    }

    @Test
    void deleteReturns404WhenNotFound() throws Exception {
        when(productService.delete(99L)).thenReturn(false);

        mockMvc.perform(delete("/api/products/99"))
            .andExpect(status().isNotFound());
    }
}
