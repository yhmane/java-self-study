# 53. 가변인수는 신중히 사용하라
가변인수 메서드는 명시한 타입의 인수를 0개 이상 받을 수 있습니다. 가변인수 메서드를 호출하면, 인수의 개수와 길이가 같은 배열을 만들고 인수들을 배열에 저장하여 가변인수 메서드에 건네줍니다.

### 가변인수를 통해 인수들의 합을 반환하는 메서드
```java
static int sum(int… args) {
	int sum = 0;
	for (int arg : args) {
		sum += arg;
	}
	return sum;
}
```

### 인수가 1개 이상이어야 하는 가변인수 메서드 (잘못 구현한 예)
```java
static int sum(int… args) {
	if (args.length == 0) {
		throw new IllegalArgumentExcetpion("인수가 1개 이상 필요합니다!");
	int min = args[0];
	for (int i = 1; i < args.length; i++) {
		if (args[i] < min) {
			min = args[i];
		}
	}
	
	return min;
}
```

이 방식에는 문제가 몇개 있습니다
* 인수가 0개일 경우, 컴파일타임이 아닌 런타입에 실패합니다
* 가변인수에 대한 유효성 검사를 명시적으로 해야 합니다
* min의 초깃값을 Integer.MAX_VALUE로 설정하지 않고는 for-each 문도 사용할 수 없습니다

### 대안으로 평범한 매개변수와 가변인수를 같이 사용합니다
```java
static int min(int firstArg, int... remainingArgs) {
	int min = firstArg;
	for (int arg : remainingArgs) {
		if (arg < min) {
			min = arg;
		}
	}
	return min;
}
```
* 코드가 깔금해지고 명시적인 유효성 검사가 사라졌습니다


### 다중정의를 이용하는 방법
```java
public void foo() { }
public void foo(int a1) { }
public void foo(int a1, int a2) { }
public void foo(int a1, int a2, int a3) { }
public void foo(int a1, int a2, int a3, int… rest) { }
```

* 성능이 민감한 상황에서는 가변인수 메서드가 걸림돌이 될 수 있습니다
* 가변인수 메서드는 호출할 때마다 배열을 새로 할당하고 초기화하기 때문입니다
* 위의 패턴은 가변인수 생성 비용을 감당할 수는 없지만 가변인수의 유연성이 필요할 때 사용할 수 있는 패턴입니다.
* 매개변수가 0개부터 ~ 3개인것 까지 생성합니다. 대부분의 95프로 메서드는 위 4가지에서 작동할 것입니다
* 마지막으로 나머지 5프로를 위해 가변인수를 사용하는 메서드를 추가해줍니다
