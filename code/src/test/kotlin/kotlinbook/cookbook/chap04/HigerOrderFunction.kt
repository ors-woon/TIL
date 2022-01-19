package kotlinbook.cookbook.chap04

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

fun <T> T.printBoolean(predicate: ((T) -> Boolean)?) {
    print(predicate?.invoke(this))
}

class HigerOrderFunction {

    @Test
    @DisplayName("inline 미 사용시, FunctionN 의 익명 객체로 대체된다. (.class)")
    fun notInline() {
        "true".printBoolean { it.toBoolean() }
    }

    @Test
    @DisplayName("inline 미 사용시, FunctionN 의 익명 객체로 대체된다. (.class)")
    fun filter() {
        val predicate: (Int) -> Boolean = { it > 3 }

        // not inline
        listOf(1, 2, 3, 4)

            .filter(predicate)

        //  inline
        listOf(1, 2, 3, 4)
            .filter { it > 3 }

    }
}