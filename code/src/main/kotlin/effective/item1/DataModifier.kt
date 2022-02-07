package effective.item1


data class Person(val name: String, val location: Location)

class Location(val address: String)


fun main() {
    val person = Person("chulwoon", Location("korea"))

    // swallow copy 를 함
    // immutable 이라 deep copy 는 안하는것으로 보이며, data 연산자 내부 type 들은 data 로 쓰는게 맞는 듯
    println(person.location)
    println(person.copy(name = "lusiue").location)

}