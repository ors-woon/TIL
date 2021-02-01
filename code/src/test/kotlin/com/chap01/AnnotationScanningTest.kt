package com.chap01

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
open class Trevi()

class AnnotationScanningTest {

    @Test
    @DisplayName("spring bean scanner example")
    fun beanScanning() {
        val ctx = AnnotationConfigApplicationContext("chap01")
        val trevi1 = ctx.getBean("trevi", Trevi::class)
        val trevi2 = ctx.getBean("trevi", Trevi::class)

        assertNotNull(trevi1)
        assertNotNull(trevi2)

        assertEquals(trevi1, trevi2)
    }

    @Test
    @DisplayName("spring bean scanner example")
    fun beanScanning_prototype() {
        val ctx = AnnotationConfigApplicationContext(ProtoTypeBean::class.java)

        val protoTypeBean = ctx.getBean("protoTypeBean", ProtoTypeBean::class)
        val protoTypeBean1 = ctx.getBean("protoTypeBean", ProtoTypeBean::class)

        assertNotNull(protoTypeBean)
        assertNotNull(protoTypeBean1)

        assertNotEquals(protoTypeBean, protoTypeBean1)
    }


    @Test
    @DisplayName("beanFactory")
    fun beanFactory() {
        val ctx = AnnotationConfigApplicationContext(BeanFactory::class.java)
        val component = ctx.getBean("happyNewYear", HappyNewYear::class)

        assertNotNull(component)

        val config: BeanFactory = ctx.getBean("beanFactory", BeanFactory::class) as BeanFactory
        assertNotNull(config, "beanFactory도 bean 으로 등록된다.")

        // beanFactory 가 bean 으로 등록.
        // beanFactory의 @Bean 함수를 호출하면, spring 에 등록되어있는 빈과 같은 객체가 호출된다.
        assertEquals(config.happyNewYear(), component)
    }
}