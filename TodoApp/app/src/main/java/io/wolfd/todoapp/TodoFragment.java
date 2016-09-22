package io.wolfd.todoapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class TodoFragment extends Fragment {
    @BindView(R.id.fab) FloatingActionButton fab;

    private ArrayList<Todo> todoArrayList;

    public TodoFragment() {
        super();

        todoArrayList = new ArrayList<>();
        // Construct the data source
        //TODO: load data from here
        todoArrayList.add(new Todo("Load data from SQL", false));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todo, container, false);

        ButterKnife.bind(this, view);

        // Create the adapter to convert the array to views
        final TodosAdapter adapter = new TodosAdapter(getContext(), todoArrayList);

        // Another thing you could do here for better usability is pop up a dialog to allow them to
        // immediately set the text, but that's not really a big issue
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.add(new Todo("tap here to set", false));
            }
        });

        // Attach the adapter to a ListView
        ListView listView = (ListView) view.findViewById(R.id.todo_list_view);
        listView.setAdapter(adapter);

        return view;
    }
}
