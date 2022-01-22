package fi.operator;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public class UnaryOperatorExample {

    public static void main(String[] args) {
        UnaryOperator<Integer> unaryOperator = num -> num * num;
        System.out.println(unaryOperator.apply(12));

        BinaryOperator<Integer> binaryOperator = (num1, num2) -> num1 * num2;
        System.out.println(binaryOperator.apply(30, 10));
    }
}
