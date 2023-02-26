package Qno8;

public class Q8A {

    public static int getMaxSqArea(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] dp = new int[rows][cols];
        int maxArea = 0;

        // initialize first row and first column of dp matrix
        for (int i = 0; i < rows; i++) {
            dp[i][0] = matrix[i][0];
            maxArea = Math.max(maxArea, dp[i][0]);
        }
        for (int j = 0; j < cols; j++) {
            dp[0][j] = matrix[0][j];
            maxArea = Math.max(maxArea, dp[0][j]);
        }

        // fill remaining cells of dp matrix
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                    maxArea = Math.max(maxArea, dp[i][j]);
                }
            }
        }

        return maxArea * maxArea;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 0, 1, 0, 0},
                {0, 1, 1, 1, 1},
                {0, 0, 0, 0, 1},
                {0, 0, 0, 1, 1},
                {0, 1, 0, 1, 1}};
        int maxSquareArea = getMaxSqArea(matrix);
        System.out.println("Maximum area of square made by 0s: " + maxSquareArea);
    }
}
