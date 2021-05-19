package kotlinbook.cookbook.chap01

import chap01.Person
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class NullWithJava {

    @Test
    fun getName() {
        val prefix = "얼죽아:"
        val name = "장철운"
        val person = Person(name, null, 82)

        assertEquals(prefix + name, person.getNameWithPrefix(prefix))
        /*
            freeCompilerArgs = ["-Xjsr305=strict"] 추가 시, 아래 코드가 컴파일 에러 발생
            person.getName(null)
         */
    }
}