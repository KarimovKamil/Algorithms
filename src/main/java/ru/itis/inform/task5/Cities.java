package ru.itis.inform.task5;

import ru.itis.inform.task4.Edge;
import ru.itis.inform.task4.Peak;

import java.util.*;

public class Cities {
    public static void main(String[] args) {
        List<String> cities = Arrays.asList("Los Angeles", "Kazan", "New-York", "Kiev", "Vladivostok",
                "Krasnoyarsk", "San Diego", "Kargopol");
        HashMap<Double, Peak> peaks = new HashMap<>();
        HashMap<Edge, ArrayList<String>> edges = new HashMap<>();
        double first, last;
        for (String city : cities) {
            first = Character.toLowerCase(city.charAt(0));
            last = Character.toLowerCase(city.charAt(city.length() - 1));
            if (!peaks.containsKey(first)) {
                peaks.put(first, new Peak(first));
            }
            if (!peaks.containsKey(last)) {
                peaks.put(last, new Peak(last));
            }
            peaks.get(first).setFrom(last);
            peaks.get(last).setTo(first);
            Edge edge = new Edge(first, last);
            if (!edges.containsKey(edge)) {
                edges.put(edge, new ArrayList<>());
            }
            edges.get(edge).add(city);
        }

        int count = 0;
        Stack<Edge> stack = new Stack<>();
        double cur = -1;
        for (Map.Entry<Double, Peak> entry : peaks.entrySet()) {
            if (entry.getValue().getFrom().size() - entry.getValue().getTo().size() == 1) {
                cur = entry.getKey();
            }
        }

        if (cur == -1) {
            cur = (double) peaks.keySet().toArray()[0];
        }

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
                if (edges.containsKey(edge)) {
                    cur = (int) edge.getFrom();
                    System.out.println(edges.get(edge).get(0));
                    edges.get(edge).remove(0);
                } else {
                    cur = (int) edge.getTo();
                    System.out.println(edges.get(new Edge(edge.getFrom(), edge.getTo())));
                    
                }
            }
        }
        System.out.println(count - cities.size() >= 0 ? "\nYes" : "\nNo");
    }

    public static void checkOdd(HashMap<Integer, ArrayList<Integer>> peaks) {
        int odd = 0;
        for (Map.Entry<Integer, ArrayList<Integer>> entry : peaks.entrySet()) {
            if (entry.getValue().size() % 2 != 0) {
                odd++;
                if (odd > 2) {
                    System.out.println("No");
                    System.exit(0);
                }
            }
        }
        if (odd == 1) {
            System.out.println("No");
            System.exit(0);
        }
    }
}
