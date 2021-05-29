package kotlinbook.incation.chap05

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

fun <T> printHashCode(t: T) {
    println(t.hashCode())
}

fun <T: Any> printHashCodeNotNull(t: T) {
    println(t.hashCode())
}

class TypeParameter {

    @Test
    fun typeParamNull() {
        printHashCode(null)
    }

    @Test
    fun numberAutoCasting(){
        val x:Int = 1
        val list = listOf<Long>(1L,2L,3L)

        assertTrue(x in list)
    }
}