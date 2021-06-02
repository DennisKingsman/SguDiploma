package com.sgu.elena;

import java.util.Arrays;
import java.util.Scanner;

public class GameTheory {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //2
        int sumA = scanner.nextInt();
        //1
        int sumB = scanner.nextInt();
        //3
        int marketNum = scanner.nextInt();
        int[] arrayS = new int[marketNum];
        double[] arrayAlpha = new double[marketNum];
        double[] arrayK = new double[marketNum];
        for (int i = 0; i < marketNum; i++) {
            arrayS[i] = i + 1;
        }
        System.out.println("Enter alpha by one");
        for (int i = 0; i < marketNum; i++) {
            arrayAlpha[i] = scanner.nextDouble();
        }
        System.out.println("Enter k by one");
        for (int i = 0; i < marketNum; i++) {
            arrayK[i] = scanner.nextDouble();
        }
//        for (int i = 0; i < marketNum; i++) {
//            int m = i + 1;
//            int[] twinS = Arrays.copyOf(arrayS, arrayS.length);
//            print(twinS, twinS.length);
//            while (nextSet(twinS, twinS.length, m)) {
//                print(twinS, twinS.length);
//            }
//        }

        while (nextSet(arrayS, arrayS.length, 3)) {
            print(arrayS, 3);
        }

    }

    private static void print(int[] array, int len) {
        for (int i = 0; i < len; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    private static boolean nextSet(int[] arrayS, int marketNum, int m) {
        for (int i = m - 1; i >= 0 ; --i) {
            if (arrayS[i] < marketNum - m + i + 1) {
                ++arrayS[i];
                for (int j = i + 1; j < m; ++j) {
                    arrayS[j] = arrayS[j - 1] + 1;
                }
                return true;
            }
        }
        return false;
    }
    
}
