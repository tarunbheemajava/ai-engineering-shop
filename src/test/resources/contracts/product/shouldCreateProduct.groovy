import org.springframework.cloud.contract.spec.Contract

Contract.make {
    name "should_create_product"
    description "POST /api/products creates a new product and returns 201"

    request {
        method POST()
        url "/api/products"
        headers {
            contentType applicationJson()
        }
        body(
            name: "Keyboard",
            price: 79.99,
            description: "Mechanical keyboard"
        )
    }

    response {
        status CREATED()
        headers {
            contentType applicationJson()
        }
        body(
            id: $(producer(anyPositiveInt())),
            name: "Keyboard",
            price: 79.99,
            description: "Mechanical keyboard"
        )
    }
}
