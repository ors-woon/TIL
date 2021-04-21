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

Pod -> kubernetes 의 기본 요소로, 동일한 namespace로 구성된 container 의 그룹을 의미한다. 
(같은 워커 노드에서 실행된다.)

기본적으로 한 포드에 하나의 프로세스를 포함시키는데, 두개 이상의 컨테이너를 포함시켜야한다면, 아래 질문이 대해 고민해봐야한다.

- 컨테이너가 같이 실행되어야 할 필요가 있는가 ? (ex logstash)
- 컴포넌트가 독립적 인가? (web server / data server)
- 함께 혹은 개별적으로 컨테이너가 조정되어야하는가 ? 


Q. nginx 와 web server 는 별도의 pod 로 관리되어야할까 ?

- 이전엔 같은 머신 안에서 nginx 1대에 tomcat 3 대로 구성되어 있었음
- 1:1 로 증설되진 않을거 같으니, 다른 pod 로 관리되어야하지 않을까 ? 
- 책을 조금 더 읽어보고, 나중에 와서 다시 생각해보자

보통 같은 Pod 으로 관리된다고함
