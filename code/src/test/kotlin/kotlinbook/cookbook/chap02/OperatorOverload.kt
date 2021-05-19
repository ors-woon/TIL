package kotlinbook.cookbook.chap02

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

data class Point(val x: Int, val y: Int)

operator fun Point.unaryMinus() = Point(-x, -y)

class OperatorOverload {

    @Test
    fun unaryMinus() {
        val point = Point(5, 7)
        val expect = Point(-5, -7)

        assertEquals(expect, point.unaryMinus())
    }

}