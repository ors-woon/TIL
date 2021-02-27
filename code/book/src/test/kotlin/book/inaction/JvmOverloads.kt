package book.inaction

import kotlin.jvm.JvmOverloads

class JvmOverloads(private val str: String) {

    fun hello(prefix: String = "hello") = "$prefix ${this.str}"

    @JvmOverloads
    fun helloJvm(prefix: String = "hello") = "$prefix ${this.str}"
}