package ru.itis.inform.task15;

import java.util.Random;

public class SortTranspositionCount {
    public static void main(String[] args) {
        int k = 1000000;
        int[] array = new int[k];
        for (int i = 0; i < k; i++) {
            array[i] = i;
        }

        int n = 1000;
        long count = 1000;
        for (int i = 0; i < n; i++) {
            array = transposition(array);
            int max = array[0];
            for (int j = 1; j < array.length; j++) {
                if (array[j] > max) {
                    max = array[j];
                    count++;
                }
            }
        }

        System.out.println(count);
        System.out.println(count / (long) n);
    }

    public static int[] transposition(int[] array) {
        Random rd = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int j = rd.nextInt(i);
            int repl = array[i];
            array[i] = array[j];
            array[j] = repl;
        }
        return array;
    }
}
