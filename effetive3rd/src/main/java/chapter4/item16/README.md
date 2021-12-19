# 16. public 클래스에서는 public 필드가 아닌 접근자 메서드를 사용하라
### 클래스 내, public 필드 사용
```java
class Point {
    public double x;
    public double y;
}

public class Execution {
  public static void main(String[] args) {
    Point p = new Point();
    p.x = 123;
    System.out.println(p.x);
  }
}
```
* 위와 같은 클래스 Point는 데이터 필드에 직접 접근할 수 있으니 캡슐화의 이점을 제공하지 못합니다.
* API를 수정하지 않고는 내부 표현을 바꿀 수 없습니다.
* 불변식을 보장할 수 없습니다.

### 클래스 내, public 필드 제거
접근자(getter)와 변경자(setter)를 통해 데이터를 캡슐화
```java
class Point2 {
  private double x;
  private double y;

  public Point2(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  public void setX(double x) {
    this.x = x;
  }
  public void setY(double y) {
    this.y = y;
  }
}

public class Execution {
  public static void main(String[] args) {
    Point2 p2 = new Point2(12, 34);
    p2.setX(123);
    System.out.println(p2.getX());
  }
}
```
* private field를 사용하여 직접적인 접근을 막습니다.
* 접근자(getter)와 수정자(setter)를 통해 내부 표현 방식의 유연성을 얻습니다.
* package-private(default class) 혹은 private 중첩 클래스라면 데이터 필드를 노출한다 해도 문제가 없습니다.

# 결론
* public 클래스는 절대 가변 필드를 직접 노출해서는 안됩니다.
* 불변 필드라면 노출해도 덜 위험하지만 완전히 안심할 수는 없습니다. (item 15의 배열의 경우 안심할 수 없음)
* package-private 클래스나 중첩 클래스에서는 종종 필드를 노출하는 편이 나을때도 있습니다.

#책/이펙티브자바/4장/클래스와인터페이스/item16