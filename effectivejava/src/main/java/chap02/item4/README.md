# 아이템4. 인스턴스화를 막으려거든 private 생성자를 사용하라.
단순히 정적 메서드와 정적 필드만을 담은 클래스가 필요하다. 이 때, 단순히 추상 클래스를 만드는 것으로는 인스턴스 화를 막을 수 없다. 
하위 클래스를 만들어 인스턴스화 할 수 있기 때문이다. **private 생성자를 추가하면 클래스의 인스턴스 화를 막을 수 있다. 
``` java
public class UtilityClass {
    // 기본 생성자가 만들어지는 것을 막는다. (인스턴스화 방지용)
    private UtilityClass() {
        throw new AssertionError();
    }
    ...
}
```
* private 생성자어서 외부에서 접근할 수 없다.
* 상속을 불가능하게 한다. 