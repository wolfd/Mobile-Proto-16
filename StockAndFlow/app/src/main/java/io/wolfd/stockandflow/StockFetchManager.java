package io.wolfd.stockandflow;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.github.mikephil.charting.charts.LineChart;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.StringReader;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

public class StockFetchManager {
    private final static SimpleDateFormat queryDateFormat = new SimpleDateFormat("MMM d, yyyy", Locale.US);
    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy", Locale.US);

    /**
     * Fetch and set stock price and update view
     * @param stockTicker
     * @param requestQueue
     * @param priceTextView
     */
    public static void fetchStock(
            final StockTicker stockTicker,
            RequestQueue requestQueue,
            @Nullable final TextView priceTextView) {
        final String url = MessageFormat.format(
                "http://finance.google.com/finance/info?client=iq&q={0}",
                stockTicker.getLetters()
        );

        StringRequest stockRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            // get rid of comment characters at beginning of response
                            final JSONArray j = new JSONArray(response.substring(3));
                            // stock value is stored in "l" attribute
                            double stockValue = ((JSONObject) j.get(0)).getDouble("l");

                            // save value
                            stockTicker.setPrice(stockValue);

                            // set text view immediately
                            if (priceTextView != null) {
                                priceTextView.setText(stockTicker.getPriceString());
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (priceTextView != null) {
                            priceTextView.setText(R.string.price_error);
                        }
                    }
                });

        requestQueue.add(stockRequest);
    }

    /**
     * Build the uri for the historical data CSV
     * @param stockTicker
     * @return
     */
    private static String buildHistoricalUri(StockTicker stockTicker) {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -30);
        final Date thirtyDaysAgo = cal.getTime();

        final Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .authority("www.google.com")
                .appendPath("finance")
                .appendPath("historical")
                .appendQueryParameter("q", stockTicker.getLetters())
                .appendQueryParameter("output", "csv")
                .appendQueryParameter("startdate", queryDateFormat.format(thirtyDaysAgo))
                .appendQueryParameter("enddate", queryDateFormat.format(new Date()));

        return builder.build().toString();
    }

    /**
     * Get and parse CSV of historical stock data for past month
     * @param stockTicker
     * @param requestQueue
     * @param chart
     */
    public static void fetchHistorical(final StockTicker stockTicker,
                                       RequestQueue requestQueue,
                                       @Nullable final LineChart chart) {
        String uri = buildHistoricalUri(stockTicker);

        final StringRequest stockRequest = new StringRequest(Request.Method.GET, uri,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            final CSVParser parser = CSVFormat.RFC4180
                                    .withFirstRecordAsHeader()
                                    .parse(new StringReader(response));

                            final ArrayList<HistoricalPrice> historicalPrices = new ArrayList<>();

                            for (CSVRecord record : parser.getRecords()) {
                                final String stringClosingPrice = record.get("Close");
                                final Date date = dateFormat.parse(record.get(0));
                                double closingPrice = Double.parseDouble(stringClosingPrice);

                                historicalPrices.add(new HistoricalPrice(closingPrice, date));
                            }

                            // CSV is reverse chronological
                            Collections.reverse(historicalPrices);

                            stockTicker.setHistoricalPrices(historicalPrices);

                            // set the graph data immediately
                            if (chart != null) {
                                StockTickersAdapter.setHistoricalGraphData(stockTicker, chart);
                            }

                        } catch (IOException | ParseException e) {
                            Log.e("StockFetchManager", e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                    }
                });

        requestQueue.add(stockRequest);
    }
}
