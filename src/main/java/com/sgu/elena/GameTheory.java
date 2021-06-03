package com.sgu.elena;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.pow;

public class GameTheory {

    private static double maxV = 0;
    private static int[] sZero;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //600
        System.out.println("Enter total A");
        double totalA = scanner.nextDouble();
        //300
        System.out.println("Enter total B");
        double totalB = scanner.nextDouble();
        //2
//        System.out.println("Enter A");
        double sumA = totalA / totalB;
        //1
//        System.out.println("Enter B");
        int sumB = 1;
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
//                System.out.println("result");
//                print(twinS, m);
            } while (nextSet(twinS, twinS.length, m, sumA, arrayAlpha, arrayK));
        }
        System.out.println("MAx val is " + maxV);
        System.out.println("S_0 is ");
        print(sZero, sZero.length);

        //y'
        double[] yDot = new double[sZero.length];
        for (int i = 0; i < yDot.length; i++) {
            yDot[i] = -maxV / arrayK[sZero[i] - 1] + arrayAlpha[sZero[i] - 1] * sumA;
        }
        System.out.println("y dot is");
        print(yDot, yDot.length);

        //xi^*
        double sumKinMinusOnePow = 0;
        for (int i = 0; i < sZero.length; i++) {
            sumKinMinusOnePow += 1 / arrayK[sZero[i] - 1];
        }
        sumKinMinusOnePow = pow(sumKinMinusOnePow, -1);
        double[] xis = new double[sZero.length];
        for (int i = 0; i < xis.length; i++) {
            xis[i] = 1 / arrayK[sZero[i] - 1] * sumKinMinusOnePow;
        }
        System.out.println("xi^* is ");
        print(xis, xis.length);

        //b -> 300 to 160 and 140
        //does count by y' or by xi^* ??
        double[] bResources = new double[sZero.length];
        for (int i = 0; i < bResources.length; i++) {
            bResources[i] = totalB * yDot[i];
        }
        System.out.println("B resources is");
        print(bResources, bResources.length);

        //H()
        double[] hMaxMathEx = new double[sZero.length];
        for (int i = 0; i < hMaxMathEx.length; i++) {
            double res = arrayK[sZero[i] - 1] * (arrayAlpha[sZero[i] - 1] * totalA - bResources[sZero[i] - 1]);
            if (res > 0) {
                hMaxMathEx[i] = res;
            } else {
                hMaxMathEx[i] = 0;
            }
        }
        System.out.println("H maxM is");
        print(hMaxMathEx, hMaxMathEx.length);
    }

    private static boolean nextSet(int[] arrayS, int marketNum, int m,
                                   double a, double[] alphas, double[] koefs) {
        //count v
        double first = 0;
        double second = 0;
        for (int i = 0; i < m; i++) {
            first += alphas[arrayS[i] - 1]; //index for alpha by S{}
            second += 1 / koefs[arrayS[i] - 1]; //index for k by S{}
        }
        double result = (first * a - 1) / second; //v

        if (result >= maxV) {
            maxV = result;
            sZero = Arrays.copyOf(arrayS, m);
        }
        //C_n^k for S{}
        for (int i = m - 1; i >= 0; --i) {
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

    private static void print(int[] array, int len) {
        for (int i = 0; i < len; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    private static void print(double[] array, int len) {
        for (int i = 0; i < len; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

}
