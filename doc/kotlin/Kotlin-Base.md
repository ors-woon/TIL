```
+++ 
author = "kmplex" 
title = "Kotlin-Base" 
date = "2021-04-21" 
description = "간단한 문법정도만 정리"  
series = ["Kotlin"] 
categories = ["dev"] 
+++
```

## statement / expression

[참고](https://medium.com/kotlin-academy/kotlin-programmer-dictionary-statement-vs-expression-e6743ba1aaa0)

`expression`이란, 다른 값을 산출하기 위해, 해석/계산하는 하나 이상의 값 및 함수의 조합을 의미한다.

`statement`이란, 수행할 작업을 나타내는 명령형 프로그래밍 언어의 가장 작은 독립 실행형 요소.
변수 할당 , class 초기화 등은 `statement` 로 볼 수 있다.


```
expression – return, no side effect
statement – no return, side effect
--- 
a expression is evaluated (eval)
a statement is executed(exec)
```

kotlin 에서 `if / try / when / throw` 을 expression 으로 간주한다.

> if / try / when 등이 evaluated 되어, 변수에 할당된다.

## expression 과 statement 을 왜 구분하는가? 

if 가 expression 일 경우, 아래처럼 표현 할 수 있습니다.

```kotlin
val valuable = if(a > b) a else b
```

또한 if 문을 함수의 parameter 로 사용 할 수 있다.

```kotlin
val productIds = (1..20).map {
    val product = getProduct(
            productRegistrationStatusType = if (it % 2 == 1) ProductRegistrationStatusType.WILL_MAPPING_REGISTRATION else ProductRegistrationStatusType.REGISTRATION)
}
```

## 2. Kotlin 엔 Checked Exception 이 존재하지 않는다.

kotlin 은 checked Exception 가 존재하지 않기때문에, method 선언시 throws 가 필요없다.

> checked error 를 통해 에러를 복구하거나, 전파하는 경우보다, 단순 로깅으로 무시하는 경우가 많았기때문에 checked Exception이 잘못된 설계라는 말들이 많다.

때문에 Kotlin 에선 checked Exception 을 지원하지 않음

## 3. 변수 선언 

변수 / 함수는 타입 추론이 가능하다.
타입을 안적어주면, 초기화 시 입력되는 변수로 추론되며, 초기화를 하지않으면 변수 선언시 타입을 명시해 주어야한다.

```
val member = "string"
val number = 1
# or 

var hello:String 
```

#### 기본 접근 제한자

java 와 달리 kotlin 은 default 접근자가 public 이다.

#### when 

kotlin엔 switch 대신 when 이 들어감.

```aidl
    when (color) {
        RED, ORANGE, YELLOW -> "warm"
        GREEN -> "neutral"
        else -> "cold"
    }
```

또한 java 에서와는 달리 enum 외에 객체를 받을 수 있다.
이때 객체는 equals 를 구현해야한다.

```aidl
fun mix(c1: Color, c2: Color) =
    when (setOf(c1, c2)) {
        setOf(RED, YELLOW) -> ORANGE
        setOf(YELLOW, BLUE) -> GREEN
        else -> throw Exception("Other color")
    }

// argument 제거 
fun mixOptimized(c1: Color, c2: Color) =
    when {
        (c1 == RED && c2 == YELLOW) || (c1 == YELLOW && c2 == RED) -> ORANGE
        else -> throw Exception("Other color")
    }
```

## in keyword 

> 1. for 에 사용 가능 

숫자 / 문자에 대해 아래처럼 처리 할 수 있다.

```kotlin
for ( i in 1..10) {
//  1 ~ 10 까지 루프  
}

for ( i in 'A'..'F') {
//  A ~ F 까지 루프  
}

// map 을 key , value 형태로도 분리 가능
for ((letter, binary) in binaryReps) {
    println("$letter = $binary")
}

val list = arrayListOf("10", "11")
```

아래 withIndex 는 indexValue를 리턴하는 함수.

> lazy iterable 

Returns a lazy Iterable that wraps each element of the original array into an IndexedValue containing the index of that element and the element itself.

```kotlin
for ((index, value) in list.withIndex()) {
    println("$index = $value")
}
```

또한 in은 범위 검사로도 사용 할 수 있다.

```kotlin
// value 가 1 ~ 10 사이에 속해있는가 ?
if( value in 1..10)
```

아래처럼 부정의 의미로도 사용할 수 있다.

```kotlin
fun isLetter(c:Char) = c in 'a'..'z' || c !in 'A'..'Z'
```