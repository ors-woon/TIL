package cookbook.chap01


import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class InfixFunction {

    @Test
    @DisplayName("pair")
    fun pair() {
        val value = "str"
        val map = mapOf("key" to value)

        assertEquals(value, map["key"])
    }

    infix fun Int.isUnder(param: Int): Boolean = this < param

    @Test
    @DisplayName("infix function")
    fun infix() {
        val five = 5
        val six = 6

        assertTrue(five isUnder six)
    }

    class MyWishWatch(val list: MutableList<String> = mutableListOf()) : MutableList<String> by list {
        infix fun addWatch(watch:String) = this.add(watch)
        infix fun `갖고싶다`(watch:String) = this.add(watch)
    }

    @Test
    @DisplayName("객체에 infix 적용")
    fun watch(){
        val watches = MyWishWatch()
        watches addWatch "Oris"
        watches `갖고싶다` "Omega"
        watches addWatch "Rolex"

        assertEquals(3, watches.size)
    }

}