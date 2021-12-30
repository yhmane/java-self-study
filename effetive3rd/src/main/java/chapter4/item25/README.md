# 25. 톱레벨 클래스는 한 파일에 하나만 담아라
우리가 사용하는 파일 내에 하나만 존재하는 클래스를 톱레벨 클래스라고 합니다.
(중첩클래스는 top 레벨 클래스가 아닙니다)

하지만, 소스파일 하나에 여러개의 톱레벨 클래스가 있다면 심각한 위험을 감수해야 합니다

## 문제

Utensil.java
```java
class UtenSil {
	static final String NAME = "pan";
}

class Dessert {
	static final String NAME = "cake";
}
```

Dessert.java
```java
class UtenSil {
	static final String NAME = "pot";
}

class Dessert {
	static final String NAME = "pie";
}
```

여기서 아래의 main 함수를 실행해보자
```java
class Main {
	public static void main(String[] args) {
		System.out.println(Utensil.NAME + Dessert.NAME);
	}
}
```

일반적으론 중복 클래스가 존재한다고 컴파일 에러가 나지만,
javac Main.java Utensil.java의 명령으로 컴파일 한다면 ‘pancake’가
Javac Main.java Dessert.java의 명령으로 컴파일 한다면 ‘potpie’가 출력될 것입니다


## 해결방안
정적멤버 클래스 또는 톱레벨 클래스를 서로 다른 파일로 분리

### 정적멤버클래스
```java
class Main {
	public static void main(String[] args) {
		System.out.println(Utensil.NAME + Dessert.NAME);
	}

	static class Utensil {
		static final String NAME = "pan";
	}

	static class Dessert {
		static final String NAME = "cake";
	}
}
```

### 톱레벨 클래스로 분리
```java
class Utensil {
	static final String NAME = "pan";
}
```

```java
class Dessert {
	static final String NAME = "cake";
}
```
