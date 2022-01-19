package kotlinbook.cookbook.chap02

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/*
    1. 생성자에 val / var 이 없는 경우 ?
     => val / var 을 붙이면, class 내에 property로 사용하겠다는 의미
     => 붙이지 않으면 단순 변수를 넘기는 것으로, init block 이나 다른 property 초기화할때 사용가능하다.
    2. const val 의 차이
     => const 은 modifier keyword (이미 정의된 type 에 추가로 의미를 부여한다.)
     ex ) final, abstract, annotation, public, private ..

     const 는 컴파일 타임에 변수를 할당한다. (함수콜, 생성자로 할당 불가) + getter 생성 불가
     => 객체 최상위 혹은 kotlin.companion 의 멤버로 사용되어야한다.
     때문에 val 과 함께 사용된다.

    3. 사용자 지정 set
*/


class Task @JvmOverloads constructor(val name: String, _priority: Int = DEFAULT_PRIORITY, keyword: String) {
    companion object {
        const val MIN_PRIORITY = 1
        const val MAX_PRIORITY = 5
        const val DEFAULT_PRIORITY = 3
    }

    var priority = validPriority(_priority) // _priority 바로 할당
        set(value) {
            field = validPriority(value)
        }

    private fun validPriority(p: Int) = p.coerceIn(MIN_PRIORITY, MAX_PRIORITY)
}


class ConstVal {
    @Test
    fun task() {
        val expect = 5
        val securityTask = Task("보안 이슈", keyword = "hello")

        securityTask.priority = expect

        assertEquals(expect, securityTask.priority)
    }
}