package io.wolfd.stockandflow;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;


public class StockTickersAdapter extends ArrayAdapter<StockTicker> {
    private final RequestQueue requestQueue;

    public StockTickersAdapter(Context context, ArrayList<StockTicker> stockTickers, RequestQueue requestQueue) {
        super(context, 0, stockTickers);
        this.requestQueue = requestQueue;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get stock ticker object for this view
        final StockTicker stockTicker = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater
                    .from(getContext())
                    .inflate(R.layout.item_stock_ticker, parent, false);
        }

        // Lookup views for population
        TextView stockLetters = (TextView) convertView.findViewById(R.id.stock_ticker_letters);

        stockLetters.setText(stockTicker.getLetters());

        final TextView stockPrice = (TextView) convertView.findViewById(R.id.stock_ticker_price);

        stockPrice.setText(stockTicker.getPriceString());

        final LineChart chart = (LineChart) convertView.findViewById(R.id.chart);

        setupLineChart(chart);

        setHistoricalGraphData(stockTicker, chart);

        // Fire off network requests
        // TODO: move these to a better place, cache data better
        StockFetchManager.fetchStock(stockTicker, requestQueue, stockPrice);
        StockFetchManager.fetchHistorical(stockTicker, requestQueue, chart);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StockFetchManager.fetchStock(stockTicker, requestQueue, stockPrice);
            }
        });

        return convertView;
    }

    /**
     * Remove all the bells and whistles from the graph instance
     * @param chart
     */
    private static void setupLineChart(LineChart chart) {
        final YAxis axis = chart.getAxisLeft();

        axis.setDrawLabels(false); // no axis labels
        axis.setDrawAxisLine(false); // no axis line
        axis.setDrawGridLines(false); // no grid lines
        axis.setDrawZeroLine(true); // draw a zero line

        chart.getAxisRight().setEnabled(false); // no right axis
        chart.getLegend().setEnabled(false);
        chart.getXAxis().setEnabled(false);
        axis.setEnabled(false);


        chart.setDescription("");
        chart.setNoDataText("Loading...");
        chart.setNoDataTextDescription("");

        chart.setTouchEnabled(false); // no extra bells and whistles
    }

    /**
     * Make the graph very plain
     * @param lineDataSet
     */
    private static void setupLineDataSet(LineDataSet lineDataSet) {
        lineDataSet.setColor(R.color.colorAccent);
        lineDataSet.setDrawCircles(false);
        lineDataSet.setDrawValues(false);
    }

    /**
     * Update graph with new data
     * @param stockTicker
     * @param chart
     */
    public static void setHistoricalGraphData(StockTicker stockTicker, LineChart chart) {
        LineDataSet lineDataSet = stockTicker.getHistoricalGraphData();
        setupLineDataSet(lineDataSet);
        LineData lineData = new LineData(lineDataSet);
        chart.setData(lineData);

        YAxis axisLeft = chart.getAxisLeft();
        axisLeft.setAxisMinValue((float) stockTicker.getPriceMin());

        chart.invalidate();
    }
}
