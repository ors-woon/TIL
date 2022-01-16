```
+++ 
author = "kmplex" 
title = "unit testing (2)" 
date = "2022-01-15" 
description = "unit testing 2부"  
series = ["unit testing"] 
categories = ["dev","test"] 
+++
```

[Unit Testing](http://www.yes24.com/Product/Goods/104084175)를 읽고 짤게 정리한 내용입니다.

## 2부 개발자에게 도움이 되는 테스트 만들기 

#### 4장. 좋은 단위 테스트의 4대 요소

1. 회귀 방지
2. 빠른 피드백 
3. 리팩터링 내성 
4. 유지 보수성

#### 1. 회귀 방지 

코드를 수정한 후, 기능이 의도한 대로 동작하는가를 확인한다.
코드는 자산이 아닌, 책임의 성격을 띄는데, 코드 베이스가 커질수록 잠재적인 버그에 더 많이 노출되기 때문이다.
이러한 이유로, 소프트웨어가 지속 / 향상되기 위해선 회귀에 대한 효과적인 보호가 필요하다.

회귀 방지의 지표는 아래와 같다.

- 테스트 중 실행되는 코드의 양 
- 코드 복잡도 
- 코드의 도메인 유의성 

회귀 방지 관점에서, 보일러 플레이트 코드보다, 도메인 코드를 확인하는게 더 중요하며, Framework 코드등 개발자가 작성하지 않은 외부 영역의 테스트도 필요하다.

> 회귀 방지 지표를 극대화 하려면, 테스트가 가능한 많은 코드를 실행하는 것을 목표로 해야한다.

#### 2. 리팩터링 내성 

테스트가 실패하지 않고, 기본 Application Code 를 리팩터링 할 수 있는지에 대한 척도이다.
기반 코드를 수정했을 때 기능에는 변화가 없지만, Test 가 깨지는 경우를 `거짓 양성`이라한다.
거짓 양성은 허위 경보로, 지속가능한 성장을 막는다. 

테스트가 `지속 가능한 성장`을 하기위해선회귀 없이 주기적으로 / 자유로운 리팩터링이 가능해야한다.

> 테스트의 목적은 지속성 / 향상성이다.

거짓 양성은 이를 방해하는데, `1.` 타당한 이유없이 테스트가 실패하며 `2.` 허위 경보를 발생 시켜, 테스트를 믿을 만한 안정망으로 인식하지 않는다.


#### 2.1 무엇이 거짓 양성의 원인인가 ? 

거짓 양성은 테스트 구성 방식과 직접적인 관련이 있는데, SUT 와 구현 세부사항이 많이 결합할수록 허위 경보가 더 많이 생긴다.    
즉, 테스트에서 구현 세부사항을 분리시켜야한다. 테스트를 통해 SUT가 제공하는 최종 결과를 검증하는지 확인해야한다.


```kotlin
class Message(val header: String, val body: String, val footer: String)


interface IRenderer {
    fun render(message: Message): String
}

class MessageRenderer : IRenderer {
    val subRenderers: MutableList<IRenderer>

    init {
        subRenderers = mutableListOf()
        subRenderers.add(HeaderRenderer())
    }

    override fun render(message: Message): String = subRenderers.joinToString("\n") { it.render(message) }
}

class HeaderRenderer : IRenderer {
    override fun render(message: Message): String = "header"
}

// Test 
@Test
fun messageRenderer_uses_correct_sub_renderers() {

    val sut = MessageRenderer()

    val renderer = sut.subRenderers

    // 식별할 수 있는 동작이 아닌, 세부 구현을 체크하는 테스트
    assertEquals(1, renderer.size)
    assertTrue(renderer[0] is HeaderRenderer)
}
```

리팩터링은 Application의 식별할 수 있는 동작에 영향을 주지 않으면서, 구현을 변경하는 것이다.   
위 테스트는 하위 랜더링 클래스의 구성을 변경할 경우 응답에 상관 없이 깨지며, 리팩터링 내성이 없는 TC이다.

> 식별할 수 있는 동작을 고려하지 않고, 구현만 고집할 경우 깨지기 쉬운 TC가 된다.

#### 2.2 구현 세부 사항 대신 최종 결과를 목표로 하기

거짓 양성을 없애기 위해선, 구현 세부사항과 테스트간 결합도를 낮춰야한다. 즉, 최종 결과에 초점을 맞춰 TC를 작성해야한다.





