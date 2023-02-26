package Qno7;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Q7A {

    private static final int NUM_THREADS = 4;

    public static void main(String[] args) {
        int n = 8;

        int[][] a = new int[n][n];
        int[][] b = new int[n][n];

        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = rand.nextInt(10);
                b[i][j] = rand.nextInt(10);
            }
        }

        int[][] c = new int[n][n];

        int blockSize = n / NUM_THREADS;
        List<MultiplierThread> threads = new ArrayList<>();
        for (int i = 0; i < NUM_THREADS; i++) {
            int startRow = i * blockSize;
            int endRow = (i == NUM_THREADS - 1) ? n : startRow + blockSize;
            threads.add(new MultiplierThread(a, b, c, startRow, endRow));
        }

        for (MultiplierThread t : threads) {
            t.start();
        }

        for (MultiplierThread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(c[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static class MultiplierThread extends Thread {
        private int[][] a;
        private int[][] b;
        private int[][] c;
        private int startRow;
        private int endRow;

        public MultiplierThread(int[][] a, int[][] b, int[][] c, int startRow, int endRow) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.startRow = startRow;
            this.endRow = endRow;
        }

        @Override
        public void run() {
            int n = a.length;
            for (int i = startRow; i < endRow; i++) {
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < n; k++) {
                        c[i][j] += a[i][k] * b[k][j];
                    }
                }
            }
        }
    }
}