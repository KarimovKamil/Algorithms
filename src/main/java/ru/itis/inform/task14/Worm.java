package ru.itis.inform.task14;

import java.math.BigDecimal;

public class Worm {
    public static void main(String[] args) {
        BigDecimal em = new BigDecimal(0.5772156649);
        BigDecimal decimal = new BigDecimal(Math.E);
        BigDecimal s = decimal.pow(99);
        BigDecimal e = decimal.pow(100);
        System.out.println(s);
        System.out.println(e);
        System.out.println(s.add(e.subtract(s).multiply(new BigDecimal("1").subtract(em))));
    }
}
