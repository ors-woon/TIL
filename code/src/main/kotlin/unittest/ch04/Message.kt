package unittest.ch04

interface IRenderer {
    fun render(): String
}

class MessageRenderer(val list: List<IRenderer> = listOf()) {
    val subRenderers: MutableList<IRenderer> = mutableListOf()

    init {
        if (list.isNotEmpty()) {
            subRenderers.addAll(list)
        }

        subRenderers.add(HeaderRenderer())
    }

    fun render(): String = subRenderers.joinToString("\n") { it.render() }
}

class HeaderRenderer : IRenderer {
    override fun render(): String = "header"
}
