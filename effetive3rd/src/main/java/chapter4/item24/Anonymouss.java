package chapter4.item24;

public class Anonymouss {

    private String name;

    public void hello() {
        HelloBot helloBot = new HelloBot() {
            @Override
            public void hello() {
                System.out.println("안녕하세요. 헬로우 봇입니다");
            }
        };
        helloBot.hello();
    }
}

interface HelloBot {
    void hello();
}
