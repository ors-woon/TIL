```
+++ 
author = "kmplex" 
title = "[Cookbook] 3장" 
date = "2021-04-30" 
description = "3장에서 인상 깊었던 내용들 정리"  
series = ["Kotlin"] 
categories = ["dev"] 
+++
```

[코틀린 쿡북](http://www.yes24.com/Product/Goods/90452827)을 읽고, 인상 깊었던 내용을 정리한다.

> 책의 목차만 동일하며, 내용은 이해한대로 재구성한다.

## 3.4 지원 속성 기법

Property 를 노출하고 싶지만, 초기화 시점을 제어하고 싶을 경우, 아래처럼 내부 property 를 사용 할 수 있다.

> 객체 생성 시점 이후, property를 초기화 하고 싶을때

```kotlin
class Customer(val name: String) {
    private var _messages: List<String>? = null

    val messages: List<String>
        get() {
            if (_messages == null) {
                _messages = loadMessages()
            }
            return _messages!!
        }

    private fun loadMessages(): MutableList<String> =
            mutableListOf("첫번째 줄", "두번째줄")
                    .also { print("로딩 끗") }
}
```

> 호출 코드 

```kotlin
@Test
@DisplayName("지연 초기화 기법")
fun loadMessage() {
    val customer = Customer("chulwoon")

    val message = customer.messages

    assertEquals(2, message.size)
}
```

실제 object 생성 시점엔, message 가 초기화 되지않고 호출 시점에 초기화가 된다.

> lazy 를 사용하여 동일하게 구현 할 수 있다.

```kotlin
val messages: List<String> by lazy { loadMessages() }
```

## 3.5 연산자 중복

operator keyword 를 통해 연산자를 재 정의 할 수 있다.

지원하는 operator 함수 목록은 [공식 문서](https://kotlinlang.org/docs/operator-overloading.html#infix-calls-for-named-functions)를 참고


```kotlin
data class Point(val x: Int, val y: Int)

operator fun Point.unaryMinus() = Point(-x, -y)

@Test
fun unaryMinus() {
    val point = Point(5, 7)
    val expect = Point(-5, -7)

    assertEquals(expect, point.unaryMinus())
}
```

## 여담 : kotlin의 annotation (use-site target)

kotlin의 property 한줄을 Java 로 변환하면, getter / setter / field 등 여러 줄을 생성한다.

> Q. 만약 property에 annotation을 할당하면, 어느 속성에 annotation이 선언될까 ? 

개발자가 이를 명확하게 표시할 수 있게, `use-site target` 이라는 keyword을 제공한다.

```kotlin
class Example(@field:Fancy val foo : String,    // annotate Java field
              @get:TT val bar : Int,      // annotate Java getter
              @param:[Fancy TT] val quux: Any)   // annotate Java constructor parameter
```

지원하는 목록은 아래와 같다.
```
file (package 위에 annotation을 지정할 수 있다.)
property (annotations with this target are not visible to Java)
field
get (property getter)
set (property setter)
receiver (receiver parameter of an extension function or property)
param (constructor parameter)
setparam (property setter parameter)
delegate (the field storing the delegate instance for a delegated property)
```

만약 use-site target을 지정하지 않으면, annotation 지정시 설정한 @Target으로 선언된다.
만약 target 이 여러개인 경우, 아래 목록의 첫번째 Target 이 지정된다.

```
param
property
field
```

> todo test 필요 

## 여담2: KProperty0 ???


## 3.6 나중 초기화를 위해 lateinit 사용하기

널 비허용 속성으로 만들고 싶으나, 초기화 시 정보가 충분하지 않을 경우 lateinit 을 사용 할 수 있다.

> 주의: 의존성 주입의 경우 유용하나, 가능하다면 lazy keyword 같은 대안을 먼저 고려하라.

대표적인 lateinit 사용처는 spring `framework`이다.

```kotlin
class ControllerTest {
    @Autowired
    lateinit var client:WebTestClient
}
```

> 5.2 부턴 @TestConstructor(autowireMode = AutowireMode.ALL) 로 생성자 주입을 쓸 수도 있다.

```
- getter/setter 가 없는 var 속성에만 사용 할 수 있다. (mutable)
- null 할당 불가능한 타입에만 사용가능 
- 기본 타입에는 사용불가
- 사용 전 초기화에 실패하면 Exception 발생
```

> 예시

```kotlin
lateinit var string: String

@Test
fun initCheck() {
    assertFalse(::string.isInitialized)

    string = "helloWord"

    assertTrue(::string.isInitialized)
}
```

> :: 으로 isInitialized 함수 호출 가능


## 3.7  equals 재정의를 위해 안전 타입 변환, 레퍼런스 동등, 엘비스 사용하기


안전 타입 변환 (`as?`)/ 레퍼런스 동등(`==`) / elvis 를 사용하여 equals 를 구현해본다.

> 1. kotlin의 동일(`equivalence`) / 동등(`equality`) 

객체 지향 언어는, 동일(`equivalence`) / 동등(`equality`) 개념이 있다.
Java와는 다르게 Kotlin 에서는 `==` 연산은 동등성을 확인 할 수 있으며, `===`을 사용하여 Java의 `==` 과 같은 연산을 수행 할 수 있다.

> 2. 안전 타입 변환 (`as?`)

nullable property에 casting 을 진행 할 경우, NPE 가 발생할 수 있다.
이를 막고자 safe type casting 을 지원한다. `as?` 

```kotlin
@Test
fun safeTypeCheck(){
    val number:Any? = 5
    val nullable:Any? = null

    assertTrue((number as? Int) is Int)
    // null 이면 elvis 로 지정한 값을 리턴한다.
    assertTrue((nullable as? Int ?: 0) is Int)
}
```

위 방법들을 이용하여, equals 를 구현 할 수 있다.
대표적인 class 가 KotlinVersion 이다.

```kotlin
override fun equals(other: Any?): Boolean {
    if (this === other) return true // equivalence 
    val otherVersion = (other as? KotlinVersion) ?: return false // safe type 
    return this.version == otherVersion.version // equality
}
```

## 3.8 싱글톤 생성하기

kotlin은 object keyword 로 singleton 을 사용 할 수 있다.

```kotlin
object Person(val name:String)
```







