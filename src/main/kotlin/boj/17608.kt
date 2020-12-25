package boj

import java.util.*

/**
 * N개의 막대기에 대한 높이 정보가 주어질 때,
 * 오른쪽에서 보아서 몇 개가 보이는지를 알아내는 프로그램을 작성하려고 한다.
 */
fun main(args: Array<String>) {
    val sut = `17608`()
    sut.input()
    print(sut.getVisibleStickCountOnRight())
}

class `17608`(private var sticks: MutableList<Int> = mutableListOf(),
              private var inputCount: Int = 0) {

    fun input() {
        val input = Scanner(System.`in`)
        inputCount = input.nextInt()

        (1..inputCount).forEach { _ ->
            sticks.add(input.nextInt())
        }
    }

    fun getVisibleStickCountOnRight(): Int {
        val reversedStick = sticks.reversed()
        var visibleCount = 1 // 첫번째 stick 은 무조건 보인다.
        var tmp = reversedStick[0]
        reversedStick.forEach {
            if (tmp >= it) {
                return@forEach
            }
            visibleCount++
            tmp = it
        }

        return visibleCount
    }

}