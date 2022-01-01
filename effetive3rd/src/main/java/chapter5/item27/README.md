# 27. 비검사 경고를 제거하라

> 제네릭을 사용하기 시작하면 수많은 컴파일러 경고들을 마주치게 됩니다<br/>
> 이러한 경고들을 가능한 많이 제거하는 것이 습니다<br/>
> 경고들을 모두 제거한다면, 그 코드는 타입 안정성이 보장되기 때문입니다

대부분의 비검사 경고는 쉽게 제거할 수 있습니다
```java
Set<Car> cars = new HashSet();
```

```text
Venery.java:4: warning: [unchecked] unchecked conversion
                Set<Car> cars = new HashSet();
                                ^
  required: Set<Car>
  found:    HashSet
```

컴파일러가 알려준 대로 수정하면 경고가 사라집니다.  자바7 이후 제공하는 다이아몬드 연산자<>로 해결이 가능합니다
```java
Set<Car> cars = new HashSet<>();
```


---
다만, 제거하기 훨씬 어려운 경고들도 있습니다. 곧바로 해결되지 않는 경고가 나타나더라도 할 수 있는한 모든 비검사 경고를 제거하도록 합니다

### 경고를 제거할 수 없지만, 타입이 안전하다고 확신할 수 있으면, @SuppressWarnings(“unchecked”)를 달아 경고를 숨기도록 합니다

### @SuppressWarnings
* 지역변수 ~ 클래스 전체까지 모두 선언 할 수 있습니다
    * 가능한 가장 좁은 범위에 적용하도록 합니다
    * 또한, 클래스 전체에 적용하지 않도록 합니다
* 한줄이 넘는 메서드나 생성자에 달린 @SuppressWarnings은 지역변수 선언 쪽으로 옮기도록 합니다
    * 이를 위해 지역변수를 새로 선언하는 수고를 해야할 수 있지만, 그만한 값어치가 있습니다
* 또한 @ SuppressWarnings 사용시 안전한 이유를 항상 주석으로 남겨두도록 합니다
```java
public <T> T[] toArray(T[] a) {
	if (a.length < size) {
		// 생성한 배열과 매개변수로 받은 배열의 타입이 모두 T[]로 같으므로 올바른 형변환입니다
		@SuppressWarnings("unchecked")
		T[] result = (T[]) Arrays.copyOf(elements, size, a.getClass());
		return result;
	}
	System.arraycopy(elements, 0, a, 0, size);
	if (a.length > size)
		a[size] = null;
	return a;
}
```
