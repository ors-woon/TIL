package book.inaction

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
}