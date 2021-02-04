```
+++ 
author = "kmplex" 
title = "Spring Configuration" 
date = "2021-02-02" 
description = "What is Spring Configurtaion?"  
series = ["Spring"] 
categories = ["dev"] 
+++
```

# Spring Configuration    

* @Configuration 은 Bean 설정의 메타 정보를 담고 있는 class.
  * Bean 설정의 `메타 정보`를 담고 있다.
  * xml 의 설정을 class 로 표현한 형태로 생각.

```kotlin
  class HappyNewYear
  
  @Configuration
  open class Config {
    @Bean
    open fun happyNewYear(): HappyNewYear = HappyNewYear()
  }

  @Test
  fun configuration() {
      val context = AnnotationConfigApplicationContext(Config::class.java)
      val bean = context.getBean(HappyNewYear::class.java) as HappyNewYear

      assertNotNull(bean)
  }
```

* method 로 표기되는 type / bean 외에는 모두 default 로 설정된다.
  * 그외 필요한 대부분의 설정들은 annotation으로 정의할 수 있다.
  
### Code config 

code config 를 사용하면서 느꼇던 장점은 크게 2가지.

* 컴파일 시점의 검사가 가능해진다. (IDE 의 기능을 모두 사용가능)
* 코드 재사용 가능 
  
주요한 설정 혹은 phase 별 분리되는 설정들은 properties 혹은 yml 로 분리하여 관리한다.   

* 외부 library 들의 의존성들을 관리할땐 xml 이 편한 경우도 있었음.
  * code config 로 wrapping 하여 사용하는 경우도 대부분이긴하지만..
  
### Component 와 Bean

`@Bean` 은 Component 와도 사용할 수 있다. `다만 동작이 살짝 달라진다.`

```kotlin
class HappyNewYear

class HappyNewYearWrapper

@Configuration
open class Config {
  @Bean
  open fun happyNewYear(): HappyNewYear = HappyNewYear()

  @Bean
  open fun happyNewYearWrapper() : HappyNewYearWrapper = HappyNewYearWrapper(happyNewYear())
}

@Component
open class Component {
  @Bean
  open fun happyNewYear(): HappyNewYear = HappyNewYear()

  @Bean
  open fun happyNewYearWrapper() : HappyNewYearWrapper = HappyNewYearWrapper(happyNewYear())
}
```

* 함수 호출로 인한 DI 시, 동작이 달라짐
  * configuration 에서 함수 호출로 DI 시, singleton 보장 
  * component 에서 함수 호출로 DI 시, 다른 객체를 반환한다.
  
Component 와 Bean을 함께 사용한다면, 함수 호출로 인한 DI 는 피하는게 좋음.


### Configuration 과 final  



---

todo
- BeanFactory / Application Context 차이점
 
 


