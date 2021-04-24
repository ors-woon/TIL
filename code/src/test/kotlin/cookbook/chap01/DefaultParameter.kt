package cookbook.chap01

import chap01.Person
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

fun buildPerson(name: String, age: String, weight: Int = 0) = Person(name, age, weight)

@JvmOverloads
fun buildPersonSupportedJava(name: String, age: String? = null, weight: Int = 0) = Person(name, age, weight)

// default param 을 java 에서 지원하기 위해, constructor 를 사용해야함
class Coffee @JvmOverloads constructor(val name: String, val hasCaffeine: Boolean = true)


class DefaultParameter {

    @Test
    @DisplayName("오른쪽 마지막 parameter 부터 default param 설정")
    fun defaultParameter() {
        val name = "장철운"
        val age = "28"
        val person = buildPerson(name, age)

        assertEquals(name, person.name)
        assertEquals(age, person.age)
        assertEquals(0, person.weight)
    }


}