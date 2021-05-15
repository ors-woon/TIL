```
+++ 
author = "kmplex" 
title = "[Cookbook] 4장" 
date = "2021-05-15" 
description = "4장에서 인상 깊었던 내용들 정리"  
series = ["Kotlin"] 
categories = ["dev"] 
+++
```

[코틀린 쿡북](http://www.yes24.com/Product/Goods/90452827)을 읽고, 인상 깊었던 내용을 정리한다.

> 책의 목차만 동일하며, 내용은 이해한대로 재구성한다.

# 들어가며

함수형 프로그래밍이라는 용어는 `불변성`을 선호하고, `반복`보다는 `변형`, `조건문`보다는 `필터`를 사용하는 코딩 스타일을 지칭한다.

- TODO 불변성은 이해함 반복 / 변형, 조건 / 필터 ?

## 4.1 알고리즘에서 fold 사용하기

fold 함수를 사용하여, Collection / Sequence를 하나의 값으로 축약(`reduce`)시킨다. 함수의 문법은 아래와 같다.

```kotlin
public inline fun <T, R> Iterable<T>.fold(initial: R, operation: (acc: R, T) -> R): R {
    var accumulator = initial
    for (element in this) accumulator = operation(accumulator, element)
    return accumulator
}
```

초기값을 하나 받아(`initial: R`), 초기값과 Iterable 의 원소로 함수를 실행시키는 형태(`(acc: R, T) -> R`)이다. fold 함수의 대표적인 예제는 sum 연산이다.

```kotlin
@Test
fun fold() {
    val list = listOf(1, 5, 7, 9)

    val sum = list.fold(0) { init, element -> init + element }

    assertEquals(sum, 22)
    assertEquals(sum, sum(*list.toIntArray()))
}
fun sum(vararg nums: Int) =
    nums.fold(0) { acc, n -> acc + n }
```

*여담으로..*

argumentParam 으로 `vararg` 를 받는 경우, spread 연산을 사용할 수 있다. (`*ArrayVariable`)
단, Kotlin 에서 spread 연산은 array 만 지원한다.

collection spread에 대해, [이슈](https://youtrack.jetbrains.com/issue/KT-12663)로 등록은 되어있으나, 아직 미지원

fold는 factorial 에서 더 재밌게(?) 표현된다.

> 일반 Factorial

```kotlin
fun recursiveFactorial(n: Long): BigInteger =
    when (n) {
        0L, 1L -> BigInteger.ONE
        else -> BigInteger.valueOf(n) * recursiveFactorial(n - 1)
    }
```

> fold 를 사용한 factorial

```kotlin
fun recursiveFactorialFold(n: Long): BigInteger =
    when (n) {
        0L, 1L -> BigInteger.ONE
        else -> (2..n).fold(BigInteger.ONE) { init, acc -> init * BigInteger.valueOf(acc) }
    }
```

> 피보나치

```kotlin
fun fibonarcciFold(n: Int) =
    (2 until n).fold(1 to 1) { pair, _ ->
        pair.second to (pair.first + pair.second)
    }.second
```

위 피보나치는 acc 값을 고려하지 않기때문에 `_`을 사용했다. 
흥미로운 점은 누적 값의 타입이 범위의 원소 타입과 다르다는 것이다.
`원소 타입은 Int, 누적 값은 Pair이다.`

> TODO Inline 함수의 장점이 뭐지 ?
> 항등원이 뭐더라 ..?