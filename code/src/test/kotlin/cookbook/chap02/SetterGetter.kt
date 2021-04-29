package cookbook.chap02

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MonsterHunter(val rank: Int, val weapon: String) {
    var palicoRank = 0
        set(value) {
            //palicoRank = value.coerceIn(1..20)
            field = value.coerceIn(1..20) // backing field
        }

}

class SetterGetter {
    @Test
    fun getterSetter() {
        val monsterHunter = MonsterHunter(18, "Bow")

        assertEquals(18, monsterHunter.rank)
        assertEquals("Bow", monsterHunter.weapon)
    }
}