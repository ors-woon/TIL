package spring.chap01

class Hello {
    var name: String = ""
    var printer: Printer? = null

    fun sayHello() = "Hello $name"

    fun print() = printer?.print(sayHello())
}

interface Printer {
    fun print(message: String): Unit
}

class StringPrinter : Printer {
    val buffer = StringBuffer()
    override fun print(message: String) {
        buffer.append(message)
    }

    override fun toString():String = buffer.toString()

}

class ConsolePrinter : Printer {
    override fun print(message: String) {
        print(message)
    }
}



