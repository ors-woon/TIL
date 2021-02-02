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




---

todo
- BeanFactory / Application Context 차이점
- configuration 왜 open ? 
 


