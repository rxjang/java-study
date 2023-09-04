# item69. 예외는 진짜 예외 상황에만 사용하라
## 잘못된 예외 사용 예시
``` java
try {
    int i = 0;
    while (true)
        range[i++].climb();
} catch (ArrayIndexOutOfBoundsException e) {
}
```
위 코드는 전혀 직관적이지 않을 뿐더러, 잘못된 추론을 근거로 성능을 높여보려했다. 
JVM은 배열에 접근할 때 마다 경계를 넘지 않는지 검사하는데, 일반적인 반복문도 배열 경계에 도달하면 종료한다. 
따라서 일 중복을 생략하려고 했지만, 세가지 면에서 잘못되었다.
1. 예외는 예외 상항에 쓸 용도로 설계되었으므로 JVM 구현자 입장에서는 명확학 검사만큼 빠르게 만들어야할 동기가 약하다.
2. 코드를 try-catch 블록 안에 넣으면 JVM이 적용할 수 있는 최적화가 제한된다.
3. 배열을 순회하는 표준 관용구는 앞서 걱정한 중복 검사를 수행하지 않는다.

``` java
for (Mountain m : range) 
    m.climb();
```
 그래서 잘못된 예시는 바로 위 코드보다 훨씬 느려졌다.
 
## 예외는 예외 상황에서만 사용하자
위 예시가 알려주든 예외는 오직 예외 상황에서만 써야한다. 절대로 일상적인 제어 흐름용을 쓰여선 안된다.  
이 원칙은 API 설계에서도 적용된다. **잘 설계된 API라면 클라이언트가 정상적인 제어 흐름에서 예외를 사용할 일이 없게 해야한다.**

### 상태검사 메서드, 옵셔널, 특정값중 하나를 선택하는 지침
1. 외부 동기화 없이 여러 스레드가 동시에 접근할 수 있거나 외부 요인으로 상태가 변할 수 있다면 옵셔널이나 특정 값을 사용하자.
2. 성능이 중요한 상황에서 상태 검사 메서드가 상태 의존적 메서드의 작업 일부를 중복 수행한다면 옵셔널이나 특정 값을 선택하자.
3. 다른 모둔 경우엔 상태 검사 메서드 방식이 조금 더 낫다고 할 수 있다. 