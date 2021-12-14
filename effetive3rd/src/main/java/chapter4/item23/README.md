# 23.  태그 달린 클래스보다는 클래스 계층구조를 활용하라
## 태그 달린 클래스
태그 클래스란, 두가지 이상의 의미를 표현할 때 그 중 현재 표현하는 의미를 태그값으로 알려주는 클래스 입니다. <br/>
책에 나온 예제로 살펴보도록 하겠습니다.

```java
class Figure {

    enum Shape {
        RECTANGLE, CIRCLE
    }

    // 태그 필드
    final Shape shape;

    // RECTANGLE 용 필드
    double length;
    double width;

    // CIRCLE 용 필드
    double radius;

    // RECTANGLE
    Figure(double length, double width) {
        shape = Shape.RECTANGLE;
        this.length = length;
        this.width = width;
    }

    // CIRCLE
    Figure(double radius) {
        shape = Shape.CIRCLE;
        this.radius = radius;
    }

    double area() {
        switch(shape) {
            case RECTANGLE:
                return length * width;
            case CIRCLE:
                return Math.PI * (radius * radius);
            default:
                throw new AssertionError(shape);
        }
    }
}
```

### 태그 클래스의 단점
1. Enum switch, field 등 여러 구현이 혼합 되어 있습니다
2. 이로 인해 가독성이 떨어집니다
3. 사용하지 않는 필드로 인해 메모리도 추가적으로 사용합니다
4. 또 다른 타입을 추가하게 되면 switch 문의 수정이 일어납니다
5. 인스턴스 타입만으로 객체의 의미를 알 수 없습니다

태그가 달린 클래스는 코드가 길어지고 확장성에 취약합니다.
또한, 태그가 추가 될 때마다 사용하지 않는 필드들이 추가 될 수도 있습니다.


---
## 계층 클래스
### 계층 구조의 클래스를 만드는 방법
1. 계층구조의 최상위(root)가 될 추상클래스를 정의합니다
2. 태그값에 따라 달라지는 동작(메서드)들을 최상위 클래스의 추상 메서드로 선언합니다
3. 태그값에 상관없이 동작이 일정한 메서드는 최상위 클래스에 일반 메서드로 정의합니다.
4. 모든 하위 클래스에 공통으로 사용하는 상태값(필드)들은 루크 클래스에 정의합니다.

### 계층구조로 변환
```java
abstract class Figure {
    abstract double area();
}

class Rectangle extends Figure {

    final double length;
    final double width;

    Rectangle(double length, double width) {
        this.length = length;
        this.width  = width;
    }

    @Override
    double area() {
        return length * width;
    }
}


class Circle extends Figure {

    final double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    @Override
    double area() {
        return Math.PI * (radius * radius);
    }
}
```

### 클래스 계층구조의 장점
1. 독립된 의미를 가지는 상태값(필드)들이 제거 되어 각 구현 클래스는 간결해집니다
2. 살아남은 field는 모두 final이므로 정의할 수 있습니다
3. 실수로 빼먹은 case 문으로 인해 runTime 에러를 방지할 수 있습니다
4. 최상위 클래스를 수정하지 않고 타입을 확장 할 수 있습니다
5. 타입사이의 자연스러운 계층 관계를 반영할 수 있습니다
