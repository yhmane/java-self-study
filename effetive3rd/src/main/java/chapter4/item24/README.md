# 24. 멤버 클래스는 되도록 static으로 만들라
## 중첩클래스란
*중첩 클래스(nested class)* 다른 클래스 안에 정의된 클래스를 말합니다.
중첩 클래스는 자신을 감싼 바깥 클래스에서만 쓰여야 하며, 그 외의 쓰임새가 있다면 톱 레벨 클래스로 만들어야 합니다

* 정적 멤버 클래스
* (비정적) 멤버 클래스
* 익명 클래스
* 지역클래스

이 중에서 정적 멤버 클래스를 제외한 나머지를 내부 클래스 (inner class)라고 합니다

### 정적 멤버 클래스
정적 멤버 클래스는 바깥 클래스의 private 멤버에도 접근할 수 있다는 점을 제외하고
일반 클래스와 쓰임새는 동일합니다

```java
public class OuterWithStaticClass {

    private String name;

    static class StaticClass {
        void hello() {
            OuterWithStaticClass outerWithStaticClass = new OuterWithStaticClass();
            outerWithStaticClass.name = "홍길동";
            System.out.println(outerWithStaticClass.name);
        }
    }
}
```

```java
public class Item24App {

    public static void main(String[] args) {

        OuterWithStaticClass.StaticClass staticClass = new StaticClass();
        staticClass.hello();
    }
}
```


### 비정적 멤버 클래스
구문상으로는 정적 클래스와 static이 붙어있고 없고의 차이입니다.
하지만 의미상으로 비정적 멤버 클래스는 바깥 클래스의 인스턴스와 암묵적으로 연결됩니다.  따라서 정규화된 this를 사용해 바깥 인스턴스의 메서드를 호출하거나 참조를 가져올 수 있습니다

```java
public class OuterWithNoneStaticClass {

    private final String name;

    public OuterWithNoneStaticClass(String name) {
        this.name = name;
    }

    public String getName() {
        NonStaticClass nonStaticClass = new NonStaticClass("noneStatic-class : ");
        return nonStaticClass.getNameWithOuter();
    }

    private class NonStaticClass {
        private final String noneStaticName;

        public NonStaticClass(String noneStaticName) {
            this.noneStaticName = noneStaticName;
        }

        public String getNameWithOuter() {
            return noneStaticName + OuterWithNoneStaticClass.this.name;
        }
    }
}
```

```java
public class Item24App {

    public static void main(String[] args) {
        OuterWithNoneStaticClass noneStaticClass = new OuterWithNoneStaticClass("고길동");
        System.out.println(noneStaticClass.getName());
    }
}
```


드물게 직접 `바깥 인스턴스의 클래스.new Member Class(args)`를 호출해 수동으로
만들기도 합니다. 하지만 이 관계 정보는 비정적 멤버 클래스의 인스턴스 안에 만들어져 메모리 공간을 차지하며, 생성시간도 더 걸립니다.

따라서,  중첩 클래스의 인스턴스가 바깥 클래스의 인스턴스와 *독립적으로 존재*해야 한다면 정적 멤버 클래스로 만드는게 좋습니다.


### 익명 클래스
* 이름이 없고 바깥 클래스의 멤버도 아닙니다
* 쓰이는 시점에 선언과 동시에 인스턴스가 만들어집니다
* 비정적인 문맥에서 사용될 때만 바깥 클래스의 인스턴스 참조가 가능하다
* 상수 정적변수 (static final) 외에는 정적 변수를 가질 수 없다.

```java
public class Anonymouss {

    private String name;

    public void hello() {
        HelloBot helloBot = new HelloBot() {
            @Override
            public void hello() {
                System.out.println("안녕하세요. 헬로우 봇입니다");
            }
        };
        helloBot.hello();
    }
}

interface HelloBot {
    void hello();
}
```

```java
public class Item24App {

    public static void main(String[] args) {
        Anonymouss anonymouss = new Anonymouss();
        anonymouss.hello();
    }
}

```

#### 익명 클래스의 제약사항
* 선언한 지점에서만 인스턴스를 만들수 있습니다
* 여러 인터페이스를 구현할 수 없고, 구현과 동시에 다른클래스 상속도 불가능합니다
* 익명 클래스 사용 클라이언트는 사용하는 익명 클래스가 상위타입에서 상속한 멤버외에는 호출이 불가능합니다
* 람다(자바7) 등장 이전에는 작은 함수 객체나 처리 객체 구현에 사용되었지만 java8 부터는 람다를 제공합니다

### 지역 클래스
네 가지 중첩 클래스 중 가장 드물게 사용됩니다.
지역 클래스는 지역 변수를 선언할 수 있는 곳이면 어디서든 선언할 수 있고, 유효 범위도 지역 변수와 같습니다.

```java
public class LocalClass {

    public void hello() {
        class LocalExample {
            private String name;

            public LocalExample(String name) {
                this.name = name;
            }

            public String getName() {
                return name;
            }
        }

        LocalExample localExample = new LocalExample("윤호호");
        System.out.println(localExample.getName());
    }
}

```

```java
public class Item24App {

    public static void main(String[] args) {
        LocalClass localClass = new LocalClass();
        localClass.hello();
    }
}
```

#### 지역 클래스는 다른 중첩 클래스들의 공통점을 하나씩 가지고 있다.
* 멤버 클래스처럼 이름을 가질수 있고 반복해서 사용할 수 있습니다
* 비정적 문맥에서 사용될 때만 바깥 인스턴스를 참조할 수 있습니다
* 정적 멤버는 가질 수 없으며, 가독성을 위해 짧게 작성되어야 합니다
