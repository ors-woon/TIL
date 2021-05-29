```
+++ 
author = "kmplex" 
title = "[inAction] Kotlin type system" 
date = "2021-05-27" 
description = "kotlin type system"  
series = ["Kotlin"] 
categories = ["dev"] 
+++
```

[코틀린 In Action](http://www.yes24.com/Product/Goods/55148593) 을 읽고, 인상 깊었던 내용을 정리한다.

## 코틀린 타입 시스템 

Kotlin 은 Null 이 될 수 있는 타입을 명시적으로 지원한다. 

```kotlin
fun strLen(s:String) = s.length

strLen(null) // compile Error !! 
```

Null 이 될 수 있는 타입은 이름 뒤에 `?`를 명시해야하며, 메서드를 직접 호출할 수 없다.

```kotlin
fun strLenSafe(s: String?) = s.length() // compile Error ! 
```

또한 nullable 한 변수를 nonnull 타입에 할당 할 수 없다.

> 반대로 nonnull 타입을 nullable 변수에 할당할 수 있다.


### 타입의 의미

타입은 분류`classification`로 어떤 값들이 가능한지와 그 타입에 대해 수행할 수 있는 연산의 종류를 결정한다. (wikipedia)

> Type 에 따라 할당가능한 값과, 수행할 수 있는 함수가 결정된다. (ex Int / String)

다만 Java 에서는 Type(`ex) String`) 에 null 을 할당해도, 컴파일러 단에서 함수의 호출을 막지 않는데,
이는 Java 의 Type System 이 Null 을 제대로 다루지 못한다는 것을 의미한다.

> 이러한 이유로 Null 인 상황에서 NPE 가 발생하며, 함수가 오류로 중단된다. 


*주의* 

Kotlin 에서 실행시점에 널이 될 수 있는 타입 / 널이 될 수 없는 타입의 객체는 같다.
널이 될 수 있는 타입은 널이 될 수 없는 타입의 subType 이 아니며, 모든 Null 검사는 compile 시점에 수행된다.

즉, Java 와 비교했을때, Null 과 관련한 런타임의 Overhead 가 존재하지 않는다.

> 다만, 이러한 특징때문에 java 와 상호운영시 nonnull을 보장하기 어렵다. (아래에서 설명)


### safe call / elvis 는 다른 글에서 다뤘으므로 생략 (?. / ?: / !! / let )

### 안전한 캐스트 as?

Kotlin은 object 의 casting 을 `as` 라는 keyword 로 지원하는데, object 가 명시된 type 이 아닌 경우, `ClassCastException` 이 발생한다.
이 경우, `as?`를 사용하여 더 간결하게 코드를 작성 할 수 있다.

```kotlin
// is 와 as 를 함께 사용
if (obj is String){
    val str = obj as String
}

// as?
val str = obj as? String ?: ""
```

## 나중에 초기화 될 프로퍼티 (lateinit property 역시 이전 글에서 설명했으므로 생략한다.)

## null 이 될 수 있는 타입 확장

null 이 될 수 있는 타입의 확장함수를 사용하면, 그 함수 내부에서 this 는 null 이 될 수 있다.

```kotlin
public inline fun CharSequence?.isNullOrBlank(): Boolean {
    contract {
        returns(false) implies (this@isNullOrBlank != null)
    }

    return this == null || this.isBlank()
}

// 호출 부
(null as String?).isNullOrBlank()
```

> this 가 null 일 수도 있다!!

## Type Parameter

Generic Type Parameter 사용시, `?`가 붙지 않아도, Null 이 될 수 있는 Type 으로 인지 될 수 있다.

> ?을 붙이지 않아도, null이 들어갈 수 있는 유일한 예외 case !!

```kotlin
fun <T> printHashCode(t: T) {
    println(t.hashCode())
}

// 호출 가능 !
printHashCode(null)
```

null 이 아님을 확실히 하려면, upper bound 를 지정해야한다. 

```kotlin
fun <T: Any> printHashCode(t: T) {
    println(t.hashCode())
}

// compile error !
printHashCodeNotNull(null) 
```

## Java 와 함께 사용시

java와 kotlin을 함께 사용 시, annotation 을 이용하여, 해당 type의 null 가능성을 명시할 수 있다. (`@Nonnull` / `@Nullable`)
만약, annotation 이 붙지 않는다면, kotlin의 platform Type 으로 변환된다.

platformType 이란, kotlin 이 널 관련 정보를 알 수 없는 타입을 말한다. (Type 뒤에 `!`가 붙는다. (ex) `String!`)

> kotlin에서는 null이 될 수 있는 타입으로 처리해도 되고, null 이 될 수 없는 type 으로 처리해도 된다.

즉, PlatformType 으로 수행되는 모든 연산에 대한 책임은 개발자에게 있다.

> java

```java
public class Person {

	private final String name;

	public Person(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
```

> kotlin 

```kotlin
fun upper(person: Person) {
    println(person.name.toUpperCase() + "!!")
}

// compile error 가 나지 않는다 ! (person.name)
upper(Person(null))
```

> error

```text
java.lang.NullPointerException: person.name must not be null
	at kotlinbook.incation.chap05.PlatformTypeKt.upper(PlatformType.kt:7)
	at kotlinbook.incation.chap05.PlatformType.upper(PlatformType.kt:14)
```

NPE 에러가 조금 더 자세히 나오는 이유는, kotlin compiler 가 public 함수에 대하여, null 검사를 추가해준다.
때문에, 함수의 사용 시점이 아닌, 호출 시점에서 Exception이 발생한다.

> 엉뚱한 위치에서 예외가 발생하지 않는다.

```java
public final void upper(@NotNull Person person) {
  Intrinsics.checkNotNullParameter(person, "person");
  StringBuilder var10000 = new StringBuilder();
  String var10001 = person.getName();
  Intrinsics.checkNotNullExpressionValue(var10001, "person.name");
  ...
```

kotlin  에서는 호출 시점에 Exception이 터지므로, java class 를 Kotlin 으로 상속하여 사용할 경우, override 시 type을 신중히 선택해야한다.

> platformType 을 notnull 로 명시했음에도, null 이 들어올 수 있으므로 NPE 주의 ! (단 primitive 는 null 이 들어갈 수 없으므로, 안전하다.)

## 왜 platformType 을 도입햇나 ?

java의 모든 타입을 nullable 로 인지 할 경우, 결코 널이 될 수 없는 경우에 대해서도 불필요한 검사가 추가된다.
또한 generic 을 함께 사용 할 경우, `ArrayList<String?>?` 으로 감지되고, 이는 사용때마다 null check 를 하는 번거로움을 야기한다.
때문에 개발자에게 책임을 부여하는 접근 방법을 택했다.


## 원시 타입 

Kotlin 은 primitive 타입과 래퍼 타입을 구분하지 않으며, 원시 타입의 값에 대해 메서드를 호출 할 수 있다.

```kotlin
@Test
@DisplayName("kotlin은 primitive 타입을 구분하지 않는다.")
fun kotlinPrimitive() {
    val num = 1.coerceIn(1..5) // Integer(1).coerceIn 처럼 사용하지 않아도 됨

    assertEquals(1, num)
}
```

항상 Wrapper로 관리하면 비효율적이기 때문에, 코드 작성 시점에는 Primitive / Wrapper Type 을 구분하지 않지만, byteCode 로 변환 시 둘 중 하나의 Type 으로 변경된다.
대부분의 경우 primitive Type 으로 compile 되지만, GenericType 과 Nullable Type 의 경우, Wrapper 으로 compile 된다. 

> Jvm 에서 generic 구현 시, primitive Type 을 허용하지 않는다.

## 숫자 변환

코틀린은 한 타입의 숫자를 다른 타입으로 변환하지 않는다.

```kotlin
val i = 1
val l:Long = i // compile error 
```
대신 모든 원시 타입에 대한 변환 함수를 제공한다. `toInt(), toLong()`  
자동 변환을 지원하지 않는 이유는 개발자들의 혼란을 피하기 위해서인데, 자바에서 Wrapper Type 을 비교 할 경우 간혹 원치 않는 결과가 나온다.

```java
new Integer(42).equals(new Long(42)) // false
```

위 같은 문제를 잡기위해, 자동변환을 지원하지 않으며, 아래 코드는 error 를 뱉는다.
```kotlin
val x = 1
val list = listOf(1L,2L,3L)
//  Back-end (JVM) Internal error: Failed to generate expression: KtCallExpression
x in list

// x.toLong() in list 로 사용해야한다.
```

## Collection 과 Array 