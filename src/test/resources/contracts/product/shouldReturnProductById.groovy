import org.springframework.cloud.contract.spec.Contract

Contract.make {
    name "should_return_product_by_id"
    description "GET /api/products/1 returns the Laptop product"

    request {
        method GET()
        url "/api/products/1"
    }

    response {
        status OK()
        headers {
            contentType applicationJson()
        }
        body(
            id: 1,
            name: "Laptop",
            price: 999.99,
            description: "High performance laptop"
        )
    }
}
