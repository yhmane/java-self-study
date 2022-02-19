package sort.bubble;

import java.util.Arrays;

public class BubbleSort {

    public static void main(String[] args) {
        int[] num =  {10, 5, 8, 3, 2, 1, 9};
        sort(num);
    }

    public static void sort(int[] numbers) {
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] > numbers[j]) {
                    int temp = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = temp;
                }
            }
        }

        System.out.println(Arrays.toString(numbers));
    }
}
