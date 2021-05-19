package kotlinbook.cookbook.chap04

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ReduceTest {

    @Test
    fun reduce() {
        val result = arrayOf(1, 4, 8).reduce { acc, item ->
            acc * item
        }
        assertEquals(32, result)
    }



    @Test
    fun reduce_badCase() {
        val result = arrayOf(1, 3, 7).reduce { acc, item ->
            acc + (2 * item)
        }
        assertEquals(21, result)
    }

}