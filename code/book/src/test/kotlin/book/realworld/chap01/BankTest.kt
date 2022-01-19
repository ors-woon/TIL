package book.realworld.chap01

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class BankTest {

    @Test
    fun getMostSpendShop(){
        val expect = "Salary"
        val bank = Bank()

        val actual = bank.getMostSpendShop(ReaderType.CSV)

        assertEquals(expect,actual)
    }

}