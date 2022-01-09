# 34. int 상수 대신 열거 타입을 사용하라
## 정수 열거 패턴
> 자바에서 열거 타입을 지원하기 전(1.5 이전)에는 <br/>
> `정수 열거 패턴(int enum pattern)`을 사용했습니다

클래스 내부에 static final 상수로 지정하여 사용<br/>
정수 열거 패턴에는 여러가지 **단점**이 있습니다

```java
public static final int APPLE_FUJI         = 0;
public static final int APPLE_PIPPIN       = 1;
public static final int APPLE_GRANNY_SMITH = 2;

public static final int ORANGE_NAVEL   = 0;
public static final int ORANGE_TEMPLE  = 1;
public static final int ORANGE_BLOOD   = 2;
```


* 타입 안전성이 보장되지 않습니다

* 동등 연산자(==)로 비교하더라도 잘못된 상황에 컴파일러는 아무런 경고 메시지를 출력하지 못합니다
```java
APPLE_FUJI == ORANGE_NAVEL
```
* 자바에서는 정수 열거 패턴을 위한 별도의 이름공간(namespace) 문법을 지원하지 않기 때문에 어쩔 수 없이 접두어(prefix)를 써서 이름 충돌을 방지해야만 한다.

```java
public static final APPLE_FUJI  = 0;
public static final ORANGE_FUJI = 0;
```

* 디버깅을 하더라도 단순 숫자 값만 보이기 때문에 큰 도움이 되지 않습니다
* 같은 정수 열거 그룹에 속한 모든 상수를 순회하는 방벙이 없습니다

## 열거 타입
자바 1.5에서는 정수 열거 패턴의 대안으로 `열거 타입(enum type)` 이 추가 되었습니다

```java
public enum Apple { FUJI, PIPPIN, GRANNY_SMITH }
public enum Orange { NAVEL, TEMPLE, BOLOOD }
```

### 열거타입의 특성
* 열거 타입 자체는 클래스입니다
* 열거 타입은 외부에서 접근할 수 있는 생성자를 제공하지 않으므로 사실상 'final' 입니다
* 컴파일타임 타입 안전성을 제공합니다 
* 참조(Reference) 타입이기 때문에 *null이 아니라면 항상 특정 값을 갖게 됩니다* 
* 관련된 임의의 필드나 메서드를 추가할 있습니다
