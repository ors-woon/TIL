package kotlinbook.cookbook.chap01

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

infix fun String.concatenate(str: String) = "$this $str"
class InfixFunction {

    @Test
    @DisplayName("infix function")
    fun infix() {
        val expect = "my cat is cute"

        assertEquals(expect, "my cat is" concatenate "cute")
    }

    @Test
    @DisplayName("Pair")
    fun pair() {
        val map = mapOf("key" to "value", "key2" to "value")

        assertEquals(2, map.size)
        assertEquals("value", map["key2"])

        val (key, value) = "key" to "value"

        assertEquals("key", key)
        assertEquals("value", value)

    }
}