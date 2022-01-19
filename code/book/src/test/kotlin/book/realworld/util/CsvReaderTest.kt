package book.realworld.util

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


const val CSV_PATH = "statement.csv"

internal class CsvReaderTest{
    @Test
    fun read() {
        val expectSize = 7

        val lists = CsvReader.readCsv(CSV_PATH)

        assertNotNull(lists)
        assertEquals(expectSize, lists.size)
    }
}