package cookbook.chap04

import org.junit.jupiter.api.Test

fun <T> Iterable<T>.forEachNotInline(action: (T) -> Unit): Unit {
    for (element in this) action(element)
}


class NonLocalReturn {

    @Test
    fun nonLocal(){
        listOf(1,4,7).forEach {
            if( it == 1){
                return
            }
        }
        println("안녕")
    }

    @Test
    fun nonLocalNotInline(){
        listOf(1,4,7).forEachNotInline label@{
            if( it == 1){
                return@label
            }
        }
        println("안녕")

    }


    @Test
    fun nonLocalNotInlineFun(){
        listOf(1,4,7).forEachNotInline(fun(it:Int){
            if( it == 1){
                return
            }
        })
        println("안녕")
    }
}