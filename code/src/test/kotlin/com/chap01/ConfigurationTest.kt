package com.chap01

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class ConfigurationTest {

    @Test
    fun configuration() {
        val context = AnnotationConfigApplicationContext(Config::class.java)
        val bean = context.getBean(HappyNewYear::class.java) as HappyNewYear

        assertNotNull(bean)
    }
}