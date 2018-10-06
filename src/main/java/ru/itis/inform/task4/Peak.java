package ru.itis.inform.task4;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Peak {
    private double value;
    private List<Double> from;
    private List<Double> to;

    public Peak(double value) {
        this.value = value;
        this.from = new ArrayList<>();
        this.to = new ArrayList<>();
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public List<Double> getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from.add(from);
    }

    public List<Double> getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to.add(to);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Peak peak = (Peak) o;
        return Double.compare(peak.value, value) == 0 &&
                Objects.equals(from, peak.from) &&
                Objects.equals(to, peak.to);
    }

    @Override
    public int hashCode() {

        return Objects.hash(value, from, to);
    }
}
