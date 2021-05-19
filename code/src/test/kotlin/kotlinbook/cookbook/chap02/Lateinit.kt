package kotlinbook.cookbook.chap02

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

annotation class Fancy
annotation class TT

class Example(@field:Fancy val foo : String,    // annotate Java field
              @get:TT val bar : Int,      // annotate Java getter
              @param:[Fancy TT] val quux: Any)   // annotate Java constructor parameter



fun @receiver:[Fancy TT] String.myExtension() = this

class Lateinit {
    lateinit var string: String

    @Test
    fun receiverTest() {
        assertEquals("a", "a".myExtension())
    }


    @Test
    fun initCheck() {
        assertFalse(::string.isInitialized)

        string = "helloWord"

        assertTrue(::string.isInitialized)
    }
}