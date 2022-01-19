package kotlinbook.cookbook.chap02

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class NothingTest {

    fun exception(): Nothing = TODO()


    @Test
    fun nothing() {
        val nullable = null

        assertTrue(nullable is Nothing?)
    }

    @Test
    fun nothingIsSubTypeForAllObject() {
        for (i in 1..10) {
            val x = when (i % 3) {
                0 -> "$i % 3 = 0"
                1 -> "$i % 3 = 1"
                2 -> "$i % 3 = 2"
                else -> throw Exception()
            }
            assertTrue(x is String)
        }
    }
}