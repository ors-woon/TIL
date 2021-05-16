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

# 들어가며 2

*[inline keyword](https://kotlinlang.org/docs/inline-functions.html)*



## 4.1 알고리즘에서 fold 사용하기

fold 함수를 사용하여, Collection / Sequence를 하나의 값으로 축약(`reduce`)시킨다. 함수의 구현은 아래와 같다.

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

위 피보나치는 acc 값을 고려하지 않기때문에 `_`을 사용했다. 흥미로운 점은 누적 값의 타입이 범위의 원소 타입과 다르다는 것이다.
`원소 타입은 Int, 누적 값은 Pair이다.`

## 4.2 reduce 함수를 사용해 축약하기

reduce는 fold 와 동일한 목적으로 사용되는데, 두 함수의 큰 차이점은 초기 값 인자가 없다는 점이다.

reduce 의 구현은 아래와 같다.

```kotlin
public inline fun <S, T : S> Array<out T>.reduce(operation: (acc: S, T) -> S): S {
    if (isEmpty())
        throw UnsupportedOperationException("Empty array can't be reduced.")
    var accumulator: S = this[0]
    for (index in 1..lastIndex) {
        accumulator = operation(accumulator, this[index])
    }
    return accumulator
}
```

fold와는 다르게, 첫번째 인자를 초기값으로 할당하여, 인자로 전달된 람다를 실행시키는 구조이다.

> 사용 예시

```kotlin
@Test
fun reduce() {
    val result = arrayOf(1, 4, 8).reduce { acc, item ->
        acc * item
    }
    assertEquals(32, result)
}
```

> 그렇다면, 인자가 2개인 fold 보단 reduce 를 쓰는게 더 편하지 않을까?

reduce는 parameter로 넘겨진 lambda가 첫번째 인자에 대해서는 수행되지 않는다.

각각의 입력 값을 2배로 곱한 합을 reduce로 구현해보자.

```kotlin
@Test
fun reduce_badCase() {
    val result = arrayOf(1, 3, 7).reduce { acc, item ->
        acc + (2 * item)
    }
    assertEquals(21, result)
}
```

사용자의 기대는, 1 / 3 / 7 을 2배로 곱한 값들의 합을 구하는 것이지만, 첫번째 item 이 초기값으로 할당 됨으로 로직에선 제외된다.

> 첫번째 item에 추가 연산이 필요 없을때만 reduce 를 사용해라.

*여담으로* java 에서는 overload를 이용하여, reduce 를 제공한다. (fold / reduce 구분하지 않는다.)

> 항등원이 뭐더라 ..?