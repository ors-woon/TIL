package kotlinbook.literal


import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

val lambdaLiteral = { first: Int, second: Int -> first + second }
val lambdaLiteralShortcut: (Int) -> Int = { it.times(2) }
val anonymousLiteral = fun(x: Int, y: Int) = x + y

fun String.receiverA(block: String.(Int) -> Int): Int {
    val a = 5
    return block(a)
}

val receiver = fun(receiverStr: String, block: String.() -> Int): Int {
    return block(receiverStr)
}

fun buildString(buildAction: StringBuilder.(Int, Int) -> Unit): String {
    val sb = StringBuilder()
    buildAction(sb, 5, 5) // 수신 객체랑 파람 둘다 넘겨야해?
    return sb.toString()
}


class FunctionLiteral {

    @Test
    @DisplayName("(1) lambda ")
    fun lambda() {
        val first = 1
        val second = 2
        val expect = first + second

        val add = { s1: Int, s2: Int -> s1 + s2 }

        assertEquals(expect, add(first, second))
    }

    @Test
    @DisplayName("(2) lambda shortcut")
    fun sortLambda() {
        val num = 5
        val expect = 10
        val multiply: (number: Int) -> Int = { it.times(2) }

        val actual = multiply(num)

        assertEquals(expect, actual)
    }

    fun addSuffix(host: String, suffix: (str: String) -> String) = host + suffix(host)


    @Test
    @DisplayName("(3) lambda parameter")
    fun lambdaParam() {
        val origin = "https://github.com"
        val suffix: (str: String) -> String = { "-$it" }

        // (1)
        val expect = origin + suffix(origin)

        val actual = addSuffix(origin, suffix)

        assertEquals(expect, actual)

        // (2) addSuffix 마지막 타입으로, compiler 가 it 유추 가능
        val actual2 = addSuffix(origin) { "-$it" }

        assertEquals(expect, actual2)
    }

    @Test
    fun let() {
        val number: Int? = null

        // number.times(2) // compile error

        val multi = number?.let {
            it.times(2)
        } ?: 0

        assertEquals(0, multi)
    }

    class Person(var name: String = "", var language: String = "") {

        private fun printName() = print(name)
    }

    @Test
    fun with() {
        val name = "chulwoon"
        val myLanguage = "java"
        val personA = Person()
        val person: Person = with(personA) {
            this.name = name
            language = myLanguage
            // printName()
            this
        }

        assertEquals(personA, person)
        assertEquals(name, person.name)
        assertEquals(myLanguage, person.language)
    }

    @Test
    @DisplayName("also 의 결과, 전 / 후 참조가 바뀌지않는다.")
    fun also() {
        val personA = Person()

        val person = personA.also {
            it.name = "hello"
        }

        assertEquals(personA, person, "참조가 바뀌지 않는다")
    }

    @Test
    fun apply() {
        val person = Person().apply {
            name = "hello"
        }
        assertNotNull(person)
    }

    fun addNum(a: Int): Int = a + 5


    @Test
    fun run() {

        val person = Person().run {
            name = "hello"
            this.name
            name
        }
        val function = run {
            addNum(5)
        }

        assertEquals(person, "hello")
        assertNotNull(person)
        assertEquals(10, function)
    }

    @Test
    fun takeIf() {
        val person = Person().run {
            name = "hello"
            name
        }

        assertEquals(person, "hello")
        assertNotNull(person)

    }


    @Test
    fun sum() {
        val first = 5
        val second = 5

        assertEquals(first + second, lambdaLiteral(first, second))
        assertEquals(first + second, anonymousLiteral(first, second))
    }

    @Test
    fun receiver() {
        val s = buildString { first: Int, second: Int ->
            append(first)
            append(second)
        }

        println(s)
    }

    @Test
    fun receiverBot() {
        val receiverInt = receiver("5") {
            toInt()
        }

        assertEquals(5, receiverInt)

        print("2".receiverA {
            toInt() + it
        })

    }
}