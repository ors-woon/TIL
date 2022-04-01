package effective.item1


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

