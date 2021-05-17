package com.sgu.gamebet;

import java.util.*;

public class Application {

    public static double[] states = new double[]{0, 0.2, 0.4, 0.6, 0.8, 1};

    public static void main(String[] args) {
        //p and 1 - p
        Scanner scanner = new Scanner(System.in);
        double p = scanner.nextDouble();
        double q = 1 - p;
        scanner.close();

        //r(x) | xVal x | then vByX r(x) |
        List<Double> xVal = new ArrayList<>();
        List<Double> vKeyByX = new ArrayList<>();
        vKeyByX.add(0.0);
        vKeyByX.add(1.0);
        xVal.add(0.0);
        xVal.add(1.0);

        //init step
        double separator = 6.666;
        List<Double> badCalc = new ArrayList<>();
        List<Double> goodCalc = new ArrayList<>();
        for (double x = 0; x <= 1; x += 0.2) {
            for (double a = 0; a <= x; a += 0.2) {
                badCalc.add(x - a);
                goodCalc.add(x + a);
            }
            badCalc.add(separator);
            goodCalc.add(separator);
        }

        System.out.println("For 1 - p");
        printWithSeparator(badCalc, separator);
        System.out.println("");
        System.out.println("For p");
        printWithSeparator(goodCalc, separator);

        //x >= 1 -> r(x) = 1
        double markBorder = 1;
        List<Double> qTiPlusOne = new ArrayList<>();
        List<Double> pTiPlusOne = new ArrayList<>();

        //v_{t + 1} while p
        for (double calc: goodCalc) {
            if (calc == separator) {
                pTiPlusOne.add(separator);
            } else {
                for (int i = xVal.size() - 1; i >= 0; --i) {
                    if (calc >= xVal.get(i)) {
                        pTiPlusOne.add(vKeyByX.get(i));
                        break;
                    }
                }
            }
        }

        //v_{t + 1} while p - 1
        for (double calc : badCalc) {
            if (calc == separator) {
                qTiPlusOne.add(separator);
            } else {
                for (int i = xVal.size() - 1; i >= 0; --i) {
                    if (calc >= xVal.get(i)) {
                        qTiPlusOne.add(vKeyByX.get(i));
                        break;
                    }
                }
            }
        }

        System.out.println("For p");
        printWithSeparator(pTiPlusOne, separator);
        System.out.println("For q");
        printWithSeparator(qTiPlusOne, separator);
    }

    private static void printWithSeparator(List<Double> list, double separator) {
        for (double cacl: list) {
            if (cacl == separator) {
                System.out.println("____");
            } else {
                System.out.print(cacl + " ");
            }
        }
    }

}
