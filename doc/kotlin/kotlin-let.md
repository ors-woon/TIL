```
+++ 
author = "kmplex" 
title = "kotlin let" 
date = "2021-03-06" 
description = "kotlin standard function"  
series = ["Kotlin"] 
categories = ["dev"] 
+++
```

# kotlin Standard function 

* kotlin 에는 @FunctionalInterface 와 유사한 편의 함수를 제공한다.
    * 확장함수로 구현되어 있으며, 모든 객체에서 사용 가능하다.
    * let, with, also, apply, run, takeIf 만 정리한다.

## 들어가며

* Kotlin 에서 function 은 first citizen 이다.
    * 변수에 함수를 할당 할 수 있으며, 파라미터, 리턴 값이 될 수 있다.
* Literal 은 소스 코드 내에 고정 값을 표현하는 표기법이다.
    * 변수에 값을 할당하는 표기법.
    * 변수에 함수를 할당 할 수 있으므로, Function Literal 이라는 개념이 추가(?)된다.

```kotlin
// lambda 를 이용한 function literal 
val lambdaLiteral: (Int, Int) -> Int = { first: Int, second: Int -> first + second }
// 익명 함수를 이용한 function literal
val anonymousLiteral = fun(x: Int, y: Int) = x + y

// 변수가 하나일 경우, 람다 축약
val lambdaLiteral:(Int) -> Int = { it.times(2) }
```

## 수신 객체 (Function Literals with Receiver)

* function literal 을 아래처럼 사용 할 수 있다.

```
val receiver = fun(block: String.() -> Int):Int {
    val receiverStr = "5"
    return block(receiverStr)
}

@Test
fun receiverBot() {
    val receiverInt = receiver {
        toInt()
    }

    assertEquals(5, receiverInt)
}
```

* parameter 로 확장 함수를 넘기는 형태
    * 위 처럼 사용할 경우 String 내부에 선언된 함수들을 it 키워드 없이 사용 할 수 있다.
    * 위 parameter type 을 수신 객체 타입이라 말하며, 넘기는 객체를 수신 객체라 말한다.
* 수신 객체 타입을 호출 할 경우, 첫번째 parameter 는 수신객체를 넣어줘야한다.
* 단, 확장 함수로 수신객체 타입을 지정할 경우, 자동으로 this 로 간주한다.

```kotlin
fun String.receiver(block: String.() -> Int):Int {
     // 본인의 함수를 호출 시엔 수신객체를 넘길 필요 없다.
     // 확장함수 내에서, 확장함수를 호출하는 느낌이기때문에 this.block() 으로 간주된다.
    return block()
}
```

이제부터 설명할 함수들이 위 수신객체를 이용하여 구현되어 있다.


#### Let 

모든 객체에서 사용 할 수 있는 확장 함수로 시그니처는 아래와 같다.

```kotlin
@kotlin.internal.InlineOnly
public inline fun <T, R> T.let(block: (T) -> R): R
```

객체 자신을 인자로 사용하여, R을 리턴하는 함수이다.





