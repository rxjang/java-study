# item71. 필요 없는 검사 예외 사용은 피하라
검사 예외를 잘 사용하면 API와 프로그램의 질을 높일 수 있다. 
결과를 코드로 반환허가나 비검사 예외를 던지는 것과 달리 검사 얘외는 발생한 문제를 프로개르머가 처리하여 안전성을 높이게 해준다.

## 검사 예외를 사용해야 할 때
1. API를 제대로 사용해도 발생할 수 있는 예외
2. 프로그래머가 의미 있는 조취를 취할 수 있는 경우  

둘 중 어디에도 해당하지 않는다면 비검사 예외를 사용하자. 

## 메서드가 단 하나의 검사 예외를 던진자면
검사 예외가 프로그래머에게 지우는 부담은 메서드가 단 하나의 검사 예외를 던질 때가 특히 크다.
오직 하나의 검사 예외 때문에 API 사용자는 try 블록을 추가해야 하고 스트림에 직접 사용하지 못하기된다.
이럴 때는 검사 예외를 안던지는 방법이 없는지 고민해 보자.

## 검사 예외를 회피하는 방법
1. 적절한 결과 타입을 담은 옵셔널을 반환한다.
2. 검사 예외를 던지는 메서드를 2개로 쪼개서 비검사 예외로 바꾼다.
``` java
// 검사 예외를 던지는 메서드 - 리펙터링 전
try {
    obj.action(args);
} catch (TheCheckedException e) {
    // 예외 상황에 대처한다.
    ...
}
```
``` java
// 상태 검사 메서드와 비검사 예외를 던지는 메서드 - 리펙터링 후
if (obj.actionPermitted(args)) {
    obj.action(args);
} else {
    // 예외 상황에 대처한다.
    ...
}
```