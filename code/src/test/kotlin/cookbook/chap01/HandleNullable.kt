package cookbook.chap01

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class HandleNullable {

    @Test
    @DisplayName("not null 단언 연산자 case")
    fun handle() {
        // code smell
        try {
            val nullableList: List<String> = nullableMap(isEmpty = true)!!
            fail()
        } catch (ex: NullPointerException) {
            // success
        }
    }

    @Test
    @DisplayName("safe call case")
    fun handleNullable() {
        val nullableList: List<String>? = nullableMap(isEmpty = true)

        assertEquals(null, nullableList?.map { it to it.length }?.size,
                "변수가 null 이 아니면 수행, null 이면 null return")
    }

    @Test
    @DisplayName("safe call + Elvis case")
    fun elvis() {
        val nullableList: List<String>? = nullableMap(isEmpty = true)

        val notNullMap: List<Pair<String, Int>> = nullableList?.map { it to it.length } ?: emptyList()
        assertEquals(0, notNullMap.toMap().size)
    }

    fun nullableMap(isEmpty: Boolean): List<String>? = if (!isEmpty) {
        mutableListOf("hi", "hello", "안녕")
    } else {
        null
    }
}