```
+++ 
author = "kmplex" 
title = "Spring MockRestServiceServer" 
date = "2021-05-11" 
description = "RestTemplate Mokcing"  
series = ["Spring"] 
categories = ["dev"] 
+++
```

# Spring MockRestServiceServer  

인수테스트 혹은 API 테스트를 하다보면, 외부 API를 호출하는 부분에 mocking 을 하고싶어질때가 있다.    
UnitTest 의 경우, Layer 를 갈라 RestTemplate을 mocking 하는 방향으로 TC를 작성하지만, 세부적인 핸들링을 하기 위해선, 조금 번잡한 코드가 추가되어야한다.

> equals 가 구현안되어있으면 mocking 이 어렵거나, API의 302 상황을 테스트해보고 싶은 경우...  여러모로 복잡해진다.

Spring에서는 RestTemplate 의 HttpRequestFactory를 Mocking 하는 방식으로, API Test 를 쉽게 짤 수 있도록 지원하는데,
이때 사용하는 객체가 `MockRestServiceServer` 이다.

> spring 3.2 부터 지원한다.

## 예제 코드






