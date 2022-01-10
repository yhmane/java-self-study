# 40 @Override 애너테이션을 일관되게 사용하라
@Override는 메서드 선언에만 달 수 있으며, 이 애너테이션이 달렸다는 것은 상위 타입의 메서드를 재정의했음을 뜻합니다. 에 애너테이션을 일관되게 사용하면 여러 가지 악명 높은 버그들을 예방해줍니다

```java
public class Bigram {
    private final char first;
    private final char second;

    public Bigram(char first, char second) {
        this.first = first;
        this.second = second;
    }

    public boolean equals(Bigram bigram) {
        return bigram.first == this.first &&
                bigram.second == this.second;
    }

    public int hashCode() {
        return 31 * first + second;
    }

    public static void main(String[] args) {
        Set<Bigram> s = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                s.add(new Bigram(ch, ch));
            }
        }

        System.out.println(s.size());
    }
}
```
Main () 메서드를 살펴보면 바이그램 26개를 10번 반복해 집합에 추가한 다음 그 집합의 크기를 출력합니다. Set은 중복을 허용하지 않으니 26이 출력될거 같지만 실제로는 260이 출력됩니다

-> 여기서 equals를 재정의한게 아니라 다중정의를 하였다.

HashSet은 내부적으로 equals 메서드를 기반으로 객체의 논리적 동치적(equals) 검사를 실시한다. 하지만 자세히 보면 equals메서드의 파라미터 타입이 Bigram이다. equals 메서드를 재정의 한게 아니라 Overloading 하였습니다
즉, equals를 재정의 하려면 파라미터 타입이 Object이어야 합니다

```java
@Override
public boolean equals(Object bigram) {
    if(!(o instanceof Bigram)) {
        return false;
    }
    Bigram b = (Bigram) bigram;
    return b.first == this.first &&
        b.second == this.second;
}
```


### 정리
* 상위 클래스의 메서드를 재정의 하는 모든 메서드에 @Override 애너테이션을 다는게 좋습니다
* 인터페이스를 상속한 구체 클래스인데 아직 구현하지 않은 추상 메서드가 남아있다면
  컴파일러가 알려줍니다
* Java 8 부터 Default 메서드의 사용이 가능해 지면서, 인터페이스의 메서드를 재정의 할 때도 사용할 수 있습니다
* 추상클래스나 인터페이스에서는 상위 클래스나 상위 인터페이스를 재정의하는 모든 메서드에 @Override를 다는것이 좋습니다
