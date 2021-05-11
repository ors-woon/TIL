package spring.resttemplate

import org.junit.jupiter.api.Test
import org.springframework.test.web.client.MockRestServiceServer
import org.springframework.web.client.RestTemplate

class MockRestServiceServerTest {

    @Test
    fun rest(){
        MockRestServiceServer.bindTo(RestTemplate())

    }
}