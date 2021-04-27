package cookbook.chap02

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/*
    1. 생성자에 val / var 이 없는 경우 ?
    2. 사용자 지정 set
    3. const val 의 차이
 */

class Task @JvmOverloads constructor(val name: String, _priority: Int = DEFAULT_PRIORITY, keyword: String) {
    companion object {
        const val MIN_PRIORITY = 1
        const val MAX_PRIORITY = 5
        const val DEFAULT_PRIORITY = 3
    }

    var priority = validPriority(_priority)
        set(value) {
            field = validPriority(value)
        }

    private fun validPriority(p: Int) = p.coerceIn(MIN_PRIORITY, MAX_PRIORITY)
}


class ConstVal {

    @Test
    fun task() {
        val expect = 5
        val securityTask = Task("보안 이슈",keyword = "hello")

        securityTask.priority = expect

        assertEquals(expect, securityTask.priority)
    }
}