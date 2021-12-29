# 22. 인터페이스는 타입을 재정의하는 용도로만 사용하라

인터페이스는 타입을 정의하는 용도로만 사용해야 합니다.
상수 공개용 수단으로 사용해서는 안됩니다

## 안티패턴 - 상수 인터페이스
메서드 없이, 상수를 뜻하는 static final 필드로만 가득찬 인터페이스
```java
public interface PhysicalConstants {
    // 아보가드로 수
    static final double AVOGADROS_NUMBER = 6.022_140_857e23;
    // 볼츠만 상수
    static final double BOLTZMANN_CONSTANT = 1.380_648_52e-23;
    // 전자 질량
    static final double ELECTRON_MASS = 9.109_383_56e-31;
}
```

*  클래스 내부에서 사용하는 상수는 외부 인터페이스가 아닌 내부 구현입니다
    * 내부 구현을 클래스의 API로 노출하는 행위가 됩니다
    * 또한, 사용자에게 혼란과 오해의 소지를 줄 뿐입니다

## 대안
클래스의 상수로 선언하여 사용합니다
```java
public class PhysicalConstants {
    // 인스턴스화 방지
    private PhysicalConstants(){}
    
    static final double AVOGADROS_NUMBER = 6.022_140_857e23;
    static final double BOLTZMANN_CONSTANT = 1.380_648_52e-23;
    static final double ELECTRON_MASS = 9.109_383_56e-31;
}
```

또는 enum의 값으로 사용합니다
```java
public enum PhysicalConstants {
    AVOGADROS_NUMBER(6.002_140_857e23),
    BOLTZMANN_CONSTANT(1.380_648_52e-23),
    ELECTRON_MASS(9.109_383_56e-31);

    private double value;

    PhysicalConstants(double value) {
        this.value = value;
    }

    public double getValue(){
        return value;
    }
}
```
