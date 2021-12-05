06. 불필요한 객체 생성을 피하라

> 똑같은 기능의 객체를 매번 생성하기 보다는 객체 하나를 재사용하는 편이 나을때가 많습니다

1. 생성자 대신 정적 팩터리 메서드를 제공하는 불변 클래스에서는 정적 팩터리 메서드를 사용해 불필요한 객체 생성을 피할수 있습니다

생성자는 호출될 때마다 새로운 객체를 만들지만, 팩터리 메서드는 전혀 그렇지 않습니다
```java
Boolean(String s) 
Boolean.valueOf(String s)
```
모두 s의 문자열이 “true”인지 확인해주는 역할을 합니다. API를 간단히 살펴보겠습니다

*Boolean(String s)*
```java
@Deprecated(since="9")
public Boolean(String s) {
    this(parseBoolean(s));
}

new Boolean("hello")
```
자바9 이후부터는 deprecated이다. 이 연산은 new로 객체를 생성해주어야 합니다

*Boolean valueOf(String s)*
```java
public static Boolean valueOf(String s) {
    return parseBoolean(s) ? TRUE : FALSE;
}
```

둘다 내부에서 parseBoolean를 호출하지만 객체를 생성하고 안하고의 차이가 명확히 있습니다. 다 굳이 객체를 생성할 필요가 없기에 Boolean.valueOf()를 호출하는 것이 좋습니다.

2. 객체가 반복해서 사용될 경우, 캐싱하여 재사용합니다
   흔히들 정규식 표현을 구현할때 Pattern을 상수값으로 지정해두라는 얘기를 많이 들어봤을 것입니다

```java
public class RomanNumerals {

    // 코드 6-1 캐싱을 하여 성능을 훨씬 더 끌어올릴 수 있다!
    static boolean isRomanNumeralSlow(String s) {
        return s.matches("^(?=.)M*(C[MD]|D?C{0,3})"
            + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
    }

    // 코드 6-2 값비싼 객체를 재사용해 성능을 개선한다.
    private static final Pattern ROMAN = Pattern.compile(
        "^(?=.)M*(C[MD]|D?C{0,3})"
            + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");

    static boolean isRomanNumeralFast(String s) {
        return ROMAN.matcher(s).matches();
    }
}
```


먼저 String의 matches 함수를 살펴보겠습니다
```java
public boolean matches(String regex) {
    return Pattern.matches(regex, this);
}

public static boolean matches(String regex, CharSequence input) {
    Pattern p = Pattern.compile(regex);
    Matcher m = p.matcher(input);
    return m.matches();
}

public static Pattern compile(String regex) {
    return new Pattern(regex, 0);
}
```

즉, String의 matches를 이용하면 매번 Pattern 객체를 생성하게 되는데, 과연 그럴 필요가 있을까요?? Pattern은 또한 유한상태머신을 만들기 때문에 인스턴스 생성 비용이 높습니다

3. 불필요한 오토박싱을 피하라
```java
public class Sum {

    private static long sum() {
        Long sum = 0L;
        for (long i = 0; i <= Integer.MAX_VALUE; i++)
            sum += i;
        return sum;
    }
}
```

Long -> long으로 변환해라. 꼭 필요한 객체가 아니라면 원시타입을 이용하라
