package com.chap01

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class PrototypeTest {

    @Test
    @DisplayName("prototype test")
    fun prototype(){
        val ac = AnnotationConfigApplicationContext(ProtoTypeBean::class.java
        , ProtoTypeClientBean::class.java)

        val set = mutableSetOf<ProtoTypeBean>()

        set.add(ac.getBean(ProtoTypeBean::class.java))
        assertEquals(1, set.size)

        set.add(ac.getBean(ProtoTypeBean::class.java))
        assertEquals(2, set.size)

        set.add(ac.getBean(ProtoTypeClientBean::class.java).bean1)
        assertEquals(3, set.size)

        set.add(ac.getBean(ProtoTypeClientBean::class.java).bean2)
        assertEquals(4, set.size)
    }

    @Test
    @DisplayName("prototype test")
    fun prototype_scope(){
        val ac = AnnotationConfigApplicationContext(ProtoTypeBean::class.java)

        val prototype = ac.getBean(ProtoTypeBean::class.java)
        val prototype1 = ac.getBean(ProtoTypeBean::class.java)

        assertNotEquals(prototype, prototype1)
    }

}

