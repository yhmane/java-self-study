# 05. 자원을 직접 명시하지 말고 의존 객체 주입을 사용하라
많은 클래스가 하나 이상의 자원에 의존합니다.
맞춤법 검사기는 사전(dictionay)에 의존하는데, 이러한 클래스를 정적 유틸리티 클래스로 구현하는 것을 볼수 있습니다

* 정적 유틸리티 클래스
```java
public class SpellChecker {
	private static final Lexicon dictionary = ...; // 사전
	private SpellChecker() {} 
    	public static boolean isValid(String word) { ... } 
    	public static List<String> suggestions(String typo) { ... } 
} 

SpellChecker.isValid("word")
```

비슷하게 싱글턴으로 구현한 것도 볼수 있습니다

* 싱글턴
```java
public class SpellChecker { 
	private final Lexicon dictionary = ...; 
    	private SpellChecker(...) {} 
    	public static SpellChecker INSTANCE = new SpellChecker(...); 
    	public static boolean isValid(String word) { ... } 
    	public static List<String> suggestions(String typo) { ... } 
} 

SpellChecker.INSTANCE.isValidisValid("word")
```

두 방법 모두 확장이 어렵고 테스트하기 어렵습니다
사전의 경우 국어사전, 영어사전, 일어사전 등 다양한 종류의 사전이 존재하는데
단 하나만 사용하는 가정은 좋아 보이지 않습니다

> *여러 사전을 쓸수 있도록 만들어 보자*
Final을 제거하고 다른 사전으로 교체할 수 있도록 메서드를 추가해 보겠습니다

```java
public class SpellChecker {
	private static Lexicon dictionary = ...;
    
    ...
    public static void changeDictionary(Lexicon newDictionary) {
    	dictionary = newDictionary;
    }
    ...
}

SpellChecker.changeDictionary(newDictionary);
```

이 방법은 오류를 내기 쉬우며 멀티스레드 환경에서는 사용할 수 없습니다

### 의존객체 주입방법
인스턴스를 생성할 때 생성자에 필요한 자원을 넘겨주는 방식

```java
public class SpellChecker {
    private final Lexicon dictionary;
    
    public SpellChecker(Lexicon dictionary){
    	this.dictionary = Objects.requireNotNull(dictionary);
    }
    
    public static boolean isVaild(String word) {...}
	  ...
}

// 인터페이스
interface Lexicon {}
public class EnglishDicionary implements Lexicon {
	...
}

// 사용
Lexicon englishDictionary = new EnglishDicionary();
SpellChecker spellChecker = new SpellChecker(englishDictionary);

spellChecker.isVaild(word);
```

유연성과 테스트 용이성을 높여줍니다.
또한, 불변을 보장하여 여러 클라이언트가 의존 객체들을 안심하고 공유할 수 있습니다

#### 의존성이 많아지게 되면 다소 불편함감이 없지 않지만,  이러한 문제는 프레임워크 레벨에서 다룰 수 있습니다

#책/이펙티브자바/2장/객체의생성과파괴/item5