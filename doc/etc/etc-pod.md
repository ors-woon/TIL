```
+++ 
author = "kmplex" 
title = "kubernetes pod" 
date = "2021-03-21" 
description = "memo"  
series = ["kubernetes"] 
categories = ["etc"] 
+++
```

# 임시로 정리

추후 필요하면, 자세히 정리한다.

Pod -> kubernetes 의 기본 요소로, 동일한 namespace로 구성된 container 의 그룹을 의미한다. (같은 워커 노드에서 실행된다.)

기본적으로 한 포드에 하나의 프로세스를 포함시키는데, 두개 이상의 컨테이너를 포함시켜야한다면, 아래 질문이 대해 고민해봐야한다.

- 컨테이너가 같이 실행되어야 할 필요가 있는가 ? (ex logstash)
- 컴포넌트가 독립적 인가? (web server / data server)
- 함께 혹은 개별적으로 컨테이너가 조정되어야하는가 ? 

