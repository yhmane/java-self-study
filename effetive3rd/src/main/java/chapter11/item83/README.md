# 83. 지연 초기화는 신중히 사용하라

지연 초기화는 필드의 초기화 시점을 그 값이 처음 필요할 때까지 늦추는 기법<br/>
지연 초기화는 주로 최적화 용도로 쓰이고, <br/> 
클래스와 인스턴스 초기화 때 발생하는 위험한 순환 문제를 해결하는 효과도 있다


## 지연 초기화
다른 모든 최적화와 마찬가지로, 지연초기화에 대한 최선의 조언은 "필요할 때까지 하지 말라" - 아이템67 <br/>
지연 초기화는 인스턴스 생성 시 초기화 비용은 줄지만, 그 필드에 접근하는 비용은 커진다 <br/>
따라서 초기화가 이루어지는 비율, 실제 초기화에 드는 비용, 호출 빈도에 따라 오히려 성능이 느려질 수 있다 <br/>

> 꼭 필요한 경우가 있다 <br/> 
> 인스턴스의 사용 빈도가 낮지만 초기화 비용이 크다면 지연 초기화가 제 역할을 해준다

## 멀티 스레드 환경에서 지연 초기화
- 둘 이상의 스레드가 필드를 공유 한다면, 어떤 형태로든 동기화해야 한다, 그렇지 않으면 심각한 버그로 이어질 수 있다 - 아이템 78
- 지연 초기화가 까다로운 환경이다. 대부분의 상황에서는 일반적인 초기화가 지연 초기화보다 낫다


## 초기화 
1. 일반적인 초기화 방법 - final 한정자 사용 (아이템 17)
```java
private final FieldType field = computeFieldValue();
```

2. 지연 초기화 방법 - synchronized 한정자 사용
```java
private FieldType field;
private synchronized FieldType getField() {
    if (field == null)
        field = computeFieldValue();
    return field;
}
```
지연 초기화가 초기화 순환성을 깨트릴 것 같다면 synchronized를 사용


3. 정적 필드 지연 초기화 홀더 클래스 관용구
```java
private static class FieldHolder {
    static final FieldType field = computeFieldValue();
}
private static FieldType getField() { return FieldHolder.field; }
```
성능 때문에 정적 필드를 지연 초기화해야 한다면 지연 초기화 홀더 클래스 관용구를 사용 <br/>
getField() 메서드가 호출되면 FieldHolder.field가 읽히면서 FieldHolder 클래스 초기화를 촉발한다 <br/>
동기화를 하지 않기 때문에 성능이 느려질 걱정이 전혀 없다는 장점이 있다


4. 인스턴스 필드 지연 초기화 이중검사 관용구
```java
private volatile FieldType field;

private FieldType getField() {
    FieldType result = field;
    if (result != null)    // 첫 번째 검사 (락 사용 안 함)
        return result;
    
    synchronized(this) {
        if (field == null) // 두 번째 검사 (락 사용)
            field = computeFieldValue();
        return field;
    }
}
```
성능 때문에 인스턴스 필드를 지연 초기화해야 한다면 이중검사 관용구를 사용<br/> 
이 관용구는 초기화된 필드에 접근할 때의 동기화 비용을 없애준다 <br/>
이름에서 알 수 있듯이 필드의 값을 두번 검사한다<br/>
한 번은 동기화 없이 검사하고, 두 번째는 동기화하여 검사한다 <br/>
두 번째 검사에서도 필드가 초기화되지 않았을 때만 필드를 초기화한다<br/>
필드가 초기화된 후로는 동기화하지 않으므로 해당 필드는 volatile로 선언해야 한다 <br/>
...
result  필드가 이미 초기화된 상황에서 이 필드가 한번만 읽도록 보장하고 성능을 높여준다.


5. 단일검사 관용구 - 초기화가 중복해서 일어날 수 있다
```java
private volatile FieldType field;

private FieldType getField() {
    FieldType result = field;
    if (result == null)
        field = result = computeFieldValue();
    return result;
}
```
이중검사 관용구에서 반복해서 초기화해도 상관없는 인스턴스 필드를 지연 초기화할 때 사용할 수 있다<br/>
모든 스레드가 필드의 값을 다시 계산해도 상관없고 필드의 타입이 long과 double을 제외한 다른 기본 타입이라면, 필드 선언에서 volatile 한정자를 없애도 된다


## 정리
* 대부분의 필드는 지연 초기화 대신 곧바로 초기화 하도록 한다
* 성능 때문에 지연 초기화를 사용해야 한다면 올바른 지연 초기화 기법을 사용
* 인스턴스 필드는 이중검사 관용구
* 정적 필드는 지역 초기화 홀더 클래스 관용구
* 반복해 초기화해도 괜찮은 인스턴스 필드는 단일검사 관용구가 고려 대상
