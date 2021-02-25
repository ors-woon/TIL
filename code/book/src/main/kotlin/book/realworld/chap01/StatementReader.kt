package book.realworld.chap01


import book.realworld.util.CsvReader
import book.realworld.util.Log
import java.lang.IllegalArgumentException
import java.lang.RuntimeException

const val CSV_PATH = "statement.csv"

enum class ReaderType {
    JSON,
    CSV,
    ;
}

class StatementReaderFactory {
    companion object {
        fun getStatementReader(type: ReaderType): StatementReader {
            if (ReaderType.CSV == type) {
                return StatementCSVReader()
            }

            if (ReaderType.JSON == type) {
                return StatementJsonReader()
            }

            throw IllegalArgumentException()
        }
    }
}


interface StatementReader {
    fun readStatements(): List<Statement>
}


class StatementCSVReader : StatementReader {
    companion object : Log

    override fun readStatements(): List<Statement> {
        val contents = CsvReader.readCsv(CSV_PATH)
        if (contents.isEmpty()) {
            return listOf()
        }

        return contents.map { Statement.from(it) }
    }
}

class StatementJsonReader : StatementReader {
    companion object : Log

    override fun readStatements(): List<Statement> {
        TODO("read Json")
    }
}