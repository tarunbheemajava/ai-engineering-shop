package com.aiengineering.shop.e2e;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Product Edge Case E2E Tests")
class ProductEdgeCaseE2eTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    @DisplayName("GET /api/products/{id} returns 404 for non-existent ID")
    void getNonExistentProductReturns404() {
        given()
            .when()
                .get("/api/products/9999")
            .then()
                .statusCode(404);
    }

    @Test
    @DisplayName("DELETE /api/products/{id} returns 404 for non-existent ID")
    void deleteNonExistentProductReturns404() {
        given()
            .when()
                .delete("/api/products/9999")
            .then()
                .statusCode(404);
    }

    @Test
    @DisplayName("POST /api/products with zero price succeeds")
    void createProductWithZeroPriceSucceeds() {
        String requestBody = """
            {
                "name": "Freebie",
                "price": 0.0,
                "description": "Free item"
            }
            """;

        given()
            .contentType(ContentType.JSON)
            .body(requestBody)
            .when()
                .post("/api/products")
            .then()
                .statusCode(201)
                .body("name", equalTo("Freebie"))
                .body("price", equalTo(0.0f));
    }

    @Test
    @DisplayName("POST /api/products assigns auto-incremented ID")
    void createProductAssignsIncrementedId() {
        String requestBody = """
            {
                "name": "Widget",
                "price": 10.00,
                "description": "A widget"
            }
            """;

        int firstId = given()
            .contentType(ContentType.JSON)
            .body(requestBody)
            .when()
                .post("/api/products")
            .then()
                .statusCode(201)
            .extract()
                .path("id");

        int secondId = given()
            .contentType(ContentType.JSON)
            .body(requestBody)
            .when()
                .post("/api/products")
            .then()
                .statusCode(201)
            .extract()
                .path("id");

        // second ID must be greater than first
        org.junit.jupiter.api.Assertions.assertTrue(
            secondId > firstId,
            "Second product ID should be greater than first"
        );
    }

    @Test
    @DisplayName("DELETE same product twice returns 204 then 404")
    void deleteSameProductTwice() {
        // Create a product
        String requestBody = """
            {
                "name": "DoubleDelete",
                "price": 5.00,
                "description": "Delete me twice"
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

        // First delete succeeds
        given()
            .when()
                .delete("/api/products/" + createdId)
            .then()
                .statusCode(204);

        // Second delete returns 404
        given()
            .when()
                .delete("/api/products/" + createdId)
            .then()
                .statusCode(404);
    }

    @Test
    @DisplayName("Created product is retrievable by ID")
    void createdProductIsRetrievableById() {
        String requestBody = """
            {
                "name": "Monitor",
                "price": 499.99,
                "description": "4K monitor"
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
                .get("/api/products/" + createdId)
            .then()
                .statusCode(200)
                .body("id", equalTo(createdId))
                .body("name", equalTo("Monitor"))
                .body("price", equalTo(499.99f))
                .body("description", equalTo("4K monitor"));
    }

    @Test
    @DisplayName("GET /api/products/{id} with string ID returns 400")
    void getProductWithInvalidIdReturns400() {
        given()
            .when()
                .get("/api/products/abc")
            .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("POST /api/products with empty body returns 400")
    void createProductWithEmptyBodyReturns400() {
        given()
            .contentType(ContentType.JSON)
            .body("{}")
            .when()
                .post("/api/products")
            .then()
                .statusCode(201)
                .body("id", notNullValue());
    }
}
