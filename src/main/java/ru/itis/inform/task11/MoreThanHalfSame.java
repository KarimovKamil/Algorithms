package ru.itis.inform.task11;

import java.util.Random;

public class MoreThanHalfSame {
    public static void main(String[] args) {
        int[] a1 = new int[]{1,1,1,1,1,2,2,2,2,2};
        System.out.println("a1: " + check(a1));
        int[] a2 = new int[]{1,1,1,1,1,1,2,2,2,2};
        System.out.println("a2: " + check(a2));
        int[] a3 = new int[]{1,2,3,4,5,6,7,8,9,0};
        System.out.println("a3: " + check(a3));
        int[] a4 = new int[]{1,1,1,1,1,2,2,2,2};
        System.out.println("a4: " + check(a4));
    }

    public static boolean check(int[] array) {
        Random rd = new Random();
        int k = 20, pos, el, count;
        for (int i = 0; i < k; i++) {
            pos = rd.nextInt(array.length);
            el = array[pos];
            count = 0;
            for (int i1 : array) {
                if (i1 == el) {
                    count++;
                }
            }
            if (count > array.length / 2) {
                return true;
            }
        }
        return false;
    }
}
