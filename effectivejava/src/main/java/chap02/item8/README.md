# item8. finalizer와 cleaner사용을 피하라

`finalizer`와 `cleaner`는 자바가 제공하는 객체 소멸자이다. 하지만 이 두 소멸자의 사용자는 권장되지 않는다. 이유는 아래와 같다.
1. 실행 시점에 대한 보장이 없다.
2. 실행 여부에 대한 보장이 없다.
3. 성능 저하를 유발한다. 
4. 보안 문제를 유발한다. 