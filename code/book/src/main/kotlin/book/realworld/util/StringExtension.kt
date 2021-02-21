package book.realworld.util

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun String.toLocalDate(formatter: DateTimeFormatter) = LocalDate.parse(this, formatter)
