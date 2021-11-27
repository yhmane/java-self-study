package chapter2.item1;

import chapter2.item1.fruit.Fruit;

public class Item1App {

    public static void main(String[] args) {

        // 전통적인 객체 생성 방법
        Student student = new Student();

        /**
         *  정적 팩터리 메서드 이용시 장점
         *  #1 이름을 부여하여 의도를 파악하기 수월해진다
         *  이름은 홍길동이며 나이는 13살이다!!
         */
        Student student1 = Student.studentWithNameAge("홍길동", 13);

        /**
         * 정적 팩터리 메서드 이용시 장점
         * #2 호출될 때마다 인스턴스를 새로 생성하지 않아도 됩니다
         * 10000원 짜리는 3개의 activity 를 체험할 수 있다
         */
        Activity threeActivity = Activity.getThreeActivity();
        Activity fiveActivity = Activity.getFiveActivity();

        /**
         * 정적 팩터리 메서드 이용시 장점
         * #3 반환 타입의 하위 타입 객체를 반환할 수 있는 능력이 있습니다
         * 구현 클래스는 공개하지 않고도 그 객체를 반환할 수 이어 API 를 작게 유지할 수 있습니다
         * 카페인/논카페인 음료를 생성할 수 있습니다
         */
        Coffee noneCaffeineCoffee = Coffee.coffeeWithCaffeine(false);
        Coffee caffeineCoffee = Coffee.coffeeWithCaffeine(true);

        /**
         * 정적 팩터리 메서드 이용시 장점
         * #4 입력 매개변수에 따라 매번 다른 클래스의 객체를 반환할 수 있습니다
         * 반환 타입의 하위 타입이기만 하면 어떤 클래스의 객체를 반환하든 상관 없습니다
         */
        Fruit apple = Fruit.getFruit("apple");
        Fruit banana = Fruit.getFruit("banana");
        Fruit strawberry = Fruit.getFruit("strawberry");

        /**
         * 정적 팩터리 메서드 이용시 장점
         * #5 정적 팩터리 메서드를 작성하는 시점에는 반환할 객체의 클래스가 존재하도 않아도 됩니다
         * 컴파일시 문제는 없지만, 예외를 던질수 있습니다
         */
        Fruit incomeFruit = Fruit.getIncomeFruit();
    }
}
