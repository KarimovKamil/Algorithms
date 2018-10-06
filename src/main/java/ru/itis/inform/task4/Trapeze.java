package ru.itis.inform.task4;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Trapeze {
    private static BufferedWriter writer;

    public static void main(String[] args) throws IOException {
        for (int f = 1; f <= 9; f++) {
            Scanner sc = new Scanner(new File("src/main/resources/task4/in/" + f));
            int n = sc.nextInt();
            HashMap<Double, Peak> peaksFor1 = new HashMap<>();
            HashMap<Double, ArrayList<Double>> peaksFor2 = new HashMap<>();
            HashMap<Edge, ArrayList<Integer>> trapezesFor1 = new HashMap<>();
            HashMap<Edge, ArrayList<Integer>> trapezesFor2 = new HashMap<>();
            double xb = 0, xcd = 0;
            int countB = 0;
            int countCD = 0;
            for (int i = 0; i < n; i++) {
                xb = sc.nextDouble();
                xcd = sc.nextDouble() - sc.nextDouble();

                if (!peaksFor1.containsKey(xb)) {
                    peaksFor1.put(xb, new Peak(xb));
                    peaksFor2.put(xb, new ArrayList<>());
                }
                if (!peaksFor1.containsKey(xcd)) {
                    peaksFor1.put(xcd, new Peak(xcd));
                    peaksFor2.put(xcd, new ArrayList<>());
                }

                peaksFor1.get(xb).setFrom(xcd);
                peaksFor1.get(xcd).setTo(xb);
                peaksFor2.get(xb).add(xcd);
                peaksFor2.get(xcd).add(xb);

                Edge edge = new Edge(xb, xcd);
                if (!trapezesFor1.containsKey(edge)) {
                    trapezesFor1.put(edge, new ArrayList<>());
                    trapezesFor2.put(edge, new ArrayList<>());
                }
                trapezesFor1.get(edge).add(i);
                trapezesFor2.get(edge).add(i);

                if (xb == 0) {
                    countB++;
                }
                if (xcd == 0) {
                    countCD++;
                }
            }

            var1(f, n, peaksFor1, trapezesFor1, countB, countCD);
            var2(f, n, peaksFor2, trapezesFor2, countB + countCD);
        }
    }

    private static void var1(int f, int n, HashMap<Double, Peak> peaks, HashMap<Edge, ArrayList<Integer>> trapezes,
                             int countB, int countCD) throws IOException {
        writer = new BufferedWriter(new FileWriter("src/main/resources/task4/out/var1/" + f));

        if (countB == 0 || countB != countCD) {
            writer.append("Нет, нет нужного количества трапеций с прямым углом");
            writer.close();
            return;
        }

        int count = 0;
        Stack<Edge> stack = new Stack<>();
        double cur = 0;
        for (Map.Entry<Double, Peak> entry : peaks.entrySet()) {
            if (entry.getKey() == 0 && entry.getValue().getFrom().size() > 0) {
                cur = entry.getKey();
            }
        }

        for (Map.Entry<Double, Peak> entry : peaks.entrySet()) {
            if (entry.getValue().getFrom().size() != entry.getValue().getTo().size()) {
                writer.append("Нет, не все вершины нужной степени");
                writer.close();
                return;
            }
        }

        Stack<Integer> out = new Stack<>();
        while (!stack.isEmpty() || peaks.get(cur).getFrom().size() > 0) {
            List<Double> adj = peaks.get(cur).getFrom();
            if (adj != null && adj.size() > 0) {
                double next = adj.get(0);
                stack.add(new Edge(cur, next));
                peaks.get(cur).getFrom().remove(next);
                cur = next;
                count++;
            } else {
                Edge edge = stack.pop();
                cur = (int) edge.getFrom();
                out.add(trapezes.get(edge).get(0));
                trapezes.get(edge).remove(0);
            }
        }

        if (count == n) {
            while (!out.isEmpty()) {
                writer.append(String.valueOf(out.pop())).append(" ");
            }
        } else {
            writer.append("Нет\n");
        }
        writer.close();
    }

    private static void var2(int f, int n, HashMap<Double, ArrayList<Double>> peaks, HashMap<Edge, ArrayList<Integer>> trapezes,
                             int rightCount) throws IOException {
        writer = new BufferedWriter(new FileWriter("src/main/resources/task4/out/var2/" + f));

        if (rightCount == 0 || rightCount % 2 != 0) {
            writer.append("Нет, нет нужного количества трапеций с прямым углом");
            writer.close();
            return;
        }

        int count = 0;
        Stack<Edge> stack = new Stack<>();
        for (Map.Entry<Double, ArrayList<Double>> entry : peaks.entrySet()) {
            if (entry.getValue().size() % 2 != 0) {
                writer.append("Нет, не все вершины четной степени");
                writer.close();
                return;
            }
        }

        double cur = 0;
        for (Map.Entry<Double, ArrayList<Double>> entry : peaks.entrySet()) {
            if (entry.getKey() == 0) {
                cur = entry.getKey();
            }
        }

        Stack<String> out = new Stack<>();
        while (!stack.isEmpty() || peaks.get(cur).size() > 0) {
            List<Double> adj = peaks.get(cur);
            if (adj != null && adj.size() > 0) {
                double next = adj.get(0);
                stack.add(new Edge(cur, next));
                peaks.get(cur).remove(next);
                peaks.get(next).remove(cur);
                cur = next;
                count++;
            } else {
                Edge edge = stack.pop();
                cur = (int) edge.getFrom();
                if (trapezes.get(edge) == null || trapezes.get(edge).size() == 0) {
                    edge = new Edge(edge.getTo(), edge.getFrom());
                    out.add(trapezes.get(edge).get(0) + "(1)");
                } else {
                    out.add(trapezes.get(edge).get(0) + "");
                }
                trapezes.get(edge).remove(0);
            }
        }

        if (count == n) {
            while (!out.isEmpty()) {
                writer.append(String.valueOf(out.pop())).append(" ");
            }
        } else {
            writer.append("Нет\n");
        }
        writer.close();
    }
}
