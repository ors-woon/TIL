package com

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.client.RestTemplate

@SpringBootApplication
open class Application



fun main(args: Array<String>) {
    runApplication<Application>(*args)

}