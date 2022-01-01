# 28. 배열보다는 리스트를 사용하라
배열과 제네릭 타입에는 중요한 차이가 두가지 있습니다

### 1. 배열은 공변, 제네릭은 불공변입니다

```java
// 배열, ArrayStoreException, RunTime 오류
Object[] objectArray = new Long[1];
objectArray[0] = “타입이 달라 넣을 수 없다”; 

// 리스트, 컴파일 되지 않는다
// List<Object> objectList = new ArrayList<Long>(); 
```

배열은 sub가 super의 하위타입이라면 sub[]는 배열 super[]의 하위타윕이 됩니다. 따라서, 위에 Long에 String을 입력하여도 컴파일 오류가 나지 않았습니다.

반면, 리스트 List<Type1>은 List<Type2>의 하위타입도 상위 타입도 아닙니다. 따라서, 위의 코드에서 컴파일 오류가 났습니다

### 2. 배열은 실체화가 되고, 리스트는 그렇지 않습니다
배열은 런타임에도 자신이 담기로 한 원소의 타입을 인지하고 확인합니다.
반면 제네릭은 타입정보가 런타임에는 소거됩니다. 이 두 차이로 인해 배열과 제네릭은 어울러질 수 없습니다

```java
List<String>[] stringLists = new List<String>[1];
```

## 정리
* 타입 세이프하고, 런타임이 아닌 컴파일 단계에서 에러를 잡아줍니다. 다만, 성능으로는 배열이 유리하지만 웬만하면 리스트를 사용하도록 합니다