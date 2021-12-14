# 10. equals는 일반 규약을 지켜 재정의하라
* equals 메서드는 여러 조건들을 충족해야 하기에 재정의하기 쉽지 않습니다.
* 문제를 회피하는 가장 쉬운 방법은 아예 재정의 하지 않는 것입니다.
* 아래의 사항에 해당하는 것이 있다면 재정의 하지 않는 것이 최선

---
## equals를 재정의 하지 않아도 되는 경우
### 각 인스턴스가 본질적으로 고유할 경우
* 값을 표현하는게 아닌 동작하는 개체를 표현할 경우. 대표적으로 Thread
### 인스턴스의 ‘논리적 동치성(logical equality)’을 검사할 일이 없을 경우
* java.utils.regex.Pattern은 equals를 재정의해서 두 Pattern의 인스턴스가 같은 정규표현식을 나타내는지 검사합니다.
### 상위 클래스에서 재정의한 equals가 하위 클래스에서도 딱 들어맞을 경우
* AbstractSet -> Set, AbstractList -> List, AbstractMap -> Map 들은 상위 Abstract 클래스로부터 equals를 구현 받아 사용합니다.
### 클래스가 private이거나, package-private여서 equals를 호출할 일이 없는 경우
* equals가 실수로라도 호출되는 걸 막고 싶다면 다음처럼 구현을 하면 됩니다.@Override public boolean equals(Object o) {
*   throw new AssertionError();
* }

### 싱글턴을 보장하는 클래스’인스턴스 통제, Enum’인 경우

# equals를 재정의 할 경우 지켜야 할 규약
### 반사성 (reflexivity)
* null이 아닌 모든 참조 값 x에 대해, ‘x.equals(x) = true’ 이어야 합니다. (자기 자신에 대해서 true)
### 대칭성 (symmetry)
* null이 아닌 모든 참조 값 x, y에 대해 ‘x.equals(y) = true’면 ‘y.equals(x) = true’를 만족해야 합니다.
```java
public final class CaseInsensitiveString {
	private final String s;

	public CaseInsensitiveString(String s) {
		this.s = Objects.requireNonNull(s);
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof CaseInsensitiveString) {
			return s.equalsIgnoreCase(((CaseInsensitiveString) o).s);
		}

		if (o instanceof String) {
			return s.equalsIgnoreCase((String) o);
		}
		return false;
	}
}

CaseInsensitiveString cis = new CaseInsensitiveString(“hello?”);
String hello = “hello?”;
System.out.println(cis.equals(hello)); //true
System.out.println(hello.equals(cis)); //false
```

cis.equals(hello)는 true를 반환하지만, hello.equals(cis)는 false를 반환한다. String의 equals는 일반 String을 알고 있지만 CaseInsenitiveString의 존재를 알수 없다.
### 추이성
* null이 아닌 모든 참조 값 x, y, z에 대해 ‘x.equals(y) = true’면 ‘y.equals(z) = true’도 만족하면 ‘x.equals(z) = true’도 만족해야 합니다.
```java
class Point {

	private final int x;
	private final int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Point)) return false;
			Point p = (Point) o;
			return this.x == p.x && this.y == p.y;
	}
}

class ColorPoint extends Point {

	private final Color color;

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Point)) {
			return false;
		}

		if (!(o instanceof ColorPoint)) {
			return o.equals(this);
		}
		return super.equals(o) && this.color == ((ColorPoint) o).color;
	}
}

ColorPoint p1 = new ColorPoint(1, 2, Color.RED);
Point p2 = new Point(1, 2);
ColorPoint p3 = new ColorPoint(1, 2, Color.BLUE);

p1 -> p2, p2 -> p3를 만족하지만 p1 -> p3는 색상도 비교해야 하기에 만족하지 않습니다. 따라서 추이성 규약을 위반합니다.
```

### 일관성
* null이 아닌 모든 참조 값 x, y에 대해 ‘x.equals(y)’는 true/false 등 항상 일관된 값을 반환해야 합니다.

### null-아님
* null이 아닌 모든 참조 값 x에 대해 ‘x.equals(null) = false’입니다.

---
## equals 구현 절차
* == 연산자를 사용해 입력이 자기 자신의 참조인지 확인합니다.
    * 성능 최적화용으로 equals가 복잡할 때 같은 참조를 가진 객체에 대한 비교를 안하기 위함입니다.
* instanceof 연산자로 입력이 올바린 타입인지 확인합니다.
    * 올바르지 않다면 false를 반환합니다.
    * equals중에서는 같은 interface를 구현한 클래스끼리 비교하는 경우도 있습니다.
* 입력을 올바른 타입으로 형변환합니다.
    * 앞서 instanceof 연산을 수행했기 때문에 100% 성공합니다.
* 입력 객체와 자기 자신의 대응되는 ‘핵삼’ 필드들이 모두 일치하는지 하나씩 검사합니다.
    * 모두 일치하면 true, 하나라도 다르면 false를 반환합니다.
    * interface 비교시 필드정보를 가져오는 메서드가 interface에 정의되어있어야하고, 구현체에서 메서드를 재정의 해야합니다.
* float, double을 제외한 기본타입은 ==을 통해 비교하고 참조(reference) 타입은 equals를 통해 비교합니다.
    * float, double은 Float.compare(float, float)와 Double.compare(double, double)로 비교합니다.
    * 배열의 모든 원소가 핵심 필드라면 Arrays.equals를 사용합니다.
* null도 정상값으로 취급하는 참조타입 필드도 있습니다.
    * Objects.equals(obj, obj)를 이용해 NullPointerException 발생을 예방합니다.
* 최상의 성능을 내기 위해 다음과 같은 방법이 있습니다.
    * 다를 확률이 높은 필드부터 비교합니다.
    * 비교하는 비용이 작은 것을 먼저 수행합니다.

---
## 결론
* 꼭 필요한 경우가 아니면 equals를 재정희 하지 말자.
* 많은 경우에 Object의 equals가 정확한 비교를 수행하여 줍니다.
* 재정의할때는 위 다섯 가지 규약을 확실히 지켜가며 정의하도록 합니다.
