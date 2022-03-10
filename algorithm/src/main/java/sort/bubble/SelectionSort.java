package sort.bubble;

import java.util.Arrays;

// O(n2)
public class SelectionSort {

    public static void main(String[] args) {
        int[] num =  {10, 5, 8, 3, 2, 1, 9};
        sort(num);
    }

    public static void sort(int[] numbers) {
        for (int i = 0; i < numbers.length - 1; i++) {
            int least = i;
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[j] < numbers[least]) {
                    least = j;
                }
            }

            if (i != least) {
                int temp = numbers[i];
                numbers[i] = numbers[least];
                numbers[least] = temp;
            }
         }

        System.out.println(Arrays.toString(numbers));
    }
}
