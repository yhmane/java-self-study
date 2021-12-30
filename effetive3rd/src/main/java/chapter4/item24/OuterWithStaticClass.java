package chapter4.item24;

public class OuterWithStaticClass {

    private String name;

    static class StaticClass {
        void hello() {
            OuterWithStaticClass outerWithStaticClass = new OuterWithStaticClass();
            outerWithStaticClass.name = "홍길동";
            System.out.println(outerWithStaticClass.name);
        }
    }
}
