# 49. 매개변수가 유효한지 검사하라
메서드와 생성자 대부분은 입력 매개변수의 값이 특정 조건을 만족하길 바랍니다
예컨데 인덱스값은 음수여서는 안되고, 객체 참조는 null이 아니어야 한다 등

이러한 제약은 문서화해야하며 메서드 몸체가 시작되기 전에 검사해야 합니다

### 매개변수 검사를 제대로 하지 못하면 발생하는 문제
* 매서스기 수행 중간에 모호한 예외를 던지며 실패할 수 있습니다
* 메서드가 잘 수행되지만 잘못된 결과를 반환할 수 있습니다
* 메서드가 문제없이 수행됐지만, 미래의 알수 없는 시점에 메서드와 관련 없는 오류를 낼 수 있습니다

### public과 protected 메서드는 예외를 문서화하자
```java
/**
 * (현재 값 mod m) 값을 반환한다. 이 메서드는
 * 항상 을이 아닌 BigInteger를 반환한다는 점에서 remainder 메서드와 다르다
 *
 * @param m 계수 (양수여야 한다.)
 * @return 현재 값 mod m
 @ @throws ArithmeticException
public BigInteger mod(BigInteger m) {
	if (m.signum() <= 0) 
		throw new ArithmeticException("계수(m)는 양수여야 합니다." + m);
	// 계산수행 ...
}
```
1. public 이나 protected 메서드는 매개변수 값이 잘못됐을 때 던지는 예외를 문서화 해야 합니다 (@throws 자바독 태그 사용, item74 에서 상세히 다룰 예정)
2. 제약을 어겼을 때 발생하는 예외도 함께 기술합니다

### Objects.requireNonNull
자바 7에 추가된 java.util.Objects.requireNonNull 메서드를 통해 더 이상 null 검사를 수동으로 하지 않아도 됩니다
```java
this.strategy = Objects.requireNonNull(strategy, "전략");
```


### public이 아닌 메서드의 경우 assert를 사용해 유효성 검증
```java
private static void sort(long a[]. int offset, int length) {
	assert a != null;
	assert offset >= 0 && offset <= a.length;
	assert length >= 0 && length <= a.length - offset;
	// ... 계산 수행
}
```
* 공개 되지않은 메서드(private)은 개발자가 메서드 호출 상황 통제 가능합니다
* 단언문 assert 를 이용해 매개변수 유효성을 검사할 수 있습니다
* assert문은 자신이 단언한 조건을 무조건 참이라고 선언합니다
* 실패하면 AssertionError를 던진고 런타임과 성능에 영향이 없습니다

### 예외 케이스
메서드 몸체 실행 전에 꼭 유효성을 검사를 해야하는 것은 아닙니다
1. 유효성 검사 비용이 지나치게 높거나 실용적이지 않을때는 제외합니다
2. 계산 과정에서 암묵적으로 검사가 수행될때도 제외합니다


#책/이펙티브자바/8장/메서드/item49