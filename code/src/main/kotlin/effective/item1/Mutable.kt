package effective.item1

fun main() {
    val list = mutableListOf(1, 2, 3)
    list.add(4)

    println(list)

    val property = PropertyExample()

    // val
    // print(property.fullName)
/*
    println("start")
    println(property.fizz)
    println(property.fizz)
    println(property.bug)
    println(property.bug)
*/

}


class PropertyExample() {
    var name = "Big Crown"
    val brand = "Oris"

    val fullName
        get() = "$brand-$name"


    val fizz = calculate()
    val bug
        get() = calculate()

    fun calculate(): Int {
        print("Calculating ..")

        return 42
    }
}

