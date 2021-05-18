package cookbook.chap04

import org.junit.jupiter.api.Test

inline fun <T, R> Array<out T>.foldInline(initial: R, operation: (acc: R, T) -> R): R {
    var accumulator = initial
    for (element in this) accumulator = operation(accumulator, element)
    return accumulator
}

inline fun <T, R> Array<out T>.foldNotInline(initial: R, operation: (acc: R, T) -> R): R {
    var accumulator = initial
    //val function = operation
    for (element in this) {
        secondFunction(initial, element, operation)
    }

    return accumulator
}

inline fun <T, R> secondFunction(first: R, second: T, operation: (acc: R, T) -> R) = operation(first, second)


class InlineTest {

    @Test
    fun inline() {
        listOf(2, 6, 8).toTypedArray().foldNotInline(0) { acc, i -> acc + i }

    }
}