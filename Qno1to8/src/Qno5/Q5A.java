package Qno5;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class Q5A {

    public int[][] getKeyCor(int[][] height) {
        TreeMap<Integer, Integer> map = new TreeMap<>();

        for (int[] rect : height) {
            map.put(rect[0], Math.max(map.getOrDefault(rect[0], 0), rect[2]));
            map.put(rect[1], 0);
        }

        int currHeight = 0;
        int[][] res = new int[map.size()][2];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int x = entry.getKey();
            int h = entry.getValue();
            if (h != currHeight) {
                res[i][0] = x;
                res[i][1] = currHeight = h;
                i++;
            }
        }

        return Arrays.copyOfRange(res, 0, i);
    }

    public static void main(String[] args) {
        Q5A obj = new Q5A();
        int[][] height = {{1, 4, 10}, {2, 5, 15}, {5, 8, 12}, {9, 11, 1}, {11, 13, 15}};
        int[][] res = obj.getKeyCor(height);
        System.out.println(Arrays.deepToString(res));
    }


}