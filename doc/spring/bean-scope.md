```
+++ 
author = "kmplex" 
title = "Spring Bean scope" 
date = "2021-01-31" 
description = "Spring bean scope"  
series = ["Spring"] 
categories = ["dev"] 
+++
```

# Spring Scope    

* [spring bean scope 종류](#spring-bean-scope)
* [singleton](#singleton)
* [prototype](#prototype)

## spring bean scope  

- singleton (default)
- prototype
  
아래 scope 는 웹 환경에서만 의미가 있다.

- request
- session / global session
- application

## singleton 

한 클래스에 하나의 object 만 생성되는 걸 보장한다.

```kotlin
class Trevi()

@Test
@DisplayName("bean scanner example")
fun beanScanning(){
    val ctx = AnnotationConfigApplicationContext("chap01")
    val trevi1 = ctx.getBean("trevi", Trevi::class)
    val trevi2 = ctx.getBean("trevi", Trevi::class)

    assertNotNull(trevi1)
    assertNotNull(trevi2)

    assertEquals(trevi1, trevi2)
}
```

대상 객체에 별도의 설정이 없다면, DL / DI 로 bean을 가져올때 동일한 객체를 가져온다.

> spring default 설정이므로, class 내에 상태값을 갖으면 안된다.

singleton(`정확히는 @bean`) 사용 시, Configuration / Component 의 차이가 존재한다.    
해당 개념은 [여기]()서 정리예정.

## prototype

한 클래스에 매번 다른 object 를 생성한다.

```kotlin
    @Component
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    class ProtoTypeBean

    @Test
    @DisplayName("prototype test")
    fun prototype_scope(){
        val ac = AnnotationConfigApplicationContext(ProtoTypeBean::class.java)

        val prototype = ac.getBean(ProtoTypeBean::class.java)
        val prototype1 = ac.getBean(ProtoTypeBean::class.java)

        assertNotEquals(prototype, prototype1)
    }
```

* IOC 의 기본 개념은 Container 가 Object 를 관리한다는 것이다.

    * 단, prototype 은 bean 을 제공하고 나면, Container 가 더이상 object 를 관리하지 않는다.

* 또한 주입 대상이 Singleton 일 경우 `주입 받은 Bean 에 종속적인 생명 주기를 갖는다.`
    * singleton 객체에 주입 시, 1개만 참조되어 관리되므로 `sigleton 취급`을 받는다.
    * 이를 막기 위해 DL 방식을 사용하거나, spring 에서 제공하는 proxyMode 를 사용할 수 있다.

```kotlin
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class ProtoTypeBean


@Component
class ProtoTypeClientSingleton (val bean1:ProtoTypeBean)

@Test
@DisplayName("inject prototype to singleton ")
fun test_injectSingleton(){
    val ac = AnnotationConfigApplicationContext(ProtoTypeBean::class.java, ProtoTypeClientSingleton::class.java)

    val prototype = ac.getBean(ProtoTypeClientSingleton::class.java)
    
    // 같은 bean 을 return
    assertEquals(prototype.bean1, prototype.bean1)
}
```

#### Prototype DL 방법

* ApplicationContext 를 직접 주입받아 DL
    * 단점) ServiceCode 에 Spring Api  가 등장하여, 계층이 깨진다.
* ObjectFactory 사용 / ServiceLocatorFactoryBean 사용
    * 단점 ) 불필요한 interface 가 생김.
* 추상 클래스를 이용한 Method 주입 
    * 단점 ) controller / Service Class 가 추상 클래스로 관리되는게 자연스럽지 않음
* Provider 사용 
    * 위 대안들에 비해 가장 깔끔
* proxyMode 사용
    * Scope annotation 사용 시, proxyMode 사용

```kotlin
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS )
class ProtoTypeBean
```

prototype bean 을 직접 넘기는 방식이 아닌, Proxy 객체로 감아 넘기는 방식.

// todo TC에서는 안먹는데, test phase 에 bean 을 생성 / 관리하는 방식이 다른거 같음. - 확인해봐

## request



## session / global session
## application











