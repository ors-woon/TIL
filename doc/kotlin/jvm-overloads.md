```
+++ 
author = "kmplex" 
title = "Jvm overloads annotation" 
date = "2021-02-27" 
description = "Jvm overloads annotation"  
series = ["Kotlin"] 
categories = ["dev"] 
+++
```

# @JvmOverLoads

kotlin 과 java 를 함께 쓸때, default param 을 지원하기 위해 `@JvmOverLoads` 이 필요하다.

> 아무 annotation 이 없는 function 

```kotlin
fun defaultParam(str: String, num: Int, obj: Any? = null) {
    println("""
        $str $num $obj 
    """.trimIndent())
}
```

> 호출부 java

```java
public static void main(String[] args) {
    JvmOverLoadsKt.defaultParam("str", 1 , new String("??"));
}
```

default param 생략시, compile error 가 발생한다.

이를 해결하기 위해 kotlin method 에 `@JvmOverLoads` 를 사용 할 수 있다.

> kotlin

```kotlin
@JvmOverloads
fun defaultParam(str: String, num: Int, obj: Any? = null) {
    println("""
        $str $num $obj 
    """.trimIndent())
}
```

> java

```java
public static void main(String[] args) {
    JvmOverLoadsKt.defaultParam("str", 1 , new String("??"));
}
```


`@JvmOverloads` 를 사용하면, java 로 변환 될때 아래처럼 overload 를 사용한다.

```java
public static final void defaultParam(@NotNull String str, int num, @Nullable Object obj) {
  Intrinsics.checkParameterIsNotNull(str, "str");
  String var3 = StringsKt.trimIndent("\n        " + str + ' ' + num + ' ' + obj + " \n    ");
  boolean var4 = false;
  System.out.println(var3);
}

// $FF: synthetic method
public static void defaultParam$default(String var0, int var1, Object var2, int var3, Object var4) {
  if ((var3 & 4) != 0) {
     var2 = null;
  }

  defaultParam(var0, var1, var2);
}
@JvmOverloads
public static final void defaultParam(@NotNull String str, int num) {
  defaultParam$default(str, num, (Object)null, 4, (Object)null);
}
```

overload 로 method 를 2개를 파고, 디폴트 인자를 박아서 함수를 재호출 하는 방식으로 구현된다.

* 메서드가 n 개 생기게 된다.

> 주의 생성자에게도 사용할 수 있는데, 그럴 경우 super 를 호출하지 않게 된다. 

어떤 side effect 가 나올지 모르니, 생성자 사용시엔 주의하자.




