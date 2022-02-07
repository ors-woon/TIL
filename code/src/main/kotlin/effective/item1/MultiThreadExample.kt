package effective.item1

import kotlin.concurrent.thread

class MultiThreadExample {

    fun run() {
        var num = 0
        for (i in 1..10) {
            thread {
                Thread.sleep(10)
                num += i
            }
        }
        Thread.sleep(500)
        println(num)
    }

}

fun main() {
    val runner = MultiThreadExample()
    runner.run()
}