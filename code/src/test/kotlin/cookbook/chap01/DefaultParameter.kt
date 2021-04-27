package cookbook.chap01

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CustomMap<T, R> @JvmOverloads constructor(val mutableMap: MutableMap<T, R> = mutableMapOf()) : MutableMap<T, R> by mutableMap

@JvmOverloads
fun <T, R> CustomMap<T, R>.add(key: T, value: R, mergeFunction: (R, R) -> R = { first, second -> first }, notMeanParam: String): Map<T, R> {
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
    @DisplayName("[Default Param] 순서를 보정해주진 않음")
    fun defaultParameter() {
        val keyboardGroupCountry = CustomMap(mutableMapOf("japan" to "HHKB", "korea" to "한성"))

        // compile error
        // keyboardGroupCountry.add("japan", "real-force", "notMean")
        keyboardGroupCountry.add(key = "japan", value = "real-force", notMeanParam = "notMean")

        assertEquals(2, keyboardGroupCountry.size)
        assertEquals("HHKB", keyboardGroupCountry["japan"])
    }


}