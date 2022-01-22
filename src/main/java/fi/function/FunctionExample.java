package fi.function;

import java.util.function.BiFunction;
import java.util.function.Function;

public class FunctionExample {

    public static void main(String[] args) {
        Function<Integer, Integer> multiplyFunction = number -> number * number;
        System.out.println(multiplyFunction.apply(10));

        Function<Integer, Integer> minusFunction = number -> number - 10;
        Integer result = multiplyFunction.andThen(minusFunction).apply(10);
        System.out.println(result);

        BiFunction<Integer, Integer, Integer> biFunction = (num1, num2) -> num1 * num2;
        System.out.println(biFunction.apply(10, 20));
    }
}
