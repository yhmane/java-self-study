# 03. Private 생성자나 열거 타입으로 싱글턴임을 보증하라
> *싱글턴(singleton)이란?*
> 인스턴스를 오직 하나만 생성할 수 있느 클래스

## 싱글턴이란?
* 인스턴스를 오직 하나만 생성할 수 있는 클래스를 말합니다. (Application내에서 단 1개의 인스턴스만 생성할 수 있는 클래스)
* 한번의 객체 생성으로 재사용이 가능하기 때문에 메모리 낭비를 방지할 수 있습니다.
* 싱글톤으로 생성된 객체는 전역성을 띄기에 다른 객체와 공유가 용이합니다.

## 싱글턴을 언제 언제 쓸까요?
* Stateless 객체일 때 (상태값이 없는 객체, 함수처럼)
* 유일한 시스템 컴포넌트일 때

## 싱글턴의 단점
* 싱글턴의 역할을 복잡하게 부여할 경우, 객체간의 결합도가 높아지는 문제가 발생살 수 있습니다.
* 멀티 쓰레드 환경에서 동기화 처리 문제가 있습니다.
* 인터페이스를 구현한 싱글턴 객체가 아니라면 mock 객체를 만들 수 없기에 테스트가 어렵습니다.
    * 그 싱글턴이 어떤 interface를 implement 하는게 아니라면 mock으로 바꿔치기 어렵습니다


## 싱글턴을 만드는 방법
1. public final field 사용
2. static factory 사용
3. enum 사용 (가장 좋은 방법)



Private 생성자로 감추고, public static 멤버를 마련
1. 필드 방식의 싱글턴
```java
public class Elvis {

    public static final Elvis INSTANCE = new Elvis();

    private Elvis() { }

    public void leaveTheBuilding() {
        System.out.println("Whoa baby, I'm outta here!");
    }

    public static void main(String[] args) {
        Elvis elvis = Elvis.INSTANCE;
        elvis.leaveTheBuilding();
    }
}
```

### 장점
* API를 보면 Singleton임이 분명하고, 간단합니다

### 단점
* reflection을 사용하여 private 생성자를 호출할 수 있습니다



2. 정적 팩터리 방식의 싱글턴
```java
public class Elvis {
    
    private static final Elvis INSTANCE = new Elvis();
    
    private Elvis() { }
    public static Elvis getInstance() { return INSTANCE; }

    public void leaveTheBuilding() {
        System.out.println("Whoa baby, I'm outta here!");
    }

    public static void main(String[] args) {
        Elvis elvis = Elvis.getInstance();
        elvis.leaveTheBuilding();
    }
}
```

### 장점
* Singleton이 아니게 바꾸고 싶을 때, API를 바꾸지 않고 할 수 있습니다
* getInstance()는 항상 같은 객체의 참조를 반환하므로 다른 Elvis 인스턴스가 만들어지지 않습니다
* 또한 정적 팩터리를 제네릭 싱글턴 팩토리로 만들 수 있습니다. 
* 메서드 참조를 공급자로 사용할 수 있습니다. Elvis::getInstance

### 단점
* reflection을 사용하여 private 생성자를 호출할 수 있습니다.
* 위의 장점들이 필요 없다면 방법1이 낫습니다.


3. Enum 방식의 싱글턴

```java
public enum Elvis {

    INSTANCE;

    public void leaveTheBuilding() {
        System.out.println("leave the building");
    }

    // 이 메서드는 보통 클래스 바깥(다른 클래스)에 작성해야 한다!
    public static void main(String[] args) {
        Elvis elvis = Elvis.INSTANCE;
        elvis.leaveTheBuilding();
    }
}
```
가장 이상적인 방법입니다

### 장점
* 간결하고 추가 노력 없이 직렬화할 수 있습니다.
* 리플렉션의 경우에도 막아줄 수 있습니다. 따라서, 가장 이상적인 방법이라 할 수 있습니다

### 단점
* 단, 만드려는 싱글턴이 Enum 외에 클래스를 상속해야 한다면 이 방법은 쓸 수 없습니다

#### 주의할 점
필드, 정적팩터리 중의 하나의 방식으로 싱글턴 클래스를 직렬화할 경우, Serializable 만으로는 부족합니다.<br/> 
모든 인스턴스 필드를 일시적으로 선언하고, resolve 메서들 제공해야 합니다. (ITEM 89) <br/>
이렇게 하지 않으면 역지렬화할때 마다 새로운 인스턴스가 만들어집니다.
