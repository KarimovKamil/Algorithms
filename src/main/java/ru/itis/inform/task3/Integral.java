package ru.itis.inform.task3;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Integral {
    public static void main(String[] args) {
        int n = new Scanner(System.in).nextInt();
        Random rd = new Random();
        double count = 100000;
        double hits = 0;
        if (n >= 1) {
            for (int i = 0; i < count; i++) {
                double[] c = new double[n];
                for (int j = 0; j < n; j++) {
                    c[j] = rd.nextDouble();
                }
                if (rd.nextDouble() < ((c[0] + 1) / (Arrays.stream(c).sum() + n))) {
                    hits++;
                }
            }
        }
        System.out.println(hits / count);
    }
}
