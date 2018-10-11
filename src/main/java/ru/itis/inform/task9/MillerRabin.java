package ru.itis.inform.task9;

import java.util.Random;

public class MillerRabin {
    public static void main(String[] args) {
        System.out.println("1007: " + primeTest(1007));
        System.out.println("1009: " + primeTest(1009));
        System.out.println(primeTest(2019));
    }

    public static boolean primeTest(int n) {
        if (n < 3) {
            throw new IllegalArgumentException("N must me bigger than 2");
        }
        Random rd = new Random();
        int t = n - 1;
        int s = 0, k = 10;
        while (t % 2 == 0) {
            t /= 2;
            s++;
        }
        int a, x;
        next:
        for (int i = 0; i < k; i++) {
            a = rd.nextInt(n - 3) + 2;
            x = powMod(a, t, n);
            if (x == 1 || x == n - 1) {
                continue;
            }
            for (int j = 0; j < s - 1; j++) {
                x = powMod(x, 2, n);
                if (x == 1) {
                    return false;
                }
                if (x == n - 1) {
                    continue next;
                }
            }
            return false;
        }
        return true;
    }

    public static int pow(int a, int n) {
        if (n == 0) {
            return 1;
        }
        if (n % 2 == 1) {
            return pow(a, n - 1) * a;
        } else {
            int b = pow(a, n / 2);
            return b * b;
        }
    }

    public static int powMod(int a, int b, int c) {
        String b2 = Integer.toString(b, 2);
        int result = 1;
        for (char c1 : b2.toCharArray()) {
            result = (result * result) % c;
            if (c1 == '1') {
                result = result * a % c;
            }
        }
        return result;
    }
}
