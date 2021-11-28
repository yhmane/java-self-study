# 02. 생성자에 매개변수가 많다면 빌더를 고려하라
정적 팩터리와 생성자에는 똑같은 제약이 있습니다.
선택적 매개변수가 많을 때 적절히 대응하기가 어렵다는 점입니다.
프로그래머들은 점층적 생성자 패턴을 즐겨 사용하였습니다.

> *점층적 생성자 패턴(Telescoping Constructor Pattern)이란?*
> *필수 매개변수*를 가지는 생성자를 베이스로하여
> 선택 매개변수를 가지는 생성자를 추가하여
> 여러 생성자를 가지는 디자인 패턴입니다

* Telescoping Constructor Pattern
```java
public class NutritionFacts {

    private final int servingSize;  // (mL, 1회 제공량)     필수
    private final int servings;     // (회, 총 n회 제공량)  필수
    private final int calories;     // (1회 제공량당)       선택
    private final int fat;          // (g/1회 제공량)       선택
    private final int sodium;       // (mg/1회 제공량)      선택
    private final int carbohydrate; // (g/1회 제공량)       선택

    public NutritionFacts(int servingSize, int servings) {
        this(servingSize, servings, 0);
    }

    public NutritionFacts(int servingSize, int servings,
        int calories) {
        this(servingSize, servings, calories, 0);
    }

    public NutritionFacts(int servingSize, int servings,
        int calories, int fat) {
        this(servingSize, servings, calories, fat, 0);
    }

    public NutritionFacts(int servingSize, int servings,
        int calories, int fat, int sodium) {
        this(servingSize, servings, calories, fat, sodium, 0);
    }
    public NutritionFacts(int servingSize, int servings,
        int calories, int fat, int sodium, int carbohydrate) {
        this.servingSize  = servingSize;
        this.servings     = servings;
        this.calories     = calories;
        this.fat          = fat;
        this.sodium       = sodium;
        this.carbohydrate = carbohydrate;
    }

    public static void main(String[] args) {
        NutritionFacts cocaCola =
            new NutritionFacts(240, 8, 100, 0, 35, 27);
    }
}
```

- 매개변수가 적을 경우 나빠 보이지 않을 수 있지만,  수가 더 늘어나면 걷잡을 수 없게 됩니다
- 실수로 매개변수의 순서를 바꿔 건네줘도 컴파이러는 알아채지 못하고, 런타임에 엉뚱한 동작을 하게 됩니다

매개변수가 많을 때 활용할 수 있는 두번째 대안으로 자바빈즈 패턴이 있습니다

> *자바빈즈(JavaBeans Pattern) 패턴이란?*
> *매개변수가 없는 생성자*로 객체를 만든 후,
> 세터(setter) 메서드를 호출해 원하는 매개변수의 값을 설정하는 방식입니다

```java
public class NutritionFacts {

    private int servingSize  = -1; // 필수; 기본값 없음
    private int servings     = -1; // 필수; 기본값 없음
    private int calories     = 0;
    private int fat          = 0;
    private int sodium       = 0;
    private int carbohydrate = 0;

    public NutritionFacts() { }

    public void setServingSize(int val)  { servingSize = val; }
    public void setServings(int val)     { servings = val; }
    public void setCalories(int val)     { calories = val; }
    public void setFat(int val)          { fat = val; }
    public void setSodium(int val)       { sodium = val; }
    public void setCarbohydrate(int val) { carbohydrate = val; }

    public static void main(String[] args) {
        NutritionFacts cocaCola = new NutritionFacts();
        cocaCola.setServingSize(240);
        cocaCola.setServings(8);
        cocaCola.setCalories(100);
        cocaCola.setSodium(35);
        cocaCola.setCarbohydrate(27);
        cocaCola.setFat(100);
    }
}
```

- 객체를 만들고 값을 설정하기 위해서는 메서드를 여러개 호출해야 합니다
- 객체는 일관성이 깨지게 됩니다

점층적 생성자 패턴과 자바빈즈의 대안으로 빌더 패턴이 있습니다

> 빌더패턴 (Builder Pattern)이란?
> 필수매개변수만으로 생성자를 호출하여 빌더 객체를 얻습니다
> 그 후, 빌더 객체가 제공하는 세터 메서드로 원하는 선택 매개변수들을 설정하고
> 마지막으로 매개변수가 없는 build 메서드를 호출합니다

```java
public class NutritionFacts {
    
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    public static class Builder {
        // 필수 매개변수
        private final int servingSize;
        private final int servings;

        // 선택 매개변수 - 기본값으로 초기화한다.
        private int calories      = 0;
        private int fat           = 0;
        private int sodium        = 0;
        private int carbohydrate  = 0;

        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings    = servings;
        }

        public Builder calories(int val) {
            calories = val;
            return this;
        }

        public Builder fat(int val) {
            fat = val;
            return this;
        }

        public Builder sodium(int val) {
            sodium = val;
            return this;
        }

        public Builder carbohydrate(int val) {
            carbohydrate = val;
            return this;
        }

        public NutritionFacts build() {
            return new NutritionFacts(this);
        }
    }

    private NutritionFacts(Builder builder) {
        servingSize  = builder.servingSize;
        servings     = builder.servings;
        calories     = builder.calories;
        fat          = builder.fat;
        sodium       = builder.sodium;
        carbohydrate = builder.carbohydrate;
    }

    public static void main(String[] args) {
        NutritionFacts cocaCola = new NutritionFacts.Builder(240, 8)
            .calories(100).sodium(35).carbohydrate(27).build();
    }
}
```

* 불변의 성질을 갖습니다
* 빌더 자신을 반환하고 파라미터의 속성값의 의미를 알고 값을 부여할 수 있습니다

빌더 패턴에 장점만 있는 것은 아닙니다. 객체를 만들려면 그에 앞서 빌더를 만들어야 합니다. 또한, 점층적 생성자 패턴보다는 코드가 장황해서 매개변수가 4개 이상은 되어야 값어치를 합니다.
하지만 시간이 지날수록 매개변수가 많아지는 경향이 있으므로 애초에 빌더로 시작하는게 나은 때가 많습니다.

