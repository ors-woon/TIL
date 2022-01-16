package unittest.ch04

class Message(val header: String, val body: String, val footer: String)


interface IRenderer {
    fun render(message: Message): String
}

class MessageRenderer : IRenderer {
    val subRenderers: MutableList<IRenderer>

    init {
        subRenderers = mutableListOf()
        subRenderers.add(HeaderRenderer())
    }

    override fun render(message: Message): String = subRenderers.joinToString("\n") { it.render(message) }
}

class HeaderRenderer : IRenderer {
    override fun render(message: Message): String = "header"
}
