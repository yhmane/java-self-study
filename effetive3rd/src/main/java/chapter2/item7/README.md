# 07. 다 쓴 객체 참조를 해제하라
> C, C++ 처럼 메모리를 직접 관리해야 하는 언어를 쓰다가 자바처럼 가비지 컬렉터를 갖춘 언어로 넘어오면 조금 더 편안해집니다. 하지만, 메모리 관리에 더 이상 신경 쓰지 않아도 된다고 오해할 수 있는데 절대 그렇지 않습니다

## 메모리 누수가 일어나는 위치는 어디인가?
```java
public class Stack {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public Object pop() throws Exception {
        if (size == 0) {
            throw new IllegalStateException("stack 사이즈가 0일 경우 오류가 발생합니다");
        }
        return elements[--size];
    }

    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }
}
```

* 코드상에는 문제가 없어 보입니다.
* 허나,  메모리 누수가 일어나 프로그램이 예기치 않게 종료될 수 있습니다 .
* 이 코드에서는 스택이 커졌다 줄어들었을 때, 스택에서 꺼내진 객체들을 가비지 컬렉터가 회수하지 않습니다.
* 그 이유는 다 쓴 참조를 여전히 가지고 있기 때문입니다.

### 가비지 컬렉터에서 메모리 누수
* 객체 참조 하나를 살려두면 가비지 컬렉터는 그 객체뿐 아니라 그 객체가 참조하는 모든 객체를 회수하지 못합니다
* 단, 몇개의 객체가 매우 많은 객체를 회수하지 못하게 할 수 있고, 잠재적으로 성능에 악영향을 줄 수 있습니다

#### 가비지컬렉터에서 메모리 누수 해결방법 - 다 쓴 객체 참조에 null을 할당
```java
public Object pop() {
    if (size == 0) {
        throw new IllegalStateException("stack 사이즈가 0일 경우 오류가 발생합니다");
    }
    Object result = elements[--size];
    elements[size] = null; // 다 쓴 참조 해제
    return result;
}
```

해법은 간단합니다. Null 처리를 해주면 가비지 컬렉터는 자원을 회수해 갑니다.

#### null 처리에 따른 이점
```java
elements[--size];
elements[size] = null; 
```
다 쓴 참조를 null 처리할 경우, 실수로 사용하게 되면 NPE가 발생하게 됩니다.
만약,  null을 할당하지 않았더라면 프로그램은 의도치 않은 결과를 내어 찾기 힘든 버그를 발생시킬 수도 있습니다.

#### 메모리를 직접 관리하는 클래스
* 위와 같이 메모리를 직접 관리하는 클래스라면 가비지 컬렉터를 잘 활용해야 합니다
* 이러한 클래스는 scope 밖의 범위가 유효한지 가비지 컬렉터가  알 수 없습니다
* 가비지 컬렉터가 보기에는 활성/비활성 영역 모두 유효한 객체이기 때문입니다
* 이러한 경우 프로그래머는 비활성 영역이 되는 순간 null 처리를 하는게 좋습니다

### 캐시에서 메모리 누수
* 캐시 역시 메모리 누수를 일으키는 주범입니다
* 객체 참조를 캐시에 넣고 이 사실을 까맣게 잊고 놔두는 경우가 있습니다
* 캐시에 자원이 계속 쌓이게 되면 빠르게 사용하기 위해 만든 캐시가의 의미가 퇴색되기도 합니다

#### 캐시에서 메모리 누수 해결방법
1. WeakHashMap 사용
* 키를 참조하는 동안만 엔트리가 살아 있는 캐시가 필요한 상환인 경우에는 WeakHashMap을 사용하는게 좋습니다
* 따로 관리를 하지 않아도, 사용하지 않으면 GC가 회수해 갑니다
2. 부가적인 방법으로 엔트리를 청소
* 캐시를 만들때 엔트리의 유효기간을 정확히 정의하기 어렵기에 시간이 지날수록 엔트리의 가치를 떨어뜨리는 방식을 사용합니다
* LinkedHashMap의 removeEldestEntry 메서드가 있습니다

```java
public class Item7App {

    public static void main(String[] args) {
        WeakHashMap<String, Object> map = new WeakHashMap<>();

        String key1 = new String("key1");
        String key2 = new String("key2");

        map.put(key1, "test 1");
        map.put(key2, "test 2");

        key1 = null;

        System.gc();
        map.entrySet()
            .stream()
            .forEach(System.out::println);
    }
}
```

WeakHashMap 주의사항
* String을 literal 방식으로 할당할 경우 gc의 대상이 되지 않습니다
  (참조 : [Java - Collection - Map - WeakHashMap (약한 참조 해시맵) - 조금 늦은, IT 관습 넘기 (JS.Kim)](http://blog.breakingthat.com/2018/08/26/java-collection-map-weakhashmap/))


### 리스너(listener) 혹은 콜백(callback)에서 메모리 누수
* 클라이언트가 콜백을 등록만 하고 해지하지 않았을 경우 발생합니다
* 조치를 하지 않을 경우 콜백은 계속 쌓이게 됩니다

#### 리스너(listener) 혹은 콜백(callback) 누수 해결 방법
* 약한 참조로 콜백을 저장하면 가비지 컬렉터가 즉시 수거해 갑니다
* 해결방법으로 WeakHashMap을 사용할 수 있습니다
