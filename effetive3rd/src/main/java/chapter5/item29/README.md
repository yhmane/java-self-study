# 29. 이왕이면 제네릭 타입으로 만들라
JDK가 제공하는 제네릭 타입과 메서드를 사용하는 일은 일반적으로 쉬운편이지만,<br/>
제네릭 타입을 새로 만드는 일은 조금 더 어렵습니다 <br/>
그래도 이왕이면 제네릭 타입으로 만든다면 충분한 값어치는 있습니다

### Object 기반 스택

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
 
   public Object pop() {
      if (size == 0)
         throw new EmptyStackException();
      Object result = elements[--size];
      elements[size] = null;
      return result;
   }
 
   private void ensureCapacity() {
      if (elements.length == size)
         elements = Arrays.copyOf(elements, 2 * size);
   }
}
```

* Object 기반 스택은 클라이언트가 스택에서 꺼낸 객체를 형변환 해야 합니다. 이때 런타임 오류가 날 위험성이 있습니다.


### 제네릭 스택 변환 - 컴파일 불가

```java
public class Stack1<E> {

    private E[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack1() {
        // 컴파일 에러 발생
        // elements = new E[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(E e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public E pop() {
        if (size == 0)
            throw new EmptyStackException();
        E result = elements[--size];
        elements[size] = null;
        return result;
    }

    private void ensureCapacity() {
        if (elements.length == size)
            elements = Arrays.copyOf(elements, 2 * size);
    }
}

```

실체화 불가 타입으로는 배열을 만들 수 없어서 컴파일 에러가 발생합니다
이 문제의 해결 방법으로는 적절한 해결책이 두가지 존재합니다.

### 첫번째 방법, Object 배열 사용

```java
@SuppressWarnings("unchecked")
public Stack1() {
   elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
}
```

* Object 배열을 생성합니다
* 그 후 제네릭 배열로 형변환합니다
* elements는 private하고 오직 push 메서드를 통해서만 원소가 추가 됩니다
* 추가되는 원소는 항상 E이기 때문에 비검사 형변환한 안전합니다
* 마지막으로 @SuppressWarnings 애너테이션으로 경고 메시지를 숨겨줍니다

### 두번째 방법, elements 필드의 타입을 E[]에서 Object[]로 변경

```java
private Object[] elements;

public E pop() {
   if (size == 0)
		throw new EmptyStackException();
   // push에서 E 타입만 허용하므로 이 형변환은 안전하다.
   @SuppressWarnings("unchecked") E result = (E) elements[--size];
   elements[size] = null;
   return result;
}
```

* elements필드의 타입을 E[]에서 Object[]로 변경해 컴파일 오류를 해결합니다
* 위와 마찬가지로 비검사 형변환이 안전한지 스스로 점검합니다
* @SuppressWarnings 애너테이션으로 경고 메시지를 숨겨줍니다

### 첫번째, 두번째 방법 비교
* 첫번째 방법이  가독성이 더 좋습니다
* 배열의 타입이 E[]로 선언되어 있고, 배열 생성시에만 형변환을 해주면 됩니다
* 두번째 방법의 경우에는 원소를 pop(읽을때)마다 형변환을 해주어야 합니다


## 정리
* 클라이언트에서 직접 형변환해야 하는 타입보다 제네릭 타입이 type safe하고 쓰기 편합니다
* 새로운 타입을 설계할 때는 형변환 없이도 사용할수 있도록 합니다
* 기존 타입중 제네릭 이어야 하는게 있다면 제네릭으로 변경하도록 합니다
