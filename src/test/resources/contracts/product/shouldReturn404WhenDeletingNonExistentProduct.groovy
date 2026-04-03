import org.springframework.cloud.contract.spec.Contract

Contract.make {
    name "should_return_404_when_deleting_non_existent_product"
    description "DELETE /api/products/9999 returns 404 when product does not exist"

    request {
        method DELETE()
        url "/api/products/9999"
    }

    response {
        status NOT_FOUND()
    }
}
