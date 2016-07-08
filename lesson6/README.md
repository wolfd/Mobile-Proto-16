# Lesson 6 - APIs and HTTP Requests
Today we'll be learning about APIs and making HTTP requests to them!

## What's an API anyway?
You've probably heard the term thrown around all the time and might have no idea what it means - no worries! An API stands for an application program interface. Think of it as a way for one application to easily communicate with another. For example, maybe you have a blog and want it to show your Twitter feed on the side. In that case, you'd need to use Twitter's API to provide a way for your blog to access Twitter's data. This example is specific to web APIs, but you'll be using a lot of Android APIs in order to make an app (e.g., an API for your app to communicate with the camera). The Android platform has a framework API that apps use to interact with the underlying Android system.

## Android APIs
Android has a *ton* of APIs built in; they're useful for using hardware sensors, accessing storage, handling user input, and setting config info.. However, you'll have to be careful with which version of Android you're using as APIs will differ depending on that. The APIs are for using hardware sensors, accessing storage, handling user input, and setting config info.

Check the sidebar in [this link](https://developer.android.com/about/versions/marshmallow/android-6.0.html) out to look at the different APIs for versions 4.1 to 6.0. [This link](https://developer.android.com/reference/android/package-summary.html) provides you with a detailed list of every Android-built API available. There are way too many to list so we won't go through them in detail, but if you've seen your phone do it, you'll find how to do it there.

## [Amazon S3](https://aws.amazon.com/s3/)
Amazon Web Services (AWS) provides something called Amazon Simple Storage Service, AKA Amazon S3. As the name implies, it's a cloud storage service. Now, instead of locally storing data on your phone you can use S3 and save it on the cloud! This is great for applications that need to share or store a lot of data.

Follow [these instructions](https://docs.aws.amazon.com/mobile/sdkforandroid/developerguide/s3transferutility.html) to set up your own S3 bucket (a cloud storage container). If you're interested in learning more about S3 past what the link above says, then check out AWS's [documentation](https://docs.aws.amazon.com/sdkfornet1/latest/apidocs/html/N_Amazon_S3_Transfer.htm).

## Libraries
Since we talked about APIs, we'll introduce some key libraries as well.
    * [Volley](https://developer.android.com/training/volley/index.html) - You'll need this to make HTTP requests. We talk about this below! Alternatively, you can use [Retrofit](https://square.github.io/retrofit/)
    * [Gson](https://github.com/google/gson) - For when you need to parse JSON
    * [Picasso](https://square.github.io/picasso/) - Makes displaying images simpler

## HTTP requests
*Note: this portion was constructed borrowing heavily from [these](https://developer.android.com/training/volley/simple.html) Android docs. Feel free to check them out for more information*

Many websites or companies offer RESTful APIs (application program interfaces) for other developers to use. REST APIs are APIs that allow you to access them using the HTTP verbs you may already be familiar with, such as `GET`, and `POST`. For your future labs and project, you may want to incorporate functionality with Twitter, Spotify, or Instagram, all of which have a REST API available.

The Android framework offers a specific protocol for making http requests. Android provides the HTTP library `Volley`. Add the following to your gradle:

```
compile 'com.android.volley:volley:1.0.0'
```

and add the `android.permission.INTERNET` to your app's manifest.

### `RequestQueue`
You use Volley by creating a `RequestQueue` and passing it `Request` objects. The Queue handles making all of your requests for you, and therefor you should only have one. The Queue will make all your requests and receive their responses.

Volley provides the function `newRequestQueue` to set up a RequestQueue. Here's an example of making a GET request to `google.com`:

```
final TextView mTextView = (TextView) findViewById(R.id.text);

// Instantiate the RequestQueue.
RequestQueue queue = Volley.newRequestQueue(this);
String url ="http://www.google.com";

// Request a string response from the provided URL.
StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
            new Response.Listener<String>() {
    @Override
    public void onResponse(String response) {
        // Display the first 500 characters of the response string.
        mTextView.setText("Response is: "+ response.substring(0,500));
    }
}, new Response.ErrorListener() {
    @Override
    public void onErrorResponse(VolleyError error) {
        mTextView.setText("That didn't work!");
    }
});
// Add the request to the RequestQueue.
queue.add(stringRequest);
```

Volley always delivers responses on the main thread. This allows you to update UI elements directly in the response listener. Ie, you make an HTTP request to Twitter to search for a tweet, and once a response comes in, you can directly update a TextView with the contents of the tweet.

### Singleton Pattern
If you are constantly making requests in your app, you will want to use a single instance of a `RequestQueue` that lasts the lifetime of your app. The way you do this is with a singleton class.

The `RequestQueue` must be instantiated in your Application as opposed to your Activity. This is so the queue does not get recreated when the activity is (such as when the device is rotated).

Here's the code for a singleton class:

```
public class MySingleton {
    private static MySingleton mInstance;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private static Context mCtx;

    private MySingleton(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();

        mImageLoader = new ImageLoader(mRequestQueue,
                new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap>
                    cache = new LruCache<String, Bitmap>(20);

            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }
        });
    }

    public static synchronized MySingleton getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new MySingleton(context);
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

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }
}
```

Using your singleton to access your application-wide queue:

```
// Get a RequestQueue
RequestQueue queue = MySingleton.getInstance(this.getApplicationContext()).
    getRequestQueue();

// ...

// Add a request (in this example, called stringRequest) to your RequestQueue.
MySingleton.getInstance(this).addToRequestQueue(stringRequest);
```

One more important aspect of Volley: **you don't have to specify for requests to be done asynchronously!** Volley handles all requests on a separate thread and deals with multi-threading for you, and returns all responses to the main thread.

## Assignment
Your homework is to create an application using Twitter's (RESTful API)[https://dev.twitter.com/rest/public/search]. Create a UI that allows the user to search for a given tweet by phrase, and displays 5 results. Allow the user to swipe right to dismiss these 5 results and bring up the next 5. If they swipe left, you should bring up the previous 5. If the user taps a tweet, open the tweet in the user's web browser or twitter client.

Instead of using local storage for the app you've made in previous classes, switch to using S3.