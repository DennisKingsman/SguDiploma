package com.sgu.prom;

import java.text.DecimalFormat;
import java.util.Scanner;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class PromApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //0.5625 | 36
        double k = scanner.nextDouble();
        double n = scanner.nextDouble();
        scanner.close();
        double alpha = 0.5;
        double b = 0.0;
        double d = 0.0;
        double lambda = 2 * k * sqrt(k) / (3 * k);
        DecimalFormat decimalFormat = new DecimalFormat( "#.###" );
        for (int i = 0; i < n; i++) {
            d = (pow(lambda * b, 1 / (1 - alpha)) / (1 + pow(lambda * b, 1 / (1 - alpha))));
            System.out.print("d is " + decimalFormat.format(d) + " | ");
            b = pow(1 + pow(lambda * b, 1 / (1 - alpha)), 1 - alpha);
            System.out.println("b is " + decimalFormat.format(b));
        }

    }

}
