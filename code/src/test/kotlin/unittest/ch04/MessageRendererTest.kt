package unittest.ch04

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class MessageRendererTest {

    @Test
    fun messageRenderer_uses_correct_sub_renderers() {

        val sut = MessageRenderer()

        val renderer = sut.subRenderers

        assertEquals(1, renderer.size)
        assertTrue(renderer[0] is HeaderRenderer)
    }
}