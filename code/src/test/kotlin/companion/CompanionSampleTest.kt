package companion

import com.fasterxml.jackson.core.type.TypeReference
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

open class Parent {
    companion object {
        val target = "json"
        val version = 1.0
    }

    open fun getTarget(): String = Companion.target
}

class Child : Parent() {
    companion object {
        val target = "html"
        val version = 1.0
    }

    override fun getTarget() = Companion.target
}

class CompanionSampleTest {

    @Test
    @DisplayName("(1) Companion keyword 를 사용하지않고, 축약하여 사용가능합니다.")
    fun shortcut() {
        val expect = Parent.target

        assertEquals(expect, Parent.target)
    }

    @Test
    @DisplayName("(2) 객체를 변수에 할당 시, Companion 객체가 할당됩니다.")
    fun companion() {
        val expect = Parent

        assertTrue(expect is Parent.Companion)
        assertEquals(expect, Parent.target)
    }

    @Test
    @DisplayName("(3) 상속을 이용할 수 있습니다.")
    fun extendsCompanion() {
        val child = Child

        assertEquals(Child.Companion.target, child.target)
        assertNotEquals("companion 이 상속되진 않습니다.", Parent.Companion.target, child.target)
    }

    @Test
    @DisplayName("(4) 다형성을 이용할 수 있습니다. (companion 의 특징은 아님) ")
    fun polymorphism() {
        val child:Parent = Child()

        assertEquals(Child.Companion.target, child.getTarget())
    }
}