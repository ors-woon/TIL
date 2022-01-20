package unittest.ch04

interface IRenderer {
    fun render(): String
}

class MessageRenderer {
    val subRenderers: MutableList<IRenderer> = mutableListOf()

    init {
        subRenderers.add(HeaderRenderer())
    }

    fun render(): String = subRenderers.joinToString("\n") { it.render() }
}

class HeaderRenderer : IRenderer {
    override fun render(): String = "header"
}
