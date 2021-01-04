package chap01

import org.junit.jupiter.api.Assertions.assertEquals
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

    @Test
    @DisplayName("beanFactory")
    fun beanFactory(){
        val ctx = AnnotationConfigApplicationContext(BeanFactory::class.java)
        val component = ctx.getBean("happyNewYear", HappyNewYear::class)

        assertNotNull(component)

        val config:BeanFactory = ctx.getBean("beanFactory", BeanFactory::class) as BeanFactory
        assertNotNull(config,"beanFactory도 bean 으로 등록된다.")

        // beanFactory 가 bean 으로 등록.
        // beanFactory의 @Bean 함수를 호출하면, spring 에 등록되어있는 빈과 같은 객체가 호출된다.
        assertEquals(config.happyNewYear(), component)
    }
}