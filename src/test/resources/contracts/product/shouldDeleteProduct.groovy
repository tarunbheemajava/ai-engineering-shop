import org.springframework.cloud.contract.spec.Contract

Contract.make {
    name "should_delete_product"
    description "DELETE /api/products/2 deletes the Mouse product and returns 204"

    request {
        method DELETE()
        url "/api/products/2"
    }

    response {
        status NO_CONTENT()
    }
}
