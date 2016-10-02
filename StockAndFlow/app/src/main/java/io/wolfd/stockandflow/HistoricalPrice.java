package io.wolfd.stockandflow;

import com.github.mikephil.charting.data.Entry;

import java.util.Date;

public class HistoricalPrice {
    private double price;
    private Date when;

    public HistoricalPrice(double price, Date when) {
        this.price = price;
        this.when = when;
    }

    public double getPrice() {
        return price;
    }

    public long getWhen() {
        return when.getTime();
    }

    public Entry getChartEntry(long baseTime) {
        return new Entry(when.getTime() - baseTime, (float) price);
    }
}
