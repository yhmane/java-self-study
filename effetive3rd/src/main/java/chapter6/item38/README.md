# 38. 확장할 수 있는 열거 타입이 필요하면 인터페이스를 사용하라
타입 안전 열거 패턴은 확장이 가능하나, 열거 타입은 확장을 할 수 없습니다.
하지만, 인터페이스와 그 인터페이스를 구현하는 기본 열거 타입을 함께 사용해 같은 효과를 낼 수 있습니다



## 타입 안전 열거 패턴(typesafe enum pattern)
enum이 나오기전 jdk 1.5 밑에 버전에서는 아래와 같이 사용하였습니다
```java
public final class Shape {

    private String polygon;

    private Shape() {
    }

    private Shape(String polygon) {
        this.polygon = polygon;
    }

    public static final Shape TRIANGLE = new Shape("triangle");
    public static final Shape RECTANGLE = new Shape("rectangle");
    public static final Shape PENTAGON = new Shape("pentagon");
}
```


## 열거타입 (enum)
Enum class cannot inherit from classes

```java
public enum Shape {
    TRIANGLE, RECTANGLE, PENTAGON
}
```

* 열거 타입은 타입 안전 열거 패턴보다 우수합니다
* 단, 타입 안전 열거 패턴은 확장이 가능하나 열거 타입은 확장할 수 없습니다
* 열거 타입을 확장하려면 열거 타입이 임의의 인터페이스를 구현할 수 있다는 사실을 이용하면 됩니다


## 인터페이스를 이용한 확장 가능 열거 타입
```java
public interface Operation {
    double apply(double x, double y);
}
```

```java
public enum BasicOperation implements Operation {

    PLUS("+") {
        @Override
        public double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS("-") {
        @Override
        public double apply(double x, double y) {
            return x - y;
        }
    },
    TIMES("*") {
        @Override
        public double apply(double x, double y) {
            return x * y;
        }
    },
    DIVIDE("/") {
        @Override
        public double apply(double x, double y) {
            return x / y;
        }
    };

    private final String symbol;

    BasicOperation(String symbol) {
        this.symbol = symbol;
    }
}
```

열거 타입인 BasicOperation은 확장할 수 없습니다.
다만, 인터페이스인 Operation을 이용해 확장할 수 있고
이 인터페이스를 연산의 타입으로 이용할 수 있습니다

### 다른 열거 타입을 추가
```java
public enum ExtendedOperation implements Operation {

    EXP("^") {
        @Override
        public double apply(double x, double y) {
            return Math.pow(x, y);
        }
    },
    REMAINDER("%") {
        @Override
        public double apply(double x, double y) {
            return x % y;
        }
    };

    private final String symbol;

    ExtendedOperation(String symbol) {
        this.symbol = symbol;
    }
}
```

Operation 인터페이스를 구현하면 다른 열거 타입에서도 확장할 수 있습니다



---
## enum 타입 확인
1. Enum타입을 넘겨 순회하기 (class literal 넘기기)
```java
public static void main(String[] args) {
	double x = 10;
	double y = 2;
	test(ExtendedOperation.class, x, y);
}

public static <T extends Enum<T> & Operation> void test(Class<T> opEnumType, double x, double y) {

	for (Operation op : opEnumType.getEnumConstants()) {
		System.out.printf(“%f %s %f = %f%n”, x, op, y, op.apply(x, y));
	}
}
```

ExtendedOperation의 class 리터럴

<T extends Enum<T> & Operation>
타입이 Enum타입이면서, Operation을 구현하는 클래스

10 ^ 2 = 100
10 % 2 =0

2. 열거 타입의 리스트 넘기기
```java
public static void main(String[] args) {
	double x = 10;
	double y = 2;
	test(Arrays.asList(ExtendedOperation.values()), x, y);
}
    
public static void test(Collection<? extends Operation> opSet, double x, double y) {
	for (Operation op : opSet) {
		System.out.printf(“%f %s %f = %f%n”, x, op, y, op.apply(x, y));
	}
}
```

열거 타입의 리스트를 넘겨 <? extends Operation>인 한정적 와일드 카드 타입으로 지정

10 ^ 2 = 100
10 % 2 =0

### 정리
* 열거 타입끼리 구현을 상속 할수는 없습니다
* 확장할 수 있는 열거 타입이 필요한 경우 인터페이스 정의를 구현합니다
