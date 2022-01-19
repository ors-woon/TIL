package kotlinbook.cookbook.chap01

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class WrapperTypeTransform {

    @Test
    @DisplayName("wrapper type 변환이 자동으로 안됨")
    fun transform() {
        val num: Int = 5
        //val longNum:Long = num // error !!

    }
}