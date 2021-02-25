package book.realworld.chap01

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class StatementReaderTest {

    @Test
    @DisplayName("음수 / 양수값을 가져올 수 있는가")
    fun read() {
        val expectSize = 7
        val statementReader = StatementReaderFactory.getStatementReader(ReaderType.CSV)

        val list: List<Statement> = statementReader.readStatements()

        assertEquals(expectSize, list.size)
        assertEquals(-100, list[0].price)
        assertEquals(6000, list[2].price)
    }

}