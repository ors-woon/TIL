package spring.chap01

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class ConfigurationTest {

    @Test
    fun configuration() {
        val context = AnnotationConfigApplicationContext(Config::class.java)
        val bean = context.getBean(HappyNewYear::class.java) as HappyNewYear
        val beanWrapper = context.getBean(HappyNewYearWrapper::class.java) as HappyNewYearWrapper

        assertNotNull(bean)
        assertEquals(bean, beanWrapper.happyNewYear)
    }

    @Test
    fun component() {
        val context = AnnotationConfigApplicationContext(ConfigWithComponent::class.java)
        val bean = context.getBean(HappyNewYear::class.java) as HappyNewYear
        val beanWrapper = context.getBean(HappyNewYearWrapper::class.java) as HappyNewYearWrapper

        assertNotNull(bean)
        assertNotEquals(bean, beanWrapper.happyNewYear)
    }
}