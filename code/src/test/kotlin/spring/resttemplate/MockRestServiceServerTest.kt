package spring.resttemplate


import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.RequestEntity
import org.springframework.test.web.client.MockRestServiceServer
import org.springframework.test.web.client.match.MockRestRequestMatchers.*
import org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

const val url = "http://naver.com"

class MockRestServiceServerTest {

    @Test
    fun rest() {
        // setup
        val restTemplate = RestTemplate()
        val mockServer = MockRestServiceServer.bindTo(restTemplate)
            .build()

        // mock
        val expect = "response"
        mockServer.expect(requestTo(url))
            .andExpect(method(HttpMethod.GET))
            .andExpect(header("MyHeader", "header"))
            .andRespond(withSuccess(expect, MediaType.TEXT_PLAIN))

        // httpCall
        val uri = UriComponentsBuilder.fromHttpUrl(url).build().toUri()
        val request = RequestEntity.get(uri)
            .header("MyHeader", "header")
            .build()

        val actual = RestTemplate().exchange(request, String::class.java)

        mockServer.verify()
        assertEquals(expect, actual.body)


    }
}