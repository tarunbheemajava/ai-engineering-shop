import org.springframework.cloud.contract.spec.Contract

Contract.make {
    name "should_create_product_with_minimal_fields"
    description "POST /api/products with only name creates product with 201"

    request {
        method POST()
        url "/api/products"
        headers {
            contentType applicationJson()
        }
        body(
            name: "Gadget",
            price: 0.0,
            description: ""
        )
    }

    response {
        status CREATED()
        headers {
            contentType applicationJson()
        }
        body(
            id: $(producer(anyPositiveInt())),
            name: "Gadget",
            price: 0.0,
            description: ""
        )
    }
}
