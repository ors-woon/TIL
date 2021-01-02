package boj

import chap01.HappyNewYear
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class AnnotationScanningTest {

    @Test
    @DisplayName("bean scanner example")
    fun beanScanning(){
        val ctx = AnnotationConfigApplicationContext("chap01")
        val component = ctx.getBean("happyNewYear", HappyNewYear::class)

        assertNotNull(component)
    }
}