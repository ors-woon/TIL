package book.realworld.chap01

import book.realworld.util.toLocalDate
import java.lang.RuntimeException
import java.time.LocalDate
import java.time.format.DateTimeFormatter

const val COLUMN_SIZE = 3
const val DELIMITER = ","
val DATE_TIME_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")

data class Statement(val date: LocalDate, val price: Int, val shop: String) {
    companion object {
        fun from(stringLow: String): Statement {
            val array = stringLow.split(DELIMITER)

            if (array.size != COLUMN_SIZE) {
                throw RuntimeException()
            }

            return Statement(array[0].toLocalDate(DATE_TIME_FORMATTER), array[1].toInt(), array[2])
        }
    }

}