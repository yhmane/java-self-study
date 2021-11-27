01. 생성자 대신 정적 팩터리 메서드를 고려하라
    클라이언트가 클래스의 인스턴스를 얻는 전통적인 수단은 public 생성자입니다
```java
class Student {

	private String name;
	private int grade;
}

class Item1App {

	public static void main(String args[]) {
		Student student = new Student();
	}
}
```


이외에도 프로그래머가 알아야할 기법이 하나 더 있습니다,
클래스는 생성자와 별도로 정적 팩터리 메서드(static factory method)를 제공할 수 있습니다 ※ 팩터리 메서드 패턴과 다른 얘기입니다


### 정적 팩터리 메서드의 장점
1. 이름을 가질 수 있습니다
    * 매개변수와 생성자만으로 반환될 객체의 특성을 설명하기 어렵습니다
2. 호출될 때마다 인스턴스를 새로 생성하지 않아도 됩니다
    * 불변 클래스는 인스턴스를 미리 만들어 놓거나 새로 생성한 인스턴스를 캐싱하여 재활용하는 식으로 불필요한 객체 생성을 피할 수 잇습니다
    * 이 방식은 인스턴스를 살아 있게 할지를 철저히 통제할 수 있습니다.
3. 반환 타입의 하위 타입 객체를 반환할 수 있습니다
    * 생성자는 리턴값이 없지만 정적 팩토리 메소드는 반환값을 유연하게 사용할 수 있습니다
    * 구현 클래스는 공개하지 않고도 그 객체를 반환할 수 이어 API를 작게 유지할 수 있습니다
4. 입력 매개변수에 따라 매번 다른 클래스의 객체를 반환할 수 있습니다
    * 반환 타입의 하위 타입이기만 하면 어떤 클래스의 객체를 반환하든 상관 없습니다
5. 정적 팩터리 메서드를 작성하는 시점에는 반환할 객체의 클래스가 존재하지 않아도 됩니다
    * 클래스를 미리 만들지 않아도 컴파일이 가능하도록 유연함을 제공합니다

### 정적 팩터리 메서드의 단점
1. Private 생성자만 제공하면 상속이나 하위 클래스를 만들 수 없습니다
2. 정적 팩터리 메서드는 프로그래머가 찾기 어렵습니다

> 정적팩터리 메서드 사용시 통용되는 이름
* from : 매개변수를 하나 받아서 해당 타입의 인스턴스를 반환합니다
   * Date date = Date.from(instant)
* of : 여러 매개 변수를 받아 적합한 타입의 인스턴스를 반환합니다
   * Set<Rank> faceCards = EnumSet.of(JACK,QUEEN, KING)
* valueOf : from과 of의 자세한 버전
   * BigInteger prime = BigInteger.valueOf(Integer.MAX_VALUE)
* instance / getInstance : 인스턴스를 반환하지만, 같은 인스턴스임을 보장하지 않습니다
   * StackWalker luke = StackWalker.getInstance(options)
* create / newInstance : 매번 새로운 인스턴스를 반환합니다
   * Object newArray = Array.newInstance(classObject, arrayLen)
* getType : getInstance와 같으나, 생성할 클래스가 아닌 다른 클래스에 팩터리 메서드를 정의할 때 씁니다
   * FileStore fs = Files.getFileStore(path)
* newType : newInstance와 같으나, 생성할 클래스가 아닌 다른 클래스에 팩터리 메서드를 정의할 때 씁니다
   * BufferedReader br = Files.newBufferedReader(path)
* type : getType과 newType의 간결한 버전
   * List<Complaint> litany = Collections.list(lagacyListany)