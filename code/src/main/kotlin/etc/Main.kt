package etc

fun main() {
    val collection = CollectionTest()

    // error !
    //collection.rwList += "hello"
    collection.roList += "hello"


}