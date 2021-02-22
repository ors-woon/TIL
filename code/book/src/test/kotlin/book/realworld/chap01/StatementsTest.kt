package book.realworld.chap01

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.Month

/*
    총 수입과 총 지출은 얼마인가 ? Ok
    특정 달엔 몇건의 입출금 내역이 발생했는가 ?
    지출이 가장 높은 상위 10건은 무엇인가 ? ok
    특정 달엔 몇건의 입출금 내역이 발생했는가 ? ok
    돈을 가장 많이 소비하는 항목은 무엇인가 ?
 */

/*
    어디까지 테스트를 짜야하는가 ?
    -> 사람의 실수가 있을 수 있는 부분들은 다 짜는게 맞는거 같음.
    -> 일단 100% 가 아니더라도, 근접하게 짜는게 맞는거 같다.
    (내가 어디까지 실수를 안할 수 있지? 를 보장하기 어려울거 같음.)
 */
internal class StatementsTest {
    val now = LocalDate.now()

    @Test
    @DisplayName("sort by ascending price")
    fun getSortedList() {
        val limitItem = 10
        val priceFixture: List<Statement> = (1..15).map { Statement(now, it, "상점") }
        val statements = Statements(priceFixture)

        val actual = statements.getSortedItemByPrice(limitItem, SortType.ASC)

        // 그럼 모든 항목들을 다 테스트해야할까 ?
        assertEquals(limitItem, actual.size)
        assertEquals(1, actual[0].price)
        assertEquals(2, actual[1].price)
        assertEquals(10, actual[9].price)
    }

    @Test
    @DisplayName("sort by descending price")
    fun getSortedListDesc() {
        val limitItem = 10
        val priceFixture: List<Statement> = (1..15).map { Statement(now, it, "상점") }
        val statements = Statements(priceFixture)

        val actual = statements.getSortedItemByPrice(limitItem, SortType.DESC)

        assertEquals(limitItem, actual.size)
        assertEquals(15, actual[0].price)
        assertEquals(14, actual[1].price)
        assertEquals(6, actual[9].price)
    }

    @Test
    @DisplayName("총 소비액을 구한다.")
    fun getTotalCost() {
        val expectPrice = 30

        val fixture = (0..10)
                .map { if (it % 2 == 0) -it else it }
                .map { Statement(now, it, "소비") }
        val statements = Statements(fixture)

        assertEquals(expectPrice, statements.getTotalCost())
    }

    @Test
    @DisplayName("총 수을 구한다.")
    fun getTotalIncome() {
        val expectPrice = 25

        val fixture = (0..10)
                .map { if (it % 2 == 0) -it else it }
                .map { Statement(now, it, "소비") }
        val statements = Statements(fixture)

        assertEquals(expectPrice, statements.getTotalInCome())
    }

    @DisplayName("6월에 몇번의 입출금 내역이 발생했는가")
    @Test
    fun getTransactionCount() {
        val expectSize = 10
        val februaryDate = LocalDate.of(2021, Month.FEBRUARY, 10)
        val fixture = (1..10)
                .map { Statement(februaryDate, it, "소비") }
        val statements = Statements(fixture)

        val februaryCount = statements.getTransactionCount(Month.FEBRUARY)

        assertEquals(expectSize, februaryCount)

        val zeroCount = statements.getTransactionCount(Month.JANUARY)

        assertEquals(0, zeroCount)
    }


}