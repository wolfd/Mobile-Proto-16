package io.wolfd.stockandflow;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


// Singleton implementation here looks good
public class StockHttpSingleton {
    private static StockHttpSingleton mInstance;
    private RequestQueue mRequestQueue;
    private static Context mCtx;

    private StockHttpSingleton(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized StockHttpSingleton getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new StockHttpSingleton(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}
