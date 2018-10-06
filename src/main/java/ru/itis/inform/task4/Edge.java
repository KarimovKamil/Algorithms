package ru.itis.inform.task4;

import java.util.Objects;

public class Edge {
    private double from;
    private double to;

    public Edge(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "( " + (int) from + ", " + (int) to + " )";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return Double.compare(edge.from, from) == 0 &&
                Double.compare(edge.to, to) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }
}
