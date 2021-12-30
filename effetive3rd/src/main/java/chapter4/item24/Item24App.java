package chapter4.item24;

import chapter4.item24.OuterWithStaticClass.StaticClass;

public class Item24App {

    public static void main(String[] args) {

        OuterWithStaticClass.StaticClass staticClass = new StaticClass();
        staticClass.hello();

        OuterWithNoneStaticClass noneStaticClass = new OuterWithNoneStaticClass("고길동");
        System.out.println(noneStaticClass.getName());

        Anonymouss anonymouss = new Anonymouss();
        anonymouss.hello();

        LocalClass localClass = new LocalClass();
        localClass.hello();
    }
}
