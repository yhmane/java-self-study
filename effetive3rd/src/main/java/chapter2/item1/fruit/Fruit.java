package chapter2.item1.fruit;

public class Fruit {

    public static Fruit getFruit(String name) {
        if ("APPLE".equalsIgnoreCase(name)) {
            return new Apple();
        } else if ("BANANA".equalsIgnoreCase(name)) {
            return new Banana();
        } else {
            return new StrawBerry();
        }
    }

    public static Fruit getIncomeFruit() {
        Fruit incomeFruit = null;

        try {
            Class<?> children = Class.forName("chapter2.item1.fruit.ShineMusket");
            incomeFruit = (Fruit) children.newInstance();
        } catch (ClassNotFoundException e) {
            System.out.println("클래스가 없습니다.");
        } catch (InstantiationException e) {
            System.out.println("추상클래스/인터페이스는 생성오류입니다.");
        } catch (IllegalAccessException e) {
            System.out.println("생성자 파라미터를 확인하세요");
        }

        return incomeFruit;
    }
}
