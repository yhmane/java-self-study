package chapter5.item28;

import java.util.List;

public class Item28App {

    public static void main(String[] args) {
        // ArrayStoreException, RunTime 오류
        Object[] objectArray = new Long[1];
        objectArray[0] = "타입이 달라 넣을 수 없다";

        // 컴파일 되지 않는다
        // List<Object> objectList = new ArrayList<Long>();


        // List<String>[] stringLists = new List<String>[1];
        List<Integer> intList = List.of(42);
    }
}
