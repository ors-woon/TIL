package effective.item1

import org.junit.jupiter.api.Test

class BankAccountTest {

    @Test
    fun withdraw() {
        val account = BankAccount()
        assert(account.balance == 0.0)

        account.deposit(100.0)
        assert(account.balance == 100.0)

        account.withdraw(50.0)
        assert(account.balance == 50.0)
    }
}