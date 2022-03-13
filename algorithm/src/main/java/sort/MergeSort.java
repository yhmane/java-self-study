package sort;

import java.util.Arrays;

//O(nlogn)
public class MergeSort {

    public static int[] nums;
    public static int[] tmp;

    public static void main(String[] args) {
        nums = new int[] {10, 5, 8, 3, 2, 1, 9};
        tmp = new int[nums.length];
        sort(0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }

    public static void sort(int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            sort(left, mid);
            sort(mid + 1, right);

            int l = left;
            int r = mid + 1;
            int idx = l;
            while (l <= mid || r <= right) {
                if (r > right || (l <= mid && nums[l] < nums[r])) {
                    tmp[idx++] = nums[l++];
                } else {
                    tmp[idx++] = nums[r++];
                }
            }

            for (int i = left; i <= right; i++) {
                nums[i] = tmp[i];
            }
        }
    }
}
