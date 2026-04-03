import org.springframework.cloud.contract.spec.Contract

Contract.make {
    name "should_return_404_for_non_existent_product"
    description "GET /api/products/9999 returns 404 when product does not exist"

    request {
        method GET()
        url "/api/products/9999"
    }

    response {
        status NOT_FOUND()
    }
}
