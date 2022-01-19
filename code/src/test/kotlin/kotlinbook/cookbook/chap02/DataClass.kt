package kotlinbook.cookbook.chap02

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class Option(val name: String)

data class Product(var name: String, var price: Double, var onSale: Boolean, var option: Option? = null) {
    var isCoupon = false
}


class DataClass {
    @Test
    fun dataClass() {
        val product = Product("옷", 1000.0, false)
        product.isCoupon = true

        val notCouponProduct = Product("옷", 1000.0, false)
        product.isCoupon = false

        assertTrue(product == notCouponProduct)
    }

    @Test
    fun copy() {
        val product = Product("옷", 1000.0, false)
        product.isCoupon = true

        val notCouponProduct = product.copy()

        assertTrue(product == notCouponProduct)
        assertFalse(notCouponProduct.isCoupon)
    }

    @Test
    fun copy_swallowCopy() {
        val product = Product("옷", 1000.0, false, Option("색상"))
        val notCouponProduct = product.copy()

        assertFalse(product === notCouponProduct, "복사한 객체는 다른 객체")
        assertTrue(product.option === notCouponProduct.option, "내부 object 는 같은 참조")
    }

    @Test
    fun component() {
        val product = Product("옷", 1000.0, false, Option("색상"))
        val (name, price, onSale, option) = product


        assertTrue(name== product.name)
        assertTrue(price== product.price)
    }
}