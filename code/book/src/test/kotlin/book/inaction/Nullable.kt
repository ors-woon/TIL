package book.inaction

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

data class Nullable(val name: String, var age: String?)

class NullableTest {

    @Test
    fun nullableTest() {
        val nullable = Nullable(name = "kmplex", age = null)

        if (nullable.age != null) {
            // compile error
            //assertNotNull(nullable.age.length)
        }
    }


    @Test
    fun nullableTestWithLet() {
        val defaultLength = 5
        val nullable = Nullable(name = "kmplex", age = "hello")

        val actual = nullable.age?.let {
            // 이렇게 단순하게는 사용 안함.
            it.length
        } ?: defaultLength

        assertEquals(5, actual)
    }
}