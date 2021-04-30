package cookbook.chap02


import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class Customer(val name: String) {
    private var _messages: List<String>? = null

    val messages: List<String>
        get() {
            if (_messages == null) {
                _messages = loadMessages()
            }
            return _messages!!
        }

    private fun loadMessages(): MutableList<String> =
            mutableListOf("첫번째 줄", "두번째줄")
                    .also { print("로딩 끗") }
}

class CustomerWithLazy(val name: String) {
    val messages: List<String> by lazy { loadMessages() }

    private fun loadMessages(): MutableList<String> =
            mutableListOf("첫번째 줄", "두번째줄")
                    .also { print("로딩 끗") }
}


class Property {

    @Test
    @DisplayName("지연 초기화 기법")
    fun loadMessage() {
        val customer = Customer("chulwoon")

        val message = customer.messages

        assertEquals(2, message.size)
    }

    @Test
    @DisplayName("지연 초기화 기법 - Lazy")
    fun loadMessageLazy() {
        val customer = CustomerWithLazy("chulwoon")

        val message = customer.messages

        assertEquals(2, message.size)
    }
}