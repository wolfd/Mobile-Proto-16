package io.wolfd.todoapp;

import android.database.sqlite.SQLiteDatabase;
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
import io.wolfd.todoapp.data.Todo;
import io.wolfd.todoapp.data.TodoDbHelper;

/**
 * A placeholder fragment containing a simple view.
 */
public class TodoFragment extends Fragment {
    @BindView(R.id.fab) FloatingActionButton fab;

    private ArrayList<Todo> todoArrayList;

    public TodoFragment() {
        super();

        todoArrayList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todo, container, false);

        ButterKnife.bind(this, view);

        final TodoDbHelper dbHelper = new TodoDbHelper(getContext());

        // Load todos from database
        todoArrayList = dbHelper.getTodos();

        // Create the adapter to convert the array to views
        final TodosAdapter adapter = new TodosAdapter(getContext(), todoArrayList, dbHelper);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Todo newTodo = dbHelper.createTodo();
                adapter.add(newTodo);
            }
        });

        // Attach the adapter to a ListView
        ListView listView = (ListView) view.findViewById(R.id.todo_list_view);
        listView.setAdapter(adapter);

        return view;
    }
}
