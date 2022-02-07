package effective.item1

interface Element {
    val active: Boolean
}

class PropertyOverride : Element {
    override var active: Boolean = false
}