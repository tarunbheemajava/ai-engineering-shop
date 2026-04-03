import org.springframework.cloud.contract.spec.Contract

Contract.make {
    name "should_return_all_products"
    description "GET /api/products returns a list of products with correct structure"

    request {
        method GET()
        url "/api/products"
    }

    response {
        status OK()
        headers {
            contentType applicationJson()
        }
        body([
            [
                id: $(producer(anyPositiveInt())),
                name: $(producer(anyNonBlankString())),
                price: $(producer(anyDouble())),
                description: $(producer(anyNonEmptyString()))
            ]
        ])
        bodyMatchers {
            jsonPath('$', byType { minOccurrence(1) })
            jsonPath('$[0].id', byRegex(nonEmpty()).asInteger())
            jsonPath('$[0].name', byRegex(nonBlank()).asString())
        }
    }
}
