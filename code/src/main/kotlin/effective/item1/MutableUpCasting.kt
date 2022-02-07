package effective.item1


class User {
    private val users: MutableMap<Int, String> = mutableMapOf()

    // anti
    fun load(): MutableMap<Int, String> = users


    fun loadCaseA(): MutableMap<Int, String> = users.toMutableMap()

    fun loadCaseB(): Map<Int, String> = users


}

fun main() {
    val user = User()

    val map = user.load()
    map[1] = "me"

    println(user.load())

    val mutableA = user.loadCaseA()

    mutableA[2] = "you"
    assert(user.load() != mutableA)

    val immutable = user.loadCaseB()

    // not implement
    // immutable[3] = "we"
}