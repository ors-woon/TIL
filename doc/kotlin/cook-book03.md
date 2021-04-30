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

operate keyword 를 통해 연산자를 재 정의 할 수 있다.




