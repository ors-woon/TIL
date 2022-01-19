package spring.chap01

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.config.RuntimeBeanReference
import org.springframework.beans.factory.support.RootBeanDefinition
import org.springframework.context.support.StaticApplicationContext


class ContainerTest {

    @Test
    @DisplayName("context 에 직접 bean 등록")
    fun testStaticApplicationContext() {
        // given
        val container = StaticApplicationContext()
        container.registerSingleton("hello", Hello::class.java)

        // when
        val hello = container.getBean("hello", Hello::class.java)

        // then
        assertThat(hello, `is`(notNullValue()))
    }


    @Test
    @DisplayName("BeanDefinition 을 이용한 등록")
    fun testBeanDefinition() {
        val container = StaticApplicationContext()
        val def = RootBeanDefinition(Hello::class.java)
        def.propertyValues.addPropertyValue("name", "chulwoon")
        container.registerBeanDefinition("hello", def)

        val hello: Hello = container.getBean("hello", Hello::class.java)

        assertThat(hello, `is`(notNullValue()))
        assertEquals(hello.sayHello(), "Hello chulwoon")
        // 등록된 bean 갯수도 가져올 수 있다.
        assertEquals(container.beanFactory.beanDefinitionCount, 1)

    }

    @Test
    @DisplayName("BenaDefiniton DI")
    fun testDI() {
        // given
        val container = StaticApplicationContext()
        container.registerBeanDefinition("printer", RootBeanDefinition(StringPrinter::class.java))

        val helloDef = RootBeanDefinition(Hello::class.java)
        helloDef.propertyValues.addPropertyValue("name", "chulwoon")
        helloDef.propertyValues.addPropertyValue("printer", RuntimeBeanReference("printer"))
        container.registerBeanDefinition("hello", helloDef)

        // when
        val hello = container.getBean("hello", Hello::class.java)
        hello.print()

        assertEquals(container.getBean("printer").toString(), "Hello chulwoon")
    }
}