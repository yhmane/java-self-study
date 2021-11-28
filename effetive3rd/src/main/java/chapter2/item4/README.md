# 04. 인스턴스화를 막으려거든 private 생성자를 사용하라
## 정적 메서드와 정적 필드만을 담은 클래스
* 객체 지향적 방식과는 거리가 멀지만, 나름의 쓰임새가 존재합니다.
    * ex) java.lang.Math, java.util.Arrays, java.util.Collections
* 특정 메서드들을 모아놓은 유틸성 클래스들이 그러합니다.

```java
public class Item4App { 
    public static void main(String[] args) {
      Math.abs(5);
      Arrays.sort(new int[]{5, 3});
    }
}
```

## 하지만 생성자를 명시하지 않으면 컴파일러가 자동으로 기본 생성자를 생성
* public 생성자가 만들어지며, 사용자가 의도한 것인지 자동생성된 것인지 알 수 없습니다.
* 실제로 공개된 API 에서도 의도치 않게 인스턴스화할 수 있는 클래스가 종종 목격됩니다.
* 추상 클래스로 만드는 것도 하위 클래스를 만들면 인스턴스화를 할 수 있습니다.
* private으로 생성자를 선언하면 인스턴스화가 불가능합니다. 마찬가지로 상속이 불가능하여 원치 않는 인스턴스화를 막을 수 있습니다.

```java
public class Item4 {

  private Item4() {
		throw new AssertionError(); 
  }

  public static void play() {
      System.out.println(“just play”);
  }
}

public class Item4Main {  
    public static void main(String[] args) {  
        // 인스턴스화 불가능  
        //Item4 item4 = new Item4();  
        Item4.play();  
    }  
}
```

### 주의하면 좋은것
클래스 바깥에서는 private 생성자에 접근할 수 없으니 Error를 꼭 던질 필요는 없습니다. 다만, 설정을 해둔다면 클래스 안에서 실수로라도 호출을 않도록 해주기에 어떤 환경에서도 클래스가 인스턴스화 되는 것을 막아 줍니다
