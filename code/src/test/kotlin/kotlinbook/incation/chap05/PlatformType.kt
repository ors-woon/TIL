package kotlinbook.incation.chap05

import coverage.Person
import org.junit.jupiter.api.Test


class PlatformType {

    fun upper(person: Person) {
        println(person.name.toUpperCase() + "!!")
    }


    @Test
    fun upper(){
        upper(Person(null))
    }
}