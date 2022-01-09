# 35. ordinal 메서드 대신 인스턴스 필드를 사용하라

## ordinal을 잘못 사용한 예
```java
public enum Ensemble {
    SOLO, DUET, TRIO, QUARTET, QUINTET,
    SEXTET, SEPET, OCTET, NONET, DECTET;

    public int numberOfMusicians() { return ordinal() + 1; }
}
```
* 순서가 바뀔 경우 ordinal의 값도 바뀌게 됩니다

## 인스턴스 필드를 사용하라
```java
public enum Ensemble {
    SOLO(1), DUET(2), TRIO(3), QUARTET(4), QUINTET(5),
    SEXTET(6), SEPET(7), OCTET(8), DOUBLE_QUARTET(8),
    NONET(9), DECTET(10), TRIPLE_QUARTET(12);

    private final int numberOfMusicians;
    Ensemble(int numberOfMusicians) {
        this.numberOfMusicians = numberOfMusicians;
    }
    public int numberOfMusicians() { return this.numberOfMusicians; }
}
```

이처럼 인스턴스 필드를 두어 값을 초기화 하여 사용하는 방법으로 사용하는 것이 좋습니다 
