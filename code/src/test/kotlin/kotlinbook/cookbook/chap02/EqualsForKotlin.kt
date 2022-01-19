package kotlinbook.cookbook.chap02

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class EqualsForKotlin {
    var first:Int = 123123123
    var two:Int = 234123412
    @Test
    fun equality(){
        assertTrue(first == two)
    }

    @Test
    fun safeTypeCheck(){
        val number:Any? = 5
        val nullable:Any? = null

        assertTrue((number as? Int) is Int)
        assertTrue((nullable as? Int ?: 0) is Int)
    }

    @Test
    fun kotlinVersion(){
        val kotlinVersion:KotlinVersion = KotlinVersion(1,1)
    }
}