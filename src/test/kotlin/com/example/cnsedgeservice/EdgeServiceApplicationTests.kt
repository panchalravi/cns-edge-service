package com.example.cnsedgeservice

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.GenericContainer
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class EdgeServiceApplicationTests {

    companion object {
        private val REDIS_PORT = 6379

        val redis: GenericContainer<Nothing> = GenericContainer<Nothing>(DockerImageName.parse("redis:6"))
            .withExposedPorts(REDIS_PORT)

        @DynamicPropertySource
        fun redisProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.redis.host") { redis.host}
            registry.add("spring.redis.port") { redis.getMappedPort(REDIS_PORT)}
        }
    }

    @Test
    fun verifyThatSpringContextLoads() {}

}