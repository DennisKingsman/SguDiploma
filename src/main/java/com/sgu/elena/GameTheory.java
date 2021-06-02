package com.sgu.elena;

import java.util.Arrays;
import java.util.Scanner;

public class GameTheory {

    private static double maxV = 0;
    private static int[] sZero;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //600
        System.out.println("Enter total A");
        int totalA = scanner.nextInt();
        //300
        System.out.println("Enter total B");
        int totalB = scanner.nextInt();
        //2
        System.out.println("Enter A");
        int sumA = scanner.nextInt();
        //1
        System.out.println("Enter B");
        int sumB = scanner.nextInt();
        //3
        System.out.println("Enter S number");
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

        for (int i = 0; i < marketNum; i++) {
            int m = i + 1;
            int[] twinS = Arrays.copyOf(arrayS, arrayS.length);
            do {
                System.out.println("result");
                print(twinS, m);
            } while (nextSet(twinS, twinS.length, m, sumA, arrayAlpha, arrayK));
        }
        System.out.println("MAx val is " + maxV);
        System.out.println("S_0 is ");
        print(sZero, sZero.length);

    }

    private static boolean nextSet(int[] arrayS, int marketNum, int m,
                                   int a, double[] alphas, double[] koefs) {
        //count v
        double first = 0;
        double second = 0;
        for (int i = 0; i < m; i++) {
            first += alphas[arrayS[i] - 1];
            second += 1 / koefs[arrayS[i] - 1];
        }
        double result = (first * a - 1) / second; //v

        if (result >= maxV){
            maxV = result;
            sZero = Arrays.copyOf(arrayS, m);
        }
        //C_n^k
        for (int i = m - 1; i >= 0 ; --i) {
            if (arrayS[i] < marketNum - m + i + 1) {
                ++arrayS[i];
                for (int j = i + 1; j < m; ++j) {
                    arrayS[j] = arrayS[j - 1] + 1;
                }
                //find maxV and S_0
//                if (result >= maxV){
//                    maxV = result;
//                    sZero = Arrays.copyOf(arrayS, arrayS.length);
//                }
                return true;
            }
        }
        return false;
    }

    private static void print(int[] array, int len) {
        for (int i = 0; i < len; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
    
}
