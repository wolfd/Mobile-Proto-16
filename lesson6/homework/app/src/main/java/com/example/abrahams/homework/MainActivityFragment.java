package com.example.abrahams.homework;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    @BindView(R.id.editText) EditText editText;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchTwitter(editText.getText().toString());
                    return true;
                }
                return false;
            }
        });
        return view;
    }

    public void searchTwitter(String search) {
        // Get a RequestQueue
        RequestQueue queue = MySingleton.getInstance(this.getActivity().getApplicationContext()).
                getRequestQueue();

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("api.twitter.com/1.1/search/tweets.json")
//                .appendPath("turtles")
//                .appendPath("types")
                .appendQueryParameter("q", "Donald Trump");
//                .appendQueryParameter("sort", "relevance")
//                .fragment("section-name");
        String myUrl = builder.build().toString();
        Log.d("my tag", myUrl);

        // Add a request (in this example, called stringRequest) to your RequestQueue.
//        MySingleton.getInstance(this.getActivity()).addToRequestQueue(stringRequest);


    }

}
