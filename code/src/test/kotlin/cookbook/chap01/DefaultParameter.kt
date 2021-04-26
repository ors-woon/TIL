package cookbook.chap01

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CustomMap<T, R> @JvmOverloads constructor(val mutableMap: MutableMap<T, R> = mutableMapOf()) : MutableMap<T, R> by mutableMap

@JvmOverloads
fun <T, R> CustomMap<T, R>.add(key: T, value: R, mergeFunction: (R, R) -> R = { first, second -> first }): Map<T, R> {
    val isExistKey = this[key] != null
    if (isExistKey) {
        val mergeValue = mergeFunction(this.getValue(key), value)
        this.plus(key to mergeValue)
        return this
    }

    this.plus(key to value)
    return this

}

class DefaultParameter {

    @Test
    @DisplayName("오른쪽 마지막 parameter 부터 default param 설정")
    fun defaultParameter() {
        val keyboardGroupCountry = CustomMap(mutableMapOf("japan" to "HHKB", "korea" to "한성"))

        keyboardGroupCountry.add("japan", "real-force")

        assertEquals(2, keyboardGroupCountry.size)
        assertEquals("HHKB", keyboardGroupCountry["japan"])
    }


}