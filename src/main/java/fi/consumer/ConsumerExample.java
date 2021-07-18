package fi.consumer;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerExample {

    public static void main(String[] args) {
        Consumer<List<Integer>> numberConsumer = list -> {
            for (int i = 0; i < list.size(); i++) {
                list.set(i, list.get(i) * list.get(i));
            }
        };

        Consumer<List<Integer>> printConsumer = list -> {
            for (Integer num : list) {
                System.out.println(num);
            }
        };
        List<Integer> numbers = Arrays.asList(10, 20, 30, 40, 50);
        numberConsumer.andThen(printConsumer).accept(numbers);


        Consumer<String> stringConsumer = str -> System.out.println(str);
        forEach(Arrays.asList("hello", "world", "byebye"), stringConsumer);
    }

    static <T> void forEach(List<T> list, Consumer<T> consumer) {
        for (T t : list) {
            consumer.accept(t);
        }
    }
}
