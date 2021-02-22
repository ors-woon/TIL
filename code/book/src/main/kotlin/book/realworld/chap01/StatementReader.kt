package book.realworld.chap01


import book.realworld.util.CsvReader
import book.realworld.util.Log

const val CSV_PATH = "statement.csv"

class StatementReader {
    companion object : Log

    fun readStatement(): List<Statement> {
        val contents = CsvReader.readCsv(CSV_PATH)
        if (contents.isEmpty()) {
            return listOf()
        }

        return contents.map { Statement.from(it) }
    }


}