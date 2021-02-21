package book.realworld.chap01

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class StatementReaderTest {

    @InjectMocks
    lateinit var statementReader: StatementReader

    @Test
    fun read() {
        val expectSize = 7

        val list:List<Statement> = statementReader.readStatement()

        assertEquals(expectSize, list.size)
    }

}