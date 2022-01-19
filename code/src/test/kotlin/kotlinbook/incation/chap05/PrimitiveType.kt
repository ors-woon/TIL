package kotlinbook.incation.chap05

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class PrimitiveType {

    @Test
    @DisplayName("kotlin은 primitive 타입을 구분하지 않는다.")
    fun kotlinPrimitive() {
        val num = 1.coerceIn(1..5)

        assertEquals(1, num)
    }
}