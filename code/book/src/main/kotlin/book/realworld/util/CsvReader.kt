package book.realworld.util

import book.realworld.chap01.CSV_PATH
import book.realworld.chap01.StatementReader
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.io.ClassPathResource
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

class CsvReader {
    companion object : Log {
        fun readCsv(filePath: String): List<String> {
            val resource = ClassPathResource(filePath)
            return try {
                val path: Path = Paths.get(resource.uri)
                Files.readAllLines(path)
            } catch (e: IOException) {
                log.error(e.message, e)
                listOf()
            }
        }
    }

}