package ru.itis.inform.task12;

import java.util.Random;

public class SMO {
    // Вероятность того, что за час придет хотя бы 3 заявки, если средний промежуток между заявками 30 минут
    public static void main(String[] args) {
        int k = 1000000;
        int count = 0;
        int m = 30;
        double time = 60.0;
        Random rd = new Random();
        for (int i = 0; i < k; i++) {
            double timeOf3 = 0;
            for (int j = 0; j < 3; j++) {
                timeOf3 += -m * Math.log(rd.nextDouble());
            }
            if (timeOf3 <= time) {
                count++;
            }
        }
        System.out.println((double) count / (double) k);
    }
}
