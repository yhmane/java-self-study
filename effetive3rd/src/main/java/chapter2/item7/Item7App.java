package chapter2.item7;

import java.util.WeakHashMap;

public class Item7App {

    public static void main(String[] args) {
        WeakHashMap<String, Object> map = new WeakHashMap<>();

        String key1 = new String("key1");
        String key2 = new String("key2");

        map.put(key1, "test 1");
        map.put(key2, "test 2");

        key1 = null;

        System.gc();
        map.entrySet()
            .stream()
            .forEach(System.out::println);
    }
}
