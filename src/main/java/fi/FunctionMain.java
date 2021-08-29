package fi;

public class FunctionMain {

    public static void main(String[] args) {
        FunctionalInterfaceExample yunhoInterface = new Yunho();
        yunhoInterface.printMsg("안녕하세요");
        yunhoInterface.defaultMethod();

        FunctionalInterfaceExample.staticMethod();
        System.out.println("test");
    }
}
