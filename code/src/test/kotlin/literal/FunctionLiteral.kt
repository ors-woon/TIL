package literal


import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

val lambdaLiteral = { first: Int, second: Int -> first + second }
val lambdaLiteralShortcut: (Int) -> Int = { it.times(2) }
val anonymousLiteral = fun(x: Int, y: Int) = x + y

fun String.receiverA(block: String.(Int) -> Int) : Int {
    val a = 5
    return block(a)
}

val receiver = fun(block: String.() -> Int): Int {
    val receiverStr = "5"
    return block(receiverStr)
}

fun buildString(buildAction: StringBuilder.(Int, Int) -> Unit): String {
    val sb = StringBuilder()
    buildAction(sb, 5, 5) // 수신 객체랑 파람 둘다 넘겨야해?
    return sb.toString()
}


class FunctionLiteral {

    @Test
    fun sum() {
        val first = 5
        val second = 5

        assertEquals(first + second, lambdaLiteral(first, second))
        assertEquals(first + second, anonymousLiteral(first, second))
    }

    @Test
    fun receiver() {
        val s = buildString { first: Int, second: Int ->
            append(first)
            append(second)
        }

        println(s)
    }

    @Test
    fun receiverBot() {
        val receiverInt = receiver {
            toInt()
        }

        assertEquals(5, receiverInt)

        print("2".receiverA {
            toInt() + it
        })

    }
}