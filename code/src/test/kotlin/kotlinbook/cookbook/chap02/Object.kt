package kotlinbook.cookbook.chap02

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

object Person {
    val name = "chulwoon"
}

class Object {
    @Test
    fun person() {
        assertEquals("chulwoon", Person.name)
    }
}