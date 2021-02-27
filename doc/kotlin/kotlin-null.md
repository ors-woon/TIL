```
+++ 
author = "kmplex" 
title = "kotlin nullable" 
date = "2021-02-27" 
description = "kotlin nullable"  
series = ["Kotlin"] 
categories = ["dev"] 
+++
```

# kotlin Nullable 

kotlin 은 기본적으로 null 을 허용하지 않으며, Null 을 허용하고 싶다면 type 뒤에 `?`를 사용해야한다.

```kotlin
val str : String? = null
```

또한 Null 체크 후, 분기를 이용해 데이터 조작을 할 경우 `safe check` / `Elvis Operator` 를 고려해야할 수 있다.

> 단 immutable 인 경우엔 편하게 사용할 수 있다.

```kotlin
class Person(val name:String, var age:String?) 

var person = Persion(name = "chulwoon", age = null)  

if(person.age != null){
    person.age.length  // error ! 
}
```

null 체크와 `person.age.length` 연산 사이에 thread로 인해 null 값이 주입되거나, 다른 함수 호출등의 이유로 값이 바뀔 수 있기때문에 컴파일러가 에러를 뱉어준다. 위에선 크게 3가지의 해결책을 생각해볼 수 있다.

```text
1. immutable 로 수정 (val) 
2. safe check 
3. Elvis operator
```

####  safe check  

safe check 는 `?.`을 사용하여 호출하는 방식이다. 만약 null 데이터에 대하여 함수를 호출 할 경우, null 값을 리턴해준다.

```kotlin
    person.age?.length // age 가 null 일 경우 null return 
```  

만약 null 값이 아니라, default 값을 리턴하길 원한다면 ? 

#### Elvis operator 

Elvis operator은 `?:` keyword 로, 삼항 연산자의 축약으로 생각하면 이해하기 편하다. 

```kotlin
    val length = person.age?.length ?: 0
```  

null check 부터 default 값까지 위처럼 선언할 수 있다.

> 그냥 val 로 바꾸는게 짱짱 편할거 같긴하다.


safe check 와 Elvis operator 를 함께 사용 할 수 있다.