package com.sgu.gamebet;

import java.util.*;

public class Application {

    public static double[] states = new double[]{0, 0.2, 0.4, 0.6, 0.8, 1};

    public static void main(String[] args) {
        //p and 1 - p
        Scanner scanner = new Scanner(System.in);
        double p = scanner.nextDouble();
        double q = 1 - p;
        int n = scanner.nextInt();
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

        for (int k = 0; k < n; k++) {
            //x >= 1 -> r(x) = 1
            List<Double> qTiPlusOne = new ArrayList<>();
            List<Double> pTiPlusOne = new ArrayList<>();

            //v_{t + 1} while p
            for (double calc : goodCalc) {
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

            //u_t(x, a) = Mv_{t + 1}
            List<Double> mathExpectation = new ArrayList<>();
            for (int i = 0; i < pTiPlusOne.size(); ++i) {
                if (pTiPlusOne.get(i) == separator) {
                    mathExpectation.add(separator);
                } else {
                    mathExpectation.add(pTiPlusOne.get(i) * p + qTiPlusOne.get(i) * q);
                }
            }

            System.out.println("MathExpectation");
            printWithSeparator(mathExpectation, separator);
            System.out.println("");

            //v_t = max u_t(x, a)
            List<Double> max = new ArrayList<>();
            double maxValue = 0.0;
            for (int i = 0; i < mathExpectation.size(); ++i) {
                if (mathExpectation.get(i) == separator) {
                    max.add(maxValue);
//                max.add(separator);
                    maxValue = 0.0;
                } else if (mathExpectation.get(i) > maxValue) {
                    maxValue = mathExpectation.get(i);
                }
            }
            System.out.println("MAX");
            printWithSeparator(max, separator);
            System.out.println("");

            //strategy
            List<Double> strategy = new ArrayList<>();
            int maxCounter = 0;
            int counter = 0;
            for (int i = 0; i < mathExpectation.size(); ++i) {
                if (mathExpectation.get(i) == separator) {
                    strategy.add(separator);
                    maxCounter++;
                    counter = 0;
                } else if (mathExpectation.get(i).equals(max.get(maxCounter))) {
                    strategy.add(states[counter]);
                    counter++;
                } else {
                    counter++;
                }
            }
            System.out.println("Strategy");
            printWithSeparator(strategy, separator);
            System.out.println("");
            //new r(x)
            double border = 0.0;
            xVal = new ArrayList<>();
            vKeyByX = new ArrayList<>();
            xVal.add(0.0);
            vKeyByX.add(0.0);
            for (int i = 0; i < max.size(); ++i) {
                if (max.get(i) > border) {
                    xVal.add(states[i]);
                    vKeyByX.add(max.get(i));
                    border = max.get(i);
                }
            }
            System.out.println("NEW r(x)");
            printWithSeparator(xVal, separator);
            System.out.println("");
            printWithSeparator(vKeyByX, separator);
        }
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
