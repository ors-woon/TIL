package etc

import java.util.*

fun main() {
    val collection = CollectionTest()

    // error !
    //collection.rwList += "hello"
    collection.roList += "hello"


}

class PropertiesTest {
    var millis: Long = 0

    // backing field 없이 get / set 사용 시, field 가 정의되지 않는다.
    var date: Date
        get() {
            // 함수로만 사용된다.
            // print(field)
            return Date(millis)
        }
        set(value) {
            millis = value.time
        }
}