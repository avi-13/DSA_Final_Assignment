package Qno3;

import java.util.Arrays;

public class Q3A {
    public static int minProductDifference(int[] arr) {
        Arrays.sort(arr);

        int n = arr.length;

        int minDiff = Integer.MAX_VALUE;

        for (int i = 0; i < n / 2; i++) {
            int product1 = arr[i] * arr[n - i - 1];
            int product2 = arr[n / 2 + i] * arr[n - n / 2 - i - 1];
            int diff = Math.abs(product1 - product2);
            minDiff = Math.min(minDiff, diff);
        }
        return minDiff;
    }

    public static void main(String[] args) {
        int[] arr = {5, 2, 4, 11};
        System.out.println(minProductDifference(arr)); // outputs 2
    }
}
