package spring.chap01

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext

internal class NotConfigurationFactoryTest {

    @Test
    @DisplayName("not configuration beanFactory")
    fun beanFactory() {
        val ctx = AnnotationConfigApplicationContext(NotConfigurationFactory::class.java)
        val fun1: Map<String, HappyNewYear> = ctx.getBean("fun1", Map::class) as Map<String, HappyNewYear>

        assertNotNull(fun1)

        val fun2: Map<String, HappyNewYear> = ctx.getBean("fun2", Map::class) as Map<String, HappyNewYear>
        assertNotNull(fun2)

        // configuration 을 설정하지 않으면 다른 객체로 인식
        // component 도 마찬가지. -> 메서드 직접 호출시 singleton 보장 x
        assertNotEquals(fun2["1"], fun1["1"])
    }

    @Test
    @DisplayName("not configuration beanFactory ineject")
    fun beanFactoryInject() {
        val ctx = AnnotationConfigApplicationContext(NotConfigurationInject::class.java, NotConfigurationFactory::class.java)
        val fun1: NotConfigurationInject = ctx.getBean("notConfigurationInject", NotConfigurationInject::class) as NotConfigurationInject

        assertNotNull(fun1.test1())
        assertNotNull(fun1.test2())

        // configuration 이 붙지 않은 bean 은 외부에서 함수를 직접 호출해도 다른 객체를 반환함.
        assertNotEquals(fun1.test1(), fun1.test2())
    }


    @Test
    @DisplayName("not configuration beanFactory ineject2")
    fun beanFactoryInject2() {
        val ctx = AnnotationConfigApplicationContext(NotConfigurationFactory::class.java, NotConfigurationFactory::class.java, HappyNewYear::class.java)
        val fun1: HappyNewYear = ctx.getBean("happyNewYear", HappyNewYear::class) as HappyNewYear

        assertNotNull(fun1)

        val fun2: HappyNewYear = ctx.getBean("happyNewYear", HappyNewYear::class) as HappyNewYear

        assertNotNull(fun2)

        // bean 으로 호출시엔 같은 객체 취급.
        assertEquals(fun1, fun2)
    }
}