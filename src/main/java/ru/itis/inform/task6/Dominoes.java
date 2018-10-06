package ru.itis.inform.task6;

import ru.itis.inform.task4.Edge;

import java.util.*;

public class Dominoes {
    public static void main(String[] args) {
        List<Integer> dominoes = Arrays.asList(
                0, 10, 0, 1, 1, 2,
                5, 4, 4, 3, 3, 3,
                2, 6, 6, 1, 4, 2,
                3, 6, 0, 2, 5, 4,
                1, 6, 10, 9, 9, 0);
        HashMap<Double, ArrayList<Double>> peaks = new HashMap<>();
        HashMap<Edge, Integer> edges = new HashMap<>();
        double first, second;
        for (int i = 0; i < dominoes.size(); i += 2) {
            first = dominoes.get(i);
            second = dominoes.get(i + 1);
            if (!peaks.containsKey(first)) {
                peaks.put(first, new ArrayList<>());
            }
            if (!peaks.containsKey(second)) {
                peaks.put(second, new ArrayList<>());
            }
            peaks.get(first).add(second);
            peaks.get(second).add(first);

            Edge edge = new Edge(first, second);
            if (!edges.containsKey(edge)) {
                edges.put(edge, 1);
            } else {
                edges.put(edge, edges.get(edge) + 1);
            }
        }

        double count = 0;
        Stack<Edge> stack = new Stack<>();
        for (Map.Entry<Double, ArrayList<Double>> entry : peaks.entrySet()) {
            if (entry.getValue().size() % 2 != 0) {
                System.out.println("No");
                System.exit(0);
            }
        }
        double cur = (double) peaks.keySet().toArray()[0];

        while (!stack.isEmpty() || peaks.get(cur).size() > 0) {
            ArrayList<Double> adj = peaks.get(cur);
            Edge edge;
            if (adj.size() > 0) {
                double next = adj.get(0);
                stack.add(new Edge(cur, next));
                peaks.get(cur).remove(next);
                peaks.get(next).remove(cur);
                cur = next;
                count++;
            } else {
                edge = stack.pop();
                cur = edge.getFrom();
                System.out.println(edge);
            }
        }
        System.out.println(count - dominoes.size() / 2 >= 0 ? "\nYes" : "\nNo");
    }
}
