# 18. 상속보다는 컴포지션을 사용하라
상속은 코드를 재사용하는 강력한 수단이지만, 항상 최선은 아니다.

## 상속이 위험한 이유
* *캡슐화*를 깨트립니다.
    * 상위클래스의 변수와 메소드가 하위클래스에 노출되기 때문
* 하위 클래스가 상위 클래스에 강하게 의존합니다
    * 내부 구현이 달라지면 하위 클래스에 수정이 불가피해질 수 있습니다

### 잘못된 상속 사용 예시
```java
public class InstrumentedHashSet <E> extends HashSet<E> {
    private int addCount = 0;

    public InstrumentedHashSet(){}

    public InstrumentedHashSet(int initCap, float loadFactor){
        super(initCap, loadFactor);
    }

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c);
    }

    public int getAddCount() {
        return addCount;
    }
}

public class Item18 {
    public static void main(String[] args) {
        InstrumentedHashSet<String> s = new InstrumentedHashSet<>();
        s.addAll(List.of(“틱”, “탁탁”, “펑”));

        System.out.println(s.getAddCount());
    }
}
```
* getAddCount()의 결과가 3을 반환하리라 생각하겠지만 6을 반환한다. 그 이유는 HashSet의 addAll 메서드가 add 메서드를 사용해 구현된 데 있다.
* 하위 클래스에서 addAll 메서드를 재정의하지 않으면 문제를 고칠 수 있다.
    * 하지만 이처럼 자신의 다른 부분을 사용하는 ‘자기사용’ 여부는 해당 클래스의 내부 구현방식에 해당하며, 자바 플랫폼 전반적인 정책인지, 그 다음 릴리즈에도 유지될지 알 수 없다.

* 그렇다면 재정의 대신 새로운 메서드를 추가하면 괜찮을까?
    * 괜찮은 방법이라 생각할수도 있지만, 위험이 전혀 없는 것은 아니다.
    * 다음 릴리스에 상위 클래스에 새 메서드가 추가 됐는데, 운 없게도 하필 추가된 메서드와 시그니처가 같고 반환 타입이 다를 수도 있다. 컴파일 문제가 바로 발생한다.


## 상속의 대안으로 컴포지션과 전달
### *컴포지션**

* 기존 클래스를 새로운 클래스의 구성요소로 사용하는 설계 방법입니다 (Has-A 관계)
* 기존 클래스를 확장하는 대신 새로운 클래스를 만들고 private 필드로 기존 클래스의 인스턴스를 참조합니다

```java
class Engine {
	private String type;
}
    
public class Car {
	
	private final Engine engine; 
    
	public Car(){
		engine  = new Engine();
	}
}
```

### *전달(forwarding)*

* 새 클래스의 인스턴스 메소드를 통해 기존 클래스의 메소드를 호출하고 그 결과를 반환합니다
* 호출자와 피호출자 클래스 사이에 중간 매개체를 둠으로써 직접적인 의존 관계를 끊어냅니다

```java
public class ForwardingSet<E> implements Set<E> {
  private final Set<E> s;

  public ForwardingSet(Set<E> s) {
    this.s = s;
  }

  public void clear() {
    s.clear();
  }

  public boolean contains(Object o) {
    return s.contains(o);
  }

  public boolean isEmpty() {
    return s.isEmpty();
  }

  public int size() {
    return s.size();
  }

  public Iterator<E> iterator() {
    return s.iterator();
  }

  public boolean add(E e) {
    return s.add(e);
  }

  public boolean remove(Object o) {
    return s.remove(o);
  }

  public boolean containsAll(Collection<?> c) {
    return s.containsAll(c);
  }

  public boolean addAll(Collection<? extends E> c) {
    return s.addAll(c);
  }

  public boolean removeAll(Collection<?> c) {
    return s.removeAll(c);
  }

  public boolean retainAll(Collection<?> c) {
    return s.retainAll(c);
  }

  public Object[] toArray() {
    return s.toArray();
  }

  public <T> T[] toArray(T[] a) {
    return s.toArray(a);
  }

  @Override
  public boolean equals(Object o) {
    return s.equals(o);
  }

  @Override
  public int hashCode() {
    return s.hashCode();
  }

  @Override
  public String toString() {
    return s.toString();
  }
}
```



```java
public class InstrumentedSet<E> extends ForwardingSet<E> {
  private int addCount = 0;

  public InstrumentedSet(Set<E> s) {
    super(s);
  }

  @Override
  public boolean add(E e) {
    addCount++;
    return super.add(e);
  }

  @Override
  public boolean addAll(Collection<? extends E> c) {
    addCount += c.size();
    return super.addAll(c);
  }

  public int getAddCount() {
    return addCount;
  }
}

public class Item18 {
  public static void main(String[] args) {
    InstrumentedHashSet<String> s = new InstrumentedHashSet<>();
    s.addAll(List.of("틱", "탁탁", "펑"));
    System.out.println(s.getAddCount());
  }
}
```

HashSet을 구현체로 사용하게 되더라도, 전달 클래스를 통해 호출하기 때문에
실제 구현체의 addAll() 내부에서 add()가 self-use 되더라도  InstrumentedSet의 재정의한 add()가 호출될 일은 없음으로 상속때와 다르게 결과는 3이 나오게 됩니다


## 장점
- 새로운 클래스는 기존 클래스의 내부 구현방식에 영향 받지 않습니다
- 기존 클래스에 새로운 메소드가 추가되더라도 영향 받지 않습니다
- 특히 래퍼 클래스를 생성해 낼 수 있는 적당한 인터페이스(여기 예제에서는 Set 인터페이스)가 존재한다면, 래퍼 클래스를 통해 견고하고 유연한 확장이 가능합니다

# 결론

* 상속은 강렬하지만 캡술화를 해친다는 문제가 있다.
* 상속은 상위 클래스와 하위 클래스가 is-a 관계일 때만 써야 한다.
* 상위 클래스와 하위 클래스의 패키지가 다를 경우에는 is-a 관계라도 문제가 발생할 수 있다.
* 상속의 취약점을 피하려면 상속 대신 컴포지션 전달을 사용하자

