```
+++ 
author = "kmplex" 
title = "[Cookbook] 2장" 
date = "2021-04-25" 
description = "2장에서 인상 깊었던 내용들 정리"  
series = ["Kotlin"] 
categories = ["dev"] 
+++
```

[코틀린 쿡북](http://www.yes24.com/Product/Goods/90452827)을 읽고, 인상 깊었던 내용을 정리한다.

> 책의 목차만 동일하며, 내용은 이해한대로 재구성한다.

### 2.1 코틀린에서 Null 허용 타입 사용하기

Kotlin은 Nullable Type과 NotNull Type을 구별한다.

```kotlin
val nullableType: String? 
val notNullType: String
```

`2.1`에선 Null Object을 다루는 방법을 소개한다.

- 0. base 

```
fun nullableMap(isEmpty: Boolean): List<String>? = if (!isEmpty) {
    mutableListOf("hi", "hello", "안녕")
} else {
    null
}
```

- 1. not null 단언 연산자 (code smell)

```kotlin
@Test
@DisplayName("not null 단언 연산자 case")
fun handle() {
    // code smell
    try {
        val nullableList: List<String> = nullableMap(isEmpty = true)!!
        fail()
    } catch (ex: NullPointerException) {
        // success
    }
}
```

- 2. safe call case 

```kotlin 
@Test
@DisplayName("safe call case")
fun handleNullable() {
    val nullableList: List<String>? = nullableMap(isEmpty = true)

    assertEquals(null, nullableList?.map { it to it.length }?.size,
            "변수가 null 이 아니면 수행, null 이면 null return")
}
```

- 3. safe call + Elvis case 

```kotlin
@Test
@DisplayName("safe call + Elvis case")
fun elvis() {
    val nullableList: List<String>? = nullableMap(isEmpty = true)

    val notNullMap: List<Pair<String, Int>> = nullableList?.map { it to it.length } ?: emptyList()
    assertEquals(0, notNullMap.toMap().size)
}
```


### 2.2 자바에 널 허용성 지시자 추가하기


설정 
```
compileKotlin {
  kotlinOptions {
    freeCompilerArgs = ["-Xjsr305=strict"]
    jvmTarget = "1.8"
  }
}
```

```
- JetBrains (@Nullable and @NotNull from the org.jetbrains.annotations package)
- Android (com.android.annotations and android.support.annotations)
- JSR-305 (javax.annotation, more details below)
- FindBugs (edu.umd.cs.findbugs.annotations)
- Eclipse (org.eclipse.jdt.annotation)
- Lombok (lombok.NonNull)
```

### 2.3 자바를 위한 메서드 중복 

kotlin 에선 Default Parameter 를 지원하는데, 이를 Java 에서 사용 할 경우 추가 Annotation 선언이 필요로하다.

- 0. Kotlin

```kotlin
class CustomMap<T, R> @JvmOverloads constructor(val mutableMap: MutableMap<T, R> = mutableMapOf()) : MutableMap<T, R> by mutableMap

@JvmOverloads
fun <T, R> CustomMap<T, R>.add(key: T, value: R, mergeFunction: (R, R) -> R = { first, second -> first }): Map<T, R> {
    val isExistKey = this[key] != null
    if (isExistKey) {
        val mergeValue = mergeFunction(this.getValue(key), value)
        this.plus(key to mergeValue)
        return this
    }

    this.plus(key to value)
    return this
}
```

> JAVA 에서 호출

```java
@Test
void defaultParameter() {
    String hhkb = "HHKB";
    CustomMap<String, String> keyboardGroupCountry = new CustomMap<>(new HashMap<>());
    keyboardGroupCountry.put("japan", hhkb);
    keyboardGroupCountry.put("korea", "한성");

    // annotation 을 달지 않으면, default param 지원 x
    //DefaultParameterKt.add(keyboardGroupCountry,"japan","real-force")
    DefaultParameterKt.add(keyboardGroupCountry, "japan", "real-force");

    assertEquals(hhkb, keyboardGroupCountry.get("japan"));
}

@Test
@DisplayName("kotlin 의 constructor keyword 필요")
void defaultParameterWithConstructor() {
    String hhkb = "HHKB";
    CustomMap<String, String> keyboardGroupCountry = new CustomMap<>();
    keyboardGroupCountry.put("japan", hhkb);
    keyboardGroupCountry.put("korea", "한성");

    DefaultParameterKt.add(keyboardGroupCountry, "japan", "real-force");

    assertEquals(hhkb, keyboardGroupCountry.get("japan"));
}
```

kotlin 생성자에 `@JvmOverloads` 를 붙이려면, `constructor` keyword 를 붙여야한다.

추가로, kotlin 의 확장함수를 Java에서 호출하려하면, `$this`를 넘겨줘야한다.
아래는 java 로 Decompile 된 kotlin 확장함수이다.

```java
@JvmOverloads
@NotNull
public static final Map add(@NotNull CustomMap $this$add, Object key, Object value, @NotNull Function2 mergeFunction) {
    Intrinsics.checkNotNullParameter($this$add, "$this$add");
    Intrinsics.checkNotNullParameter(mergeFunction, "mergeFunction");
    boolean isExistKey = $this$add.get(key) != null;
    if (isExistKey) {
        Object mergeValue = mergeFunction.invoke(MapsKt.getValue((Map)$this$add, key), value);
        MapsKt.plus((Map)$this$add, TuplesKt.to(key, mergeValue));
        return (Map)$this$add;
    } else {
        MapsKt.plus((Map)$this$add, TuplesKt.to(key, value));
        return (Map)$this$add;
    }
}
```

### 2.9 to로 Pair 인스턴스 생성하기

kotlin 은 `infix` 라는 keyword로 `중위 함수`를 선언할 수 있다.

```kotlin
infix fun String.concatenate(str: String) = "$this $str"

@Test
@DisplayName("Pair")
fun pair() {
    val map = mapOf("key" to "value", "key2" to "value")

    assertEquals(2, map.size)
    assertEquals("value", map["key2"])

}
```

함수를 문장처럼 쓸 수 있다는 장점(?)이 있음

대표적인 중위 함수가 `Pair`이며, 아래처럼 사용 할 수 있다.

```kotlin
@Test
@DisplayName("Pair")
fun pair() {
    val map = mapOf("key" to "value", "key2" to "value")

    assertEquals(2, map.size)
    assertEquals("value", map["key2"])

    // (A, B) 로 할당도 가능
    val (key, value) = "key" to "value"

    assertEquals("key", key)
    assertEquals("value", value)
}
```