package fi;

@FunctionalInterface
public interface FunctionalInterfaceExample {

    void printMsg(String msg);
    //void printName(String name);

    default void defaultMethod() {
        System.out.println("defalut Method");
    }

    static void staticMethod() {
        System.out.println("static Method");
    }
}
