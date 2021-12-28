# 20. 추상 클래스보다는 인터페이스를 우선하라
자바가 제공하는 다중 구현 메커니즘으로 인터페이스와 추상클래스가 존재합니다
자바8부터 인터페이스에 default method를 지원하여 두 메커니즘 모두 인스턴스 메서드를 구현 형태로 제공할 수 있습니다

### 둘의 차이점
* 인터페이스 - 다중 상속이 가능하고 구현한 클래스와 같은 타입으로 취급합니다. 또한 Java8 부터 default 메서드를 제공합니다
* 추상클래스 - 다중 상속이 불가하고, 구현체와 상하관계에 있습니다


## 인터페이스 장점
### 기존 클래스에도 손쉽게 새로운 인터페이스를 구현할 수 있다
* 인터페이스 - 추상 메서드를 추가하고, 클래스에 implements 구문을 추가하여 구현체임을 알립니다
* 추상클래스 - 계층 구조상 두 클래스의 공통 조상이어야 하며, 새로 추가된 추상 클래스의 모든 자손이 상속하게 된다.
### 믹스인 정의에 적합하다.
* 추상 클래스는 단일 상속만 가능하기 때문에 기존 클래스에 덧씌울 수 없습니디
### 인터페이스로는 계층구조가 없는 타입 프레임워크를 만들 수 있습니다
먼저 인터페이스로 살펴보자.
```java
public interface Singer {
    AudioClip sing(Song s);
}

public interface SongWriter {
    Song compose(int charPosition);
}

public interface SingSongWriter extends Singer, SongWriter {
    AudioClip strum();
    void actSensitive();
}
```


다음으로 추상클래스로 살펴보자.
```java
public abstract class Singer {
    abstract AudioClip sing(Song s);
}

public abstract class SongWriter {
    abstract Song compose(int charPosition);
}

public abstract class SingerSongWriter {
    abstract AudioClip sing(Song s);
    abstract Song compose(int charPosition);
    abstract AudioClip strum();
    abstract void actSensitive();
}
```
추상 클래스로 만들면 다중상속이 불가하여 새로운 추상클래스를 만들어서 클래스 계층을 표현할 수 밖에 없습니다. 따라서 이 계층구조를 만들기 위해서는 많은 조합이 필요하게 됩니다


## 디폴트 메서드 제약
### 자바8부터 인터페이스에서도 메서드를 구현할 수 있게 되었습니다다만, 아래와 같은 규칙을 지켜줘야 합니다
* @implSpec 자바독 태그를 붙여 사용하려는 default 메서드를 문서화합니다
* equals와 hashCode는 default 메서드로 제공해서는 안됩니다
* 인스턴스 필드를 가질 수 없습니다
* public이 아닌 정적 멤버를 가질 수 없습니다
* 만들지 않은 인터페이스에는 디폴트 메서드를 추가할 수 없습니다



