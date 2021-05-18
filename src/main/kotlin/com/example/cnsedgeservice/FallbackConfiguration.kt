package com.example.cnsedgeservice

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Mono

@Configuration
class FallbackConfiguration {

    @Bean
    fun routerFunction(): RouterFunction<ServerResponse> = router {
        GET("/catalog-fallback") {
            ok().body(Mono.just("Fallback"))
        }
        POST("/catalog-fallback") {
            status(HttpStatus.SERVICE_UNAVAILABLE).build()
        }
    }
}