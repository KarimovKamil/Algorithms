package ru.itis.inform.task2;

import java.util.ArrayList;
import java.util.Random;

public class Median {
    private static Random rd = new Random();

    public static void main(String[] args) {
        int n = 999;
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arrayList.add(rd.nextInt(n));
        }
        System.out.println(findM(0, 0, arrayList));
        arrayList.sort(Integer::compareTo);
        System.out.println(arrayList.get((arrayList.size() / 2 + 1)));
    }

    public static int findM(int left, int right, ArrayList<Integer> array) {
        int m = rd.nextInt(array.size());
        int n = array.get(m);
        ArrayList<Integer> leftA = new ArrayList<>();
        ArrayList<Integer> rightA = new ArrayList<>();

        for (Integer a : array) {
            if (a < n) {
                leftA.add(a);
            } else {
                rightA.add(a);
            }
        }
        if (Math.abs((leftA.size() + left) - (rightA.size() + right - 1)) <= 1) {
            return n;
        } else {
            if (left + leftA.size() >= right + rightA.size()) {
                return findM(left, right + rightA.size(), leftA);
            } else {
                return findM(left + leftA.size(), right, rightA);
            }
        }
    }
}
