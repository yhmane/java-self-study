# 17.  변경 가능성을 최소화하라
## 불변 클래스(Immutable Class)란 ?
* 인스턴스의 내부 값을 수정할 수 없는 클래스를 말합니다.
* 인스턴스의 저장된 정보가 객체가 파괴되기 전까지 바뀌지 않습니다
* 대표적으로 String, Boolean, Integer, Float, Long 등등이 있습니다
* Immutable Class들은 heap영역에서 변경불가능 한 것이지 재할당을 못하는 것은 아닙니다

### 불변 클래스 사용 이유는 무엇일까요?
* 설계, 구현, 사용이 쉽습니다
* thread-safe하고 에러를 만들 가능성이 적습니다


## 불변 클래스를 만드는 5가지 방법
* 객체의 상태를 변경하는 메서드(변경자)를 제공하지 않습니다
* 클래스의 확장/상속을 막습니다
* 모든 상태를 final로 선언합니다
* 모든 상태를 private으로 선언합니다
* 만약, 가변 필드가 존재한다면 외부에서 접근을 못하게 막습니다

> 불변 복소수 클래스 Complex
```java
public final class Complex {

    private final double re;
    private final double im;

    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public double realPart() {
        return re;
    }

    public double imaginaryPart() {
        return im;
    }

    public Complex plus(Complex c) {
        return new Complex(re + c.re,im + c.im);
    }

    public Complex minus(Complex c) {
        return new Complex(re - c.re,im - c.im);
    }

    public Complex times(Complex c) {
        return new Complex(re * c.re - im * c.im,re * c.im + im + c.re);
    }

    public Complex dividedBy(Complex c) {
        double tmp = c.re * c.re + c.im * c.im;
        return new Complex((re * c.re + im * c.im) / tmp,
                (im * c.re - re + c.im) / tmp);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Complex)) {
            return false;
        }

        Complex c = (Complex) o;
        return Double.compare(c.re, re) == 0
                && Double.compare(c.im, im) == 0;
    }

    @Override
    public int hashCode() {
        return 31 * Double.hashCode(re) + Double.hashCode(im);
    }

    @Override
    public String toString() {
        return "(" + re + " + " + im + "i)";
    }

}
```

* 상태값을 반환하는 접근자와 사칙연산 메서드들을 정의하였습니다
* 사칙연산 함수들은 상태값을 변경하지 않고 새로운 Complex를 반환합니다
* 이처럼 피연산자에 함수를 적용해 그 결과를 반환하지만, 피연산자 자체는 그대로인 프로그래밍 패턴을 함수형 프로그래밍이라 합니다

### 불변클래스 장점
* 불변 객체는 근본적으로 스레드 안전하여 따로 동기화 할 필요가 없습니다
* 불변 객체는 안심하고 공유 할 수 있습니다
* 불변 객체는 방어적 복사본이 필요 없습니다
* 불변 객체는 자유롭게 공유할 수 있음은 물론, 불변 객체끼리는 내부 데이터를 공유할 수 있습니다
* 불변 객체를 key로 하면 이점이 많습니다
    * Map의 key
    * Set의 원소
* 불변 객체는 그 자체로 실패 원자성을 제공합니다
