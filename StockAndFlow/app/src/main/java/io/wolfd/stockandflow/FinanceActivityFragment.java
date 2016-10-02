package io.wolfd.stockandflow;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.RequestQueue;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class FinanceActivityFragment extends Fragment {
    private FloatingActionButton fab;

    private final ArrayList<StockTicker> stockList;

    public FinanceActivityFragment() {
        super();

        stockList = new ArrayList<>();

        stockList.add(new StockTicker("AAPL", -1));
        stockList.add(new StockTicker("GOOG", -1));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RequestQueue queue = StockHttpSingleton.getInstance(getContext().getApplicationContext()).
                getRequestQueue();

        View view = inflater.inflate(R.layout.fragment_finance, container, false);

        final StockTickersAdapter adapter = new StockTickersAdapter(getContext(), stockList, queue);

        ListView listView = (ListView) view.findViewById(R.id.stock_tickers_list);
        listView.setAdapter(adapter);

        fab = (FloatingActionButton) view.findViewById(R.id.fab);

        final Context context = getContext();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText input = new EditText(context);
                input.setHint("GOOG");
                input.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);

                // use the alert dialog builder to make the edit popup
                final AlertDialog editDialog = new AlertDialog.Builder(context)
                        .setView(input)
                        .setTitle(R.string.add_new_stock_title)
                        .setMessage(R.string.add_new_stock_description) // what should the popup say?
                        .setCancelable(true) // you should be able to exit without saving the new value
                        .setPositiveButton(R.string.add_new_stock_add, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final String savedText = input.getText().toString(); // get the new value
                                stockList.add(new StockTicker(savedText, -1));
                            }
                        })
                        .create();

                // show the keyboard when you open the edit popup
                editDialog.getWindow().setSoftInputMode(
                        WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE
                );

                // set cursor to after the last character
                input.setSelection(input.getText().toString().length());

                editDialog.show();
            }
        });

        return view;
    }
}
