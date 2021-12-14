# 11. Equals를 재정의하려거든 hashcode도 재정의하라
* equals 재정의한 클래스스 모두에서 hashCode도 재정의해야 합니다.
* 그렇지 않으면 hashCode 일반 규약을 어기게 됩니다.
* HashMap, HashSet 같은 컬렉션의 원소로로 사용할때 문제를 일으키게 됩니다.

## hashCode 기본 규약
* equals 비교에 사용되는 정보가 변경되지 않았다면, 애플리케이션이 실행되는 동안 그 hashCode 메서드는 몇번을 호출해도 항상 일관된 값을 반환해야 합니다.
* 논리적으로 각은 객체는 같은 해시코드를 반환해야 합니다.
* equals가 두 객체를 다르다고 판단했더라도, 두 객체의 hashCode가 서로 다른 값을 반환할 필요는 없습니다. 단, 다른 객체에 대해서는 다른 값을 반환해야 해시테이블의 성능이 좋아집니다.

## hashCode를 재정의하지 않은 경우
```java
Map<PhoneNumber, String> map = new HashMap<>();
map.put(new PhoneNumber(707, 867, 5309), “제니”);
System.out.println(m.get(new PhoneNumber(707, 867, 5309));
```
* ‘제니’의 결과를 기대하지만 get의 결과는 null입니다.
* 최악의 hashCode 재정의

```java
@Override
public int hashCode() {
    return 42;    
}
```
* 모든 객체의 hashCode값이 42로 반환됩니다. 성능적으로도 떨어지고 논리적으로도 부적합하기에 규약을 따라야합니다.


## hashCode 재정의
```java
@Override
public int hashCode() {
    int result = Short.hashCode(areaCode);
    result = 31 * result + Short.hashCode(prefix);
    result = 31 * result + Short.hashCode(lineNum);
    return result;
}
```
* 클래스가 불변이고 해시코드를 계산하는 비용이 크다면, 캐싱 방식을 고려해보는 것도 좋습니다.

```java
private int hashCode; //0으로 초기화
@Override
public int hashCode() {
    int result = hashCode;
    if (result == 0) {
      result = Short.hashCode(areaCode);
      result = 31 * result + Short.hashCode(prefix);
      result = 31 * result + Short.hashCode(lineNum);
      hashCode = result;
    }
    return result;
}
```

### hashcode를 재정의 할 때 주의
* 불변 객체에 대해서는 hashcode 생성비용이 많이 든다면 캐싱을 고려해 봅니다.
* 성능을 높인다고 핵심 field를 계산에서 제외하면 안됩니다.
* hashcode 생성규칙을 API 사용자에게 open하지 않는게 좋습니다.
* 그래야 클라이언트가 hashcode값에 의지한 코드를 구현하지 않습니다.

## 결론
* equals를 재정의할 때는 반드시 hashCode도 재정의해야 합니다.
* 서로 다른 인스턴스라면 해시코드도 서로 다르게 구현해야 합니다.
* AutoValue를 이용한다면 간단히 해시코드를 재정의 할 수 있습니다.

