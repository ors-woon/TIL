package effective.item1

import kotlin.properties.Delegates

fun main() {
    var names by Delegates.observable(listOf<String>()) { _, old, new ->
        println("changed from $old to $new")
    }

    names += "hellow"
    names += "world"

}