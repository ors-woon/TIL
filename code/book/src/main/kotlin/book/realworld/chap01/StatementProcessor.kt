package book.realworld.chap01

import org.apache.logging.log4j.util.Strings
import java.time.Month
import java.util.stream.Collectors.groupingBy
import java.util.stream.Collectors.summingInt

enum class SortType {
    DESC,
    ASC,
    ;
}

class StatementProcessor(private val statements: List<Statement> = listOf()) : List<Statement> by statements {
    fun getSortedItemByPrice(limitCount: Int, sortType: SortType): List<Statement> =
            if (SortType.ASC == sortType) {
                statements.sortedBy { it.price }.take(limitCount)
            } else {
                statements.sortedByDescending { it.price }.take(limitCount)
            }

    fun getTotalCost(): Int = statements.filter { it.price < 0 }
            .map { it.price.times(-1) }
            .sum()

    fun getTotalInCome(): Int = statements.filter { it.price > 0 }
            .map { it.price }
            .sum()

    fun getTransactionCount(month: Month): Int = statements.filter { month == it.date.month }.count()

    fun getMostSpendShop(): String {
        val shopMap = statements.stream().collect(groupingBy(Statement::shop, summingInt(Statement::price)))

        return shopMap.maxByOrNull { it.value }?.key ?: Strings.EMPTY
    }
}