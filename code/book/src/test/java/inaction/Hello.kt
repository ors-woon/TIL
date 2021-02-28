package inaction

import com.fasterxml.jackson.core.JsonParser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test



class Hello private constructor(){
    companion object  {
        fun init() = Hello()

        fun hello() = "hello"
    }
}

class World {
    fun world() = Hello.init()
}

object HelloWorld {
    fun helloWorld() = "helloWorld"
}


internal class Test {
    @Test
    fun helloWorldTest() {
        val expect = "helloWorld"

        assertEquals(expect, HelloWorld.helloWorld())
    }
}