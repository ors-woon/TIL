package kotlinbook.incation.chap05

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


fun String?.isCustomBlank() = this == null || this.isEmpty()

fun String.isNonnullCustomBlank() = this.isEmpty()


class NullableExtendsFunction {

    @Test
    @DisplayName("nullable extends function 을 nonnull type 에서 호출 할 수 있다.")
    fun extendsNullableFunction() {
        val nullableStr: String? = null
        val nonnullStr: String = "hello"

        assertTrue(nullableStr.isCustomBlank())
        assertFalse(nonnullStr.isCustomBlank())

    }

    @Test
    @DisplayName("nonnull extends function 을 nullable type 에서 null check 없인 호출 할 수 없다")
    fun extendsNonnullFunction() {
        val nullableStr: String? = null
        val nonnullStr: String = "hello"

        assertTrue(nullableStr?.isNonnullCustomBlank() ?: true)
        assertFalse(nonnullStr.isNonnullCustomBlank())

    }
}