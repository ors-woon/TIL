package kotlinbook.cookbook.chap04

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.math.BigInteger

class FoldTest {

    @Test
    fun fold() {
        val list = listOf(1, 5, 7, 9)

        val sum = list.fold(0) { init, element -> init + element }

        assertEquals(sum, 22)
        assertEquals(sum, sum(*list.toIntArray()))

    }

    fun sum(vararg nums: Int) =
        nums.fold(0) { acc, n -> acc + n }

    @Test
    fun factorial() {
        val fiveFactorial = recursiveFactorial(5)

        assertTrue(120 == fiveFactorial.intValueExact())
        assertEquals(recursiveFactorialFold(5), fiveFactorial)
    }

    fun recursiveFactorial(n: Long): BigInteger =
        when (n) {
            0L, 1L -> BigInteger.ONE
            else -> BigInteger.valueOf(n) * recursiveFactorial(n - 1)
        }

    fun recursiveFactorialFold(n: Long): BigInteger =
        when (n) {
            0L, 1L -> BigInteger.ONE
            else -> (2..n).fold(BigInteger.ONE) { init, acc -> init * BigInteger.valueOf(acc) }
        }

    fun fibonarcciFold(n: Int) =
        (2 until n).fold(1 to 1) { pair, _ ->
            pair.second to (pair.first + pair.second)
        }.second



}