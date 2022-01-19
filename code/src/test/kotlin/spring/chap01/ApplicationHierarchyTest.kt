package spring.chap01

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader
import org.springframework.context.ApplicationContext
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.GenericXmlApplicationContext

class ApplicationHierarchyTest {

    @Test
    fun testHierarchy() {
        val parent: ApplicationContext = GenericXmlApplicationContext("/parentContext.xml")

        assertNotNull(parent.getBean("printer"))

        val child = GenericApplicationContext(parent)
        val reader = XmlBeanDefinitionReader(child)
        reader.loadBeanDefinitions("/childContext.xml")
        // reader 로 읽은 후, refresh 를 사용해야한다.
        child.refresh()

        assertNotNull(child.getBean("printer"), "계층 구조를 이루고 있어, 부모 printer 를 조회한다.")
        assertEquals(parent.getBean("printer"), child.getBean("printer"))
        assertEquals("Hello child", child.getBean("hello", Hello::class.java).sayHello(), "우선순위 테스트")
    }


}