package ru.itis.inform.task17;

import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;

public class Fibonacci {
    public static void main(String[] args) {
        for (int i = 2; i < 5; i++) {
            long start = System.currentTimeMillis();
            System.out.println("Number: " + fibonacci(100000, i));
            long end = System.currentTimeMillis();
            System.out.println(" Time:" + (end - start) + "\n________________________________\n");
        }
    }

    public static BigInteger fibonacci(int n, int var) {
        if (n < 1) {
            return new BigInteger("0");
        }
        if (n == 1 || n == 2) {
            return new BigInteger("1");
        }
        try {
            Class<Fibonacci> c = Fibonacci.class;
            return (BigInteger) c.getMethod("fibonacci" + var, int.class).invoke(c, n);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return new BigInteger("0");
    }

    public static BigInteger fibonacci1(int n) {
        if (n < 1) {
            return new BigInteger("0");
        }
        if (n == 1 || n == 2) {
            return new BigInteger("1");
        }
        return fibonacci1(n - 1).add(fibonacci1(n - 2));
    }

    public static BigInteger fibonacci2(int n) {
        BigInteger[] a = new BigInteger[n];
        a[0] = new BigInteger("1");
        a[1] = new BigInteger("1");
        for (int i = 2; i < n; i++) {
            a[i] = a[i - 1].add(a[i - 2]);
        }
        return a[n - 1];
    }

    public static BigInteger fibonacci3(int n) {
        BigInteger el1 = new BigInteger("0");
        BigInteger el2 = new BigInteger("1");
        BigInteger copy;
        for (int i = 1; i < n; i++) {
            copy = el2;
            el2 = el1.add(el2);
            el1 = copy;
        }
        return el2;
    }

    public static BigInteger fibonacci4(int n) {
        return matrixPow(new BigInteger[][]{{new BigInteger("0"), new BigInteger("1")},
                {new BigInteger("1"), new BigInteger("1")}}, n)[0][1];
    }

    public static BigInteger[][] matrixPow(BigInteger[][] a, int n) {
        if (n == 1) {
            return a;
        }
        if (n % 2 == 1) {
            return multipleMatrix(matrixPow(a, n - 1), a);
        } else {
            BigInteger[][] b = matrixPow(a, n / 2);
            return multipleMatrix(b, b);
        }
    }

    public static BigInteger[][] multipleMatrix(BigInteger[][] a, BigInteger[][] b) {
        int n = a.length;
        BigInteger[][] out = new BigInteger[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                BigInteger num = new BigInteger("0");
                for (int k = 0; k < n; k++) {
                    num = num.add(a[i][k].multiply(b[k][j]));
                }
                out[i][j] = num;
            }
        }
        return out;
    }
}
