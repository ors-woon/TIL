```
+++ 
author = "kmplex" 
title = "companion object" 
date = "2021-02-28" 
description = "companion object"  
series = ["Kotlin"] 
categories = ["dev"] 
+++
```

# kotlin object keyworld 

* kotlin 에서는 singleton 을 언어에서 지원한다.
    * 이때 사용하는 keyworld 가 object

```kotlin
object HelloWorld {
    fun helloWorld() = "helloWorld"
}
```

위처럼 선언하고자 하는 class 에 object keyworld 를 대신 사용할 경우, java 에서는 아래처럼 표현된다.

```java
public final class HelloWorld {
   @NotNull
   public static final HelloWorld INSTANCE;

   @NotNull
   public final String helloWorld() {
      return "helloWorld";
   }

   private HelloWorld() {
   }

   static {
      HelloWorld var0 = new HelloWorld();
      INSTANCE = var0;
   }
}
```

static final 로 object 를 선언하고, static block 으로 객체를 미리 초기화해둔다.
`public static final` 로 객체를 선언했기에, 아래처럼 사용할 수 있다.

```kotlin
    @Test
    fun helloWorldTest() {
        val expect = "helloWorld"

        assertEquals(expect, HelloWorld.helloWorld())
    }
```

static method 와 유사하게 사용 할 수 있으며, Java 로 변환 할 경우 아래처럼 표현된다.

`HelloWorld.INSTANCE.helloWorld()`

# kotlin companion object 

* kotlin 에는 static 함수/변수 가 존재하지 않는다.
* static 과 *유사하게* 사용하기 위해서, companion object 라는 keyworld를 사용 할 수 있다.

다만, 유사하게 동작할뿐, 실제로 static 이 생성되는게 아니다.

```
class Hello {
    companion object {
        fun hello() = "hello"
    }
}

class World {
    fun world() = Hello.hello()
}
```


> java

```
public final class Hello {
   @NotNull
   public static final Hello.Companion Companion = new Hello.Companion((DefaultConstructorMarker)null);

  public static final class Companion {
      // code 
  }
}
```

nested class 로 Companion class 를 선언하고 이를 객체화해서 들고있는다.

#### 왜 다른 방식으로 지원하는가 ?

`The main advantage of this is that everything is an object.` [link](https://softwareengineering.stackexchange.com/questions/356415/why-is-there-no-static-keyword-in-kotlin/356421)

kotlin 에서 static 을 지원하지 않는 가장 큰 이유는, static member 가 object 로 취급되지 않기 때문이다.
object 로 취급되지 않는다는 건, 상속을 이용할 수 없고, parameter 로 전달될 수 없으며, instance Map 등을 활용할 수 없다는 것을 의미한다.

> 개인적으로는 아직 이점을 잘 모르겠으나 .. 아래 예제는 꽤 괜찮아보였다.

```kotlin
interface KeyGenerator {
    fun generate() : String
}


class Hello{
    companion object : KeyGenerator {
        override fun generate(): String = "this object key"
    }
}
```

객체의 역할이 아닌, 유틸성(?) Interface 를 아래처럼 분리해서 사용하면 꽤 유용할거 같다.
