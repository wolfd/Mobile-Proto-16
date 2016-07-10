# Lesson 4 - Data Storage App Life Cycle

##  Data Storage
Everytime you close an app and reopen it, a new session of app is created. This means that any data that wasn't saved cannot be retrieved again. This is where persistent storage comes in. Android provides you with 5-6 options to choose from. While choosing an option, you should consider complexity of data being stored, privacy (if it can be read by the app only, the users or other apps on the device), and location (if it is local or on the cloud). We'll be mainly discussing Shared Preferences and SQLite databases. But here are many of the options available to Android:

1. [Shared Preferences](https://developer.android.com/guide/topics/data/data-storage.html#pref):

   Useful for writing small amount of data as key-value pairs.
2. [Firebase](https://www.firebase.com/docs/android/quickstart.html):

   A cool way to store data, run tests and analytics, authenticate, and monetize. The quickstart page linked here gives you a very good idea about how to use Firebase.
3. [Internal Storage](https://developer.android.com/guide/topics/data/data-storage.html#filesInternal):

   This memory chunk is private by default and is only available to the app. This data gets deleted when the app is uninstalled from the Android device. You can use methods like `openFileInput()`, `read()`, and `close()` on a [FileOutputStream](https://developer.android.com/reference/java/io/FileOutputStream.html)
4. [External Storage](https://developer.android.com/guide/topics/data/data-storage.html#filesExternal):

   The data stored here is world-readable but the data itself can't be relied upon as the user can unmount the storage anytime they want. Be sure to handle in the code the case where the data is unavailable.
   ```java
    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
    
    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
            Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }
   ```
5. [Cache](https://developer.android.com/reference/android/content/Context.html#getCacheDir()):

   If you have data that aren't important enough to be stored safely (i.e. if deleted, the user experience won't get affected), use cache. Remember that cache isn't reliable as it gets deleteled unreliably and you can't store large amounts or data (>1MB) without causing problems. Also, be responsible about how you use the cache memory. Clean the space you occupied after you are done.
6. [SQLite databases]():

   Think of SQL as a giant table of rows and columns where you can name the columns. You can also ask the table for certain values within those columns. You also have the ability to build complex queries that ask for multiple things within multiple columns. You can create multiple SQL databases for your app and all of them will be available to all parts of your app but won't be available to anything outside the app. SQL databases are also stored locally.

### Shared Preferences
Imagine an app which lets the users set the background color and the user sets it to red. The expected behaviour for the app should be to set the background color to red everytime the app opens even if the source code says the default color is white. It would be pretty bad if the user is made to set the color EVERY SINGLE TIME they close and reopen the app. Small things similar to this can be saved in Shared Preferences. Only condition is that the data being stored has to be of primitive type (like booleans, floats, ints, longs, and strings).

The way you store data is using key-value pairs. There are two ways to create/access a SharedPreferences file.

1. [`getSharedPreferences()`](https://developer.android.com/reference/android/content/Context.html#getSharedPreferences(java.lang.String, int)): You can create and access multiple shared preferences file. You can access it by calling the method with the name of the file using the activity.
    ```java
    SharedPreferences sharedPref = getActivity().getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE);
    ```

2. [`getPreferences()`](https://developer.android.com/reference/android/app/Activity.html#getPreferences(int)): Here you are pretty much doing the same thing except you don't need to mention the file name.
    ```java
    SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
    ```

So, whenever a session of the app gets created (inside `onCreate()`), you should read the shared preference file and when the app gets closed (inside `onStop()`), changes made (for ex. the backgroud color is changed to blue) should be written to the file.

```java
// To read
SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
// Default value for when the file is unavilable
String defaultValue = getResources().getString(R.string.saved_background_default);
String background = sharedPref.getString(getString(R.string.saved_background), defaultValue);

// To write
SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
SharedPreferences.Editor editor = sharedPref.edit();
editor.putString(getString(R.string.saved_background), currentBackground);
editor.commit();
```
While writing, notice that we gave it the key (`getString(R.string.saved_background)`) and the corresponding value (`currentBackground`).

### SQL

While implementing a SQLite database in Android, you almost always need to make a schema. The schema basically tells the database what columns are needed. This [page](https://developer.android.com/training/basics/data-storage/databases.html) has all the information you might need to add a SQLite database to your app. 

## App Life Cycle

![](https://developer.android.com/images/training/basics/basic-lifecycle.png)

When you create an new project in Android Studio, you will see a lot of boilerplate. If you are new to Android dev, you probably wouldn't have gone beyond using `onCreate()` and `onDestroy()`. But as you advance, you will realize the need for other methods. Other that the create and destroy methods, we'll use `onPause()` and `onResume`.

Read through each of the lessons listed in [this page](https://developer.android.com/training/basics/activity-lifecycle/index.html).

## Homework

1. Make a simple app that takes an RGB value from the user. The app sets the background color to that specified RGB value and also save the value in SharedPreferences. Use the correct methods of activity life cycle to figure out where to save and retrive those preferences. Test your app by pausing, resuming, quitting and restarting the app.
2. TODO: Wait for Bill to figure out the HW for lesson 3. It will be some form of "Add SQL storage to the shopping list app"
