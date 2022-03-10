package sort;

import java.util.Arrays;

public class InsertSort {

    public static void main(String[] args) {
        int[] num =  {10, 5, 8, 3, 2, 1, 9};
        sort(num);
    }

    public static void sort(int[] numbers) {
        for (int i = 1; i < numbers.length; i++) {
            for (int j = i; j > 0; j--) {
                if (numbers[j] < numbers[j -1]) {
                    int temp = numbers[j];
                    numbers[j] = numbers[j - 1];
                    numbers[j - 1] = temp;
                }
            }
        }

        System.out.println(Arrays.toString(numbers));
    }
}
