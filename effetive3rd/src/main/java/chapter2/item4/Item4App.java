package chapter2.item4;

import java.util.Arrays;

public class Item4App {

    public static void main(String[] args) {
        Math.abs(5);
        Arrays.sort(new int[]{5, 3});

        // 인스턴스화 불가능
        //Item4 item4 = new Item4();
        Item4.play();
    }
}
