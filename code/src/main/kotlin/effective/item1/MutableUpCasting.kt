package effective.item1


class User {
    private val users: MutableMap<Int, String> = mutableMapOf()

    // anti
    fun load(): MutableMap<Int, String> = users


    fun loadCaseA(): MutableMap<Int, String> = users.toMutableMap()

    fun loadCaseB(): Map<Int, String> = users


}
