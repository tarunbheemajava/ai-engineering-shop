import org.springframework.cloud.contract.spec.Contract

Contract.make {
    name "should_return_health_status"
    description "GET /api/health returns UP status with service name and timestamp"

    request {
        method GET()
        url "/api/health"
    }

    response {
        status OK()
        headers {
            contentType applicationJson()
        }
        body(
            status: "UP",
            service: "ai-engineering-shop",
            timestamp: $(producer(regex("[0-9]{4}-[0-9]{2}-[0-9]{2}T.*")))
        )
    }
}
