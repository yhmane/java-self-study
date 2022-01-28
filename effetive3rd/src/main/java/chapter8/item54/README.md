# 54. Null이 아닌 빈 컬렉션이나 배열을 반환하라
## 컬렉션이 비었을 경우 null 반환
```java
public List<Cheese> getCheeses() {
    return cheeseInStock.isEmpty() ? null : new ArrayList<>(cheeseInStock);
}

// null 처리
List<Cheese> cheeses = shop.getCheeses();
if (cheeses != null && chess.contains(Chess.STILTON) {
    ...
}
```

* 재고가 없다고 해서 특별히 취급할 이유는 없습니다
* null을 반환한다면 null을 반환한다면 클라이언트는 null 상황을 처리하는 코드를 추가로 작성해야 합니다

## 빈 컬렉션을 반환
```java
public List<Cheese> getCheeses() {
    return new ArrayList<>(cheeseInStock);
}
```

* Empty Collection을 반환하는 것이 성능을 떨어뜨릴 수 있다고 얘기하지만 차이는 거의 됩니다
* 빈 컬렉션을 매번 새로 할당하지 않아도 됩니다

## 빈 컬렉션 반환 최적화
```java
public List<Cheese> getCheeses() {
    return cheeseInStock.isEmpty() ? Collections.emptyList() : new ArrayList<>(cheeseInStock);
  }
```

## 빈 배열 반환 최적화
```java
private static final Cheese[0] EMPTY_CHEESE_ARRAY = new Cheese[0];
public Cheese[] getCheeses() {
    return EMPTY_CHEESE_ARRAY;
}
```

## 정리
* null이 아닌 빈 배열이나 컬렉션을 반환 하도록 합니다
* null을 반환하는 API는 사용하기 어렵고 오류 처리 코드도 늘어납니다
