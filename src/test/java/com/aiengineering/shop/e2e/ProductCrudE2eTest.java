package com.aiengineering.shop.e2e;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Product CRUD E2E Tests")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductCrudE2eTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    @Order(1)
    @DisplayName("GET /api/products returns seeded product list")
    void getAllReturnsSeededProducts() {
        given()
            .when()
                .get("/api/products")
            .then()
                .statusCode(200)
                .body("$", hasSize(greaterThanOrEqualTo(2)))
                .body("[0].id", notNullValue())
                .body("[0].name", equalTo("Laptop"))
                .body("[0].price", equalTo(999.99f))
                .body("[0].description", equalTo("High performance laptop"))
                .body("[1].name", equalTo("Mouse"))
                .body("[1].price", equalTo(29.99f));
    }

    @Test
    @Order(2)
    @DisplayName("GET /api/products/{id} returns single product")
    void getByIdReturnsProduct() {
        given()
            .when()
                .get("/api/products/1")
            .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("name", equalTo("Laptop"))
                .body("price", equalTo(999.99f))
                .body("description", equalTo("High performance laptop"));
    }

    @Test
    @Order(3)
    @DisplayName("POST /api/products creates a new product with 201")
    void createProductReturns201() {
        String requestBody = """
            {
                "name": "Keyboard",
                "price": 79.99,
                "description": "Mechanical keyboard"
            }
            """;

        given()
            .contentType(ContentType.JSON)
            .body(requestBody)
            .when()
                .post("/api/products")
            .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("name", equalTo("Keyboard"))
                .body("price", equalTo(79.99f))
                .body("description", equalTo("Mechanical keyboard"));
    }

    @Test
    @Order(4)
    @DisplayName("GET /api/products reflects newly created product")
    void getAllIncludesCreatedProduct() {
        given()
            .when()
                .get("/api/products")
            .then()
                .statusCode(200)
                .body("$", hasSize(greaterThanOrEqualTo(3)));
    }

    @Test
    @Order(5)
    @DisplayName("DELETE /api/products/{id} removes product with 204")
    void deleteProductReturns204() {
        // First create a product to delete
        String requestBody = """
            {
                "name": "ToDelete",
                "price": 1.00,
                "description": "Will be deleted"
            }
            """;

        int createdId = given()
            .contentType(ContentType.JSON)
            .body(requestBody)
            .when()
                .post("/api/products")
            .then()
                .statusCode(201)
            .extract()
                .path("id");

        // Now delete it
        given()
            .when()
                .delete("/api/products/" + createdId)
            .then()
                .statusCode(204);
    }

    @Test
    @Order(6)
    @DisplayName("GET /api/products/{id} returns 404 after deletion")
    void getDeletedProductReturns404() {
        // Create and delete a product
        String requestBody = """
            {
                "name": "Temporary",
                "price": 5.00,
                "description": "Temporary product"
            }
            """;

        int createdId = given()
            .contentType(ContentType.JSON)
            .body(requestBody)
            .when()
                .post("/api/products")
            .then()
                .statusCode(201)
            .extract()
                .path("id");

        given()
            .when()
                .delete("/api/products/" + createdId)
            .then()
                .statusCode(204);

        // Verify it's gone
        given()
            .when()
                .get("/api/products/" + createdId)
            .then()
                .statusCode(404);
    }
}
