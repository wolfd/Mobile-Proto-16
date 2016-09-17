package io.wolfd.mobileproto.lesson3;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class TodoListActivity extends AppCompatActivity {
    private static final String PREFS_NAME = "TodoListAppSettings";
    private static final String BACKGROUND_COLOR_PREF = "backgroundColor";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todolist);

        // Restore preferences
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        // load the color preference from shared preferences
        int backgroundColor = settings.getInt(
                BACKGROUND_COLOR_PREF,
                Color.WHITE
        );

        // set the activity's root view color to the stored value
        getWindow().getDecorView().setBackgroundColor(backgroundColor);

        // Check that the activity is using the layout with
        // "fragment_container"
        if (findViewById(R.id.fragment_container) != null) {
            // If being restored, don't do anything
            if (savedInstanceState != null) {
                return;
            }

            Bundle todoListBundle = new Bundle();

            // make the TodoListFragment, because we are going to swap it out later, we don't
            // want it to be in the XML
            TodoListFragment todoListFragment = new TodoListFragment();
            todoListFragment.setArguments(todoListBundle);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, todoListFragment).commit();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // I'm going to leave the fab in for the next assignment
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Hi there says the action button", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, new SettingsFragment());
            transaction.addToBackStack(null);
            transaction.commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Set and save the background color setting
     * @param color
     */
    public void setBackgroundColor(int color) {
        getWindow().getDecorView().setBackgroundColor(color);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();

        editor.putInt(BACKGROUND_COLOR_PREF, color);

        editor.apply();
    }

    @Override
    protected void onStop(){
        super.onStop();
    }
}
