# 14. Comparable을 구현할지 고려하라
Comparable의 compareTo()는 단순 동치성 비교에 더해 순서까지 비교할 수 있으며 제네릭 합니다.
```java
public interface Comparable<T> {
	int compareTo(T t);
}
```

# compareTo() 일반규약
* 이 객체와 주어진 객체의 순서를 비교합니다.
* 이 객체가 주어진 객체보다 작으면 음수를, 같으면 0을, 크면 양수를 반환합니다.
* 이 객체와 비교할 수 없는 타입의 객체가 주어지면 ClassCastException을 던집니다.
* x.compareTo(y) = y.compareTo(x) * -1 입니다. 또한 예외가 발생한다면 양쪽 표현식 모두 발생합니다.
* 추이성을 보장합니다.
    * x.compareTo(y) > 0이고 y.compareTo(z) > 0 이면 x.compareTo(z) > 0도 성립합니다.
* (x.compareTo(y) == 0) == (x.equals(y)) 이어야 합니다.
    * 이 권고를 지키지 않는 모든 클래스는 아래의 사실을 명시해야 합니다
    * “이 클래스의 순서는 equals 메서드와 일관되지 않다”
    * Collection, Set, Map 인터페이스들은 동치성을 비교할 때 equals 대신 compareTo를 사용하기 때문에 마지막 규약을 꼭 지키는 것이 좋습니다.


# 사용예제
* 기본 타입 필드가 여럿일 때의 비교자

## Comparable 이용
### Comparable anti-pattern
```java
@Override
public int compareTo(StudentComparable o) {
    if (this.age > o.age) {
		return 1;
	} else if (this.age == o.age) {
		return 0;
	} else {
		return -1;
	}
}
```

### Comparable, 박싱된 기본 타입 클래스가 제공하는 정적 compare 메서드
```java
public int compareTo(StudentComparable o) {
    return Integer.compare(this.age, o.age);
}
```

## Comparator 이용
### Comparator anti-pattern
```java
Collections.sort(list, (Comparator<Student>) (o1, o2) -> {
	if (o1.getAge() > o2.getAge()) {
		return 1;
	} else if (o1.getAge() == o2.getAge()) {
		return 0;
	} else {
		return -1;
	}
});
```

### Comparator, 인터페이스가 제공하는 비교자 생성 메서드
```java
Collections.sort(list, Comparator.comparingInt(Student::getAge));
```


# 결론
* 순서를 고려해야 하는 값 클래스를 작성할 경우 꼭 Comparable 인터페이스를 구현합니다.
* 그 인스턴스들은 쉽게 정렬하고, 검색하고, 비교 기능을 제공하는 컬렉션과 어우러져야 합니다.
* compareTo()는 필드의 값을 비교할 때 ‘<‘와 ‘>’ 연산자는 쓰지 말도록 합니다. [overflow 문제로 인해](https://monicagranbois.com/blog/java/comparing-integers-using-integercompare-vs-subtraction/)
* 대신 박싱된 기본 타입 클래스가 제공하는 정적 compare 메서드나 Comparator 인터페이스가 제공하는 비교자 생성 메서드를 사용합니다.