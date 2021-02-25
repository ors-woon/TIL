package book.realworld.chap01

import org.apache.logging.log4j.util.Strings
import org.springframework.util.CollectionUtils

class Bank {
    fun getMostSpendShop(type: ReaderType): String {
        val reader = StatementReaderFactory.getStatementReader(type)
        val statements = reader.readStatements()
        if (CollectionUtils.isEmpty(statements)) {
            return Strings.EMPTY
        }

        return StatementProcessor(statements).getMostSpendShop()
    }


}