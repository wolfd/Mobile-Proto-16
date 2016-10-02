package io.wolfd.stockandflow;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class StockTicker {
    private String letters;
    private double price;

    private ArrayList<HistoricalPrice> historicalPrices;

    public StockTicker(String letters, double price) {
        this.letters = letters;
        this.price = price;

        historicalPrices = new ArrayList<>();
    }

    public String getLetters() {
        return letters;
    }

    public double getPrice() {
        return price;
    }

    /**
     * Get price string for displaying in a view
     * @return friendly price string
     */
    public String getPriceString() {
        if (price == -1d) {
            return "Loading...";
        }

        // this is not internationalized
        return NumberFormat.getCurrencyInstance(
                new Locale("en", "US")
        ).format(getPrice());
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Return the historical data as a LineDataSet
     * @return
     */
    public LineDataSet getHistoricalGraphData() {
        ArrayList<Entry> entries = new ArrayList<>();

        long baseTime = 0;
        if (historicalPrices.size() > 0) {
            baseTime = historicalPrices.get(0).getWhen();
        }

        for (HistoricalPrice h : historicalPrices) {
            entries.add(h.getChartEntry(baseTime));
        }
        return new LineDataSet(entries, "Price");
    }

    /**
     * Get the minimum price over the stored history
     * @return
     */
    public double getPriceMin() {
        double min = 0;

        if (historicalPrices.size() > 0) {
            min = historicalPrices.get(0).getPrice();
        }

        for (HistoricalPrice h : historicalPrices) {
            double price = h.getPrice();

            if (price < min) {
                min = price;
            }
        }

        return min;
    }

    public void setHistoricalPrices(ArrayList<HistoricalPrice> historicalPrices) {
        this.historicalPrices = historicalPrices;
    }
}
