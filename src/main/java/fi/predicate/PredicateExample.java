package fi.predicate;

import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PredicateExample {

    public static void main(String[] args) {
        Predicate<Integer> integerPredicate = num ->  num > 10;
        Predicate<Integer> integerPredicate1 = num -> num < 20;

        System.out.println(integerPredicate.test(5));
        System.out.println(integerPredicate.and(integerPredicate1).test(15));

        Stream<Integer> stream = IntStream.range(1, 10).boxed();
        Predicate<Integer> predicate = num -> num % 2 == 0;
        stream.filter(predicate)
            .forEach(System.out::println);
    }
}
