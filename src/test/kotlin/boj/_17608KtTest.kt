package boj

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class _17608KtTest {


    @Test
    fun `17068_case1`() {
        val sut = `17608`(mutableListOf(6, 9, 7, 6, 4, 6), 6)

        assertEquals(3, sut.getVisibleStickCountOnRight())
    }

    @Test
    fun `17068_case2`() {
        val sut = `17608`(mutableListOf(5, 4, 3, 2, 1), 5)

        assertEquals(5, sut.getVisibleStickCountOnRight())
    }

}