```
+++ 
author = "kmplex" 
title = "change git author" 
date = "2021-02-13" 
description = "change the incorrectly written author."  
series = ["Git"] 
categories = ["etc"] 
+++
```

# git author 수정

집에서 개인 맥북으로 일을 하다보니 기본 계정이 회사 계정으로 되어있는데, git repo 를 새로 파거나 clone 후 commit 하면, 회사 계정으로 commit 된다.

개인 repo 에 회사 명이 적혀있으면 부끄럽기때문에 .. 매번 지우는 작업을 하고 있는데, 자꾸 까먹어서 여기에 저장해둔다.

## 요약 

```text
1. git rebase -i HEAD~${num}  OR git rebase -i -p hash 
2. edit 으로 수정할 commit 선택 
3. git commit --amend --author="name <email>"
4. git rebase --continue
```

## 추가 

```text
git config --local user.name ""
git config --local user.email ""

# 확인
git config --list
```


