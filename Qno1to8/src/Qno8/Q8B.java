package Qno8;

import java.util.ArrayList;
import java.util.List;

public class Q8B {
    public static int kthMissingEvenNumber(int[] a, int k) {
        List<Integer> missingEvens = new ArrayList<>();
        int j = 0;
        for (int i = a[0]; i < a[a.length - 1]; i += 2) {
            if (j < a.length && a[j] == i) {
                j++;
                continue;
            }
            missingEvens.add(i);
            if (missingEvens.size() == k) {
                return i;
            }
        }
        return a[a.length - 1] + 2 * k;
    }

    public static void main(String[] args) {
        int[] a = {0, 2, 6, 18, 22};
        int k = 6;
        System.out.println(kthMissingEvenNumber(a, k));
    }
}

