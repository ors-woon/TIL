```
+++ 
author = "kmplex" 
title = "[Effective Kotlin] 01. 가변성을 제한하라" 
date = "2022-02-07"   
series = ["Kotlin"] 
categories = ["dev"] 
+++
```

## Effective Kotlin 

[Effective Kotlin](http://www.yes24.com/Product/Goods/106225986)를 읽고 짤게 정리한 내용입니다.

## Item01. 가변성을 제한하라

- `var` 을 쓰거나, mutable 객체를 사용하면 `상태` 를 갖을 수 있습니다.
  - 상태를 갖는 경우, 사용 방법 뿐아니라, 이력(`history`)에도 의존하게 됩니다.
- [예제](/code/src/main/kotlin/effective/item1/LimitMutability.kt)는 계좌의 돈을 나타내는 상태를 갖고 있습니다.
  - 상태를 적절히 관리하는 것이 생각보다 어렵습니다.
    - 프로그램을 이해하고 디버그하기 힘들어집니다. (상태 변경이 많아지면, 이를 추적하기 어려워집니다.)
    - 가변성(mutablility)이 있으면 코드의 실행을 추론하기 어려워집니다.
    - 멀티 스레드 환경에서 적절한 동기화가 필요합니다.
      - [예제](/code/src/main/kotlin/effective/item1/MultiThreadExample.kt)처럼 일부 연산이 충돌되어 사라지므로, 적절한 동기화가 필요합니다. 
    - Test 하기 어려워집니다. (변경이 많으면 확인해야할 조합이 늘어납니다.)
- 가변성은 생각보다 단점이 많아, 이를 제한하는 함수형 언어들도 등장하고 있습니다. (Haskell)
  - 이러한 언어는 가변성에 너무 많은 제한이 걸려서 프로그램을 작성하기가 굉장히 어렵습니다.
  - 가변성은 시스템의 상태를 나타내기 위한 중요한 방법중 하나이지만, 변경이 일어나야하는 부분을 신중하게 결정하여 사용해야합니다.

#### 코틀린에서 가변성 제한하기

```
- 01. 읽기 전용 프로퍼티(val)
- 02. 가변 컬렉션과 읽기 전용 컬렉션 구분하기
- 03 데이터 클래스의 copy
```

- 코틀린은 val 을 사용해 읽기 전용 프로퍼티를 만들 수 있습니다.
  - 마치 value 처럼 동작하며, 일반적인 방법으로는 값이 변하지 않습니다.
    - 단, 완전히 변경 불가능한 것은 아닌데, [mutable 객체](/code/src/main/kotlin/effective/item1/Mutable.kt)를 담고 있다면, 내부적으로 변할 수 있습니다.
    - 또한 다른 프로퍼티를 활용하는 사용자 정의 getter 로도 정의 할 수 있습니다. [예제](/code/src/main/kotlin/effective/item1/Mutable.kt)
      - 값을 추출 할때마다, getter 가 호출되므로, 이렇게 코드를 작성 할 수도 있습니다. [예제](/code/src/main/kotlin/effective/item1/Mutable.kt)
  - (주의) val 은 읽기 전용이지, 값이 변할 수 없는 것은 아닙니다. (불변성 X)
  - 코틀린의 property는 기본적으로 캡슐화 되어 있으며, 사용자 정의 접근자를 가질 수 있습니다. (관련 설명은 item.16 에서..)
  - var 은 getter / setter 를 모두 제공하지만, val 은 getter 만 제공합니다. 
    - 그래서 val 을 var 로 override 할 수 있습니다.
  - val 사용 시 스마트 캐스트등의 추가적인 기능을 사용 할 수 있습니다.
  - 읽기 전용 프로퍼티 값은 변경될 수 있으나, reference 자체를 변경할 수는 없으므로, 동기화 문제등을 줄일 수 있습니다.
    - 위와 같은 이유로 일반적으로 var 보단 val 을 더 많이 사용합니다.
  

![collections-diagram.png](collections-diagram.png)

- 마찬가지로 읽고 쓸 수 있는 컬렉션과 읽기 전용 컬렉션을 구분합니다.
  - Iterable, Collection, Set, List Interface 는 읽기 전용이며, 이를 상속받아 Mutable Collection 을 지원합니다.
  - Mutable Collection 은 읽기 전용 인테페이스에 변경을 위한 메서드를 추가한 것입니다.
- 마찬가지로 읽기 전용 컬렉션이 내부의 값을 변경할 수 없다는 의미는 아닙니다.
  - 아래 코드는 filter 의 구현 부입니다. ArrayList 를 정의 후, List (읽기 전용) collection 으로 upcasting 합니다. 

```kotlin
public inline fun <T> Iterable<T>.filter(predicate: (T) -> Boolean): List<T> {
    return filterTo(ArrayList<T>(), predicate)
}

public inline fun <T, C : MutableCollection<in T>> Iterable<T>.filterTo(destination: C, predicate: (T) -> Boolean): C {
  for (element in this) if (predicate(element)) destination.add(element)
  return destination
}
```

- 위 코드를 downCasting 하여, 내부 값을 변경할 수 있으나, 이는 추상화를 무시하는 행위이며 절대 허용해서는 안되는 문제입니다.
  - 자세한 내용은 2부에서 다룹니다.

```kotlin
val list = listOf(1,2,3)

if(list is MutableList){
    list.add(4)
}

print(list)
```

- 위 코드는 플랫폼에 따라 결과가 달라지는데, JVM 에서는 Arrays.ArrayList 를 리턴하고 이는 MutableList 로 변경 할 수 있습니다.
  - 단, Arrays.ArrayList 는 이를 구현하고 있지 않아, `UnsupportedOperationException` 이 발생하게 됩니다.
  - Kotlin 이 이후, 내부 구조를 바꿀 경우 이슈가 될 수 있으므로, 다운 캐스팅은 사용해선 안됩니다. 
- 읽기 전용에서 mutable 로 바꿔야한다면, toMutableList 함수를 사용해야합니다.


- 지금까지 설명했던 이유 외에도, immutable 객체를 사용하면 다음과 같은 장점이 있습니다.
  - 코드를 이해하기 쉬우며, 병렬처리를 안전하게 할 수 있다.
  - 참조가 변경되지 않으므로, 쉽게 캐시할 수 있으며 깊은 복사를 따로 하지 않아도 됩니다.
  - HashSet / HashMap 의 key 로 활용될 수 있습니다.
    - mutable 한 object 를 key로 사용할 경우, 값이 바뀌면서 hash 값도 바뀌기에 사용이 어렵습니다. (item 41 에서 자세히 설명)
- 단, immutable 객체는 변경할 수 없다는 단점이 있습니다.
  - 따라서, 자신의 일부를 수정한 새로운 객체를 만들어내는 메서드를 가져야합니다. ex) withProperty()
  - 모든 property 에 함수를 추가하는건 어려우므로, data 한정자를 사용하여 copy 함수를 이용하면 됩니다. [예제](/code/src/main/kotlin/effective/item1/DataModifier.kt)

#### 다른 종류의 변경 가능 지점

- 변경할 수 있는 리스트를 만들어야 한다고 하면, 2가지 선택지가 있습니다.
  - val + mutable 
  - var + immutable 
- 두가지 모두 변경은 가능하나, 방법이 달라집니다.

```kotlin
val list1 = mutableListOf<Int>()
list1.add(1)

var list2 = listOf<Int>()
list2 = list2 + 1
```

- 각 항목은 장단점이 있는데, 두가지 모두 변경 가능 지점이 있으나, 그 위치가 다릅니다.
  - 첫 번째 코드는 구체적 리스트 구현 내부에 변경 가능 지점이 있습니다.
    - multiThread 처리를 할때, 내부적으로 적절한 동기화가 되어 있는지 확실하게 알 수 없으므로 위험합니다.
  - 두 번째 코드는 프로퍼티 자체가 변경 가능 지점입니다.
    - 따라서, 멀티스레드 처리의 안정성이 더 좋다고 할 수 있습니다. (물론 잘못 만들면 일부 요소가 손실 됩니다.)
    - 또한 Delegate 를 사용하여, 변경을 추적 할 수도 있습니다. [예제](/code/src/main/kotlin/effective/item1/Delegates.kt)
    - private set 을 만들어, 내부에서만 변경할 수 있게 제어할 수 있습니다.
  - mutable Collection 을 사용하는 것이 더 간단하게 느껴지겠지만, mutable property 를 사용하면 객체 변경을 제어하기 더 쉽습니다.
  - 단, mutable collection / mutable property 를 모두 사용하는 방식은 최악입니다.
    - 변경할 수 있는 모든 지점에 동기화를 구현해야하며, 모호성이 발생하여 += 를 사용 할 수 없습니다.
      - mutable 의 구현체 (plusAssign)를 호출하는지, var 을 바꾸는건지 알 수 없음
- 상태를 변경할 수 있는 불필요한 방법은 만들지 않아야합니다. (가변성을 제한하는 것이 좋습니다.)

#### 변경 가능 지점 노출하지 말기

- 상태를 나타내는 mutable 객체를 외부에 노출하지 마세요.
  - 이러한 코드는 돌발적인 수정이 일어날 때 위험할 수 있습니다. [예제](/code/src/main/kotlin/effective/item1/MutableUpCasting.kt)
    - copy를 사용하여, 복제하거나
    - 업캐스트하여 가변성을 제한 할 수 있습니다.


(주의) 읽기 전용과 가변성은 다른 개념입니다. 



  

