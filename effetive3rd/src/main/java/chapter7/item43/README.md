# 43. 람다보다는 메서드 참조를 사용하라

```java
// 람다
map.merge(key, 1, (count, incr) -> count + incr);

// 메서드 참조
map.merge(key, 1, Integer::sum);
```


| 메서드 참조 유형 | 예          | 같은 기능을 하는 람다       |
| ----------- | ---------- | ------------------ |
| 정적        | Integer::parseInt | str -> Integer.parseInt(str)|
| 한정적 (인스턴스) | Instant.now()::isAfter | Istant then = Instant.now();t -> t.isAfter(t)|
| 비한정적 (인스턴스) | String::toLowerCase | str -> str.toLowerCase()|
| 클래스 생성자 | TreeMap<K, V>::new | () -> new TreeMap<K, V>()|
| 배열 생성자 | int[]::new | len -> new int[len]|

메서드  참조(method reference)를 사용하면 람다보다도 더 간결하게 코드를 작성할 수 있습니다

```java
코드가 GoshThisClassNameIsHumongous에 있다고 가정해
  
// 메서드 참조
service.execute(GoshThisClassNameIsHumongous::action);

// 람다
service.execute(() -> action());
```
위처럼 람다가 더 간결할 경우를 제외하고는 메서드 참조를 사용하는것이 가독성이 좋습니다 


