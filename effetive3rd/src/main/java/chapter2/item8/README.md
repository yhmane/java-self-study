# Item8 finalizer와 cleaner 사용을 피하라

자바에는 두 가지 객체 소멸자를 제공합니다

> finalizer는 나름의 쓰임새가 있지만, 기본적으로 쓰지 말아야합니다<br/>
> 그래서 Java9에서는 finalizer api를 deprecated하고 cleaner 사용을 권하지만<br/> 
> 여전히 예측할 수 없고, 느리고 일반적으로 불필요합니다


---
## finalizer와 cleaner를 사용하면 안되는 이유
**1. finalizer와 cleaner는 제때 실행되어야 하는 작업은 절대 할 수 없습니다**
- finalizer와 cleaner를 언제 실행할 지는 가비지 컬렉터에게 의존적입니다
- 가비지 컬렉터는 언제 실행될 지 정확히 예측할 수 없습니다
- finalizer 스레드는 우선순위가 낮습니다
- cleaner는 특정 스레드를 할당 할 수 있 수 있지만 즉각 수행된다는 보장은 없습니다

**2. finalizer 동작중 발생한 예외는 무시되며, 처리할 작업이 남아도 종료됩니다**
- 예외처리를 제대로 하지 못하면, 예외는 무시되고, object는 소멸됩니다

**3. finalizer와 cleaner는 성능 문제를 일으킬 수 있습니다**
- finalizer는 매우 비용이 큰 작업입니다.

**4. finalizer 보안문제를 일으 킬 수도 있습니다**
- 생성자 또는 직렬화 과정(readObject, readResolve) 과정에서 예외가 발생합니다
  - (ex. ClassNotFoundException)하면, 생성하다 만 객체에서 finalizer가 수행 되는데, 이 finalizer 클래스에서 악의적인 공격을 수행시킬수 있습니다
- finalize()는 상속이 가능하기에 이런 방법이 가능합니다
