package io.wolfd.mobileproto.lesson3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.wolfd.mobileproto.lesson3.listeners.TodoOnClickListener;

/**
 * TodoListFragment
 */
public class TodoListFragment extends Fragment {
    private TodoData todoData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setData(TodoData todoData) {
        this.todoData = todoData;
    }

    public TodoData getData() {
        return todoData;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_todolist, container, false);

        List<TextView> todoList = new ArrayList<>();

        todoList.add((TextView) view.findViewById(R.id.textView));
        todoList.add((TextView) view.findViewById(R.id.textView2));
        todoList.add((TextView) view.findViewById(R.id.textView3));
        todoList.add((TextView) view.findViewById(R.id.textView4));
        todoList.add((TextView) view.findViewById(R.id.textView5));

        // set the onclick listener for each of the textviews, pass the current context to each
        // Yayyy for-each so pretty.
        for (TextView todo : todoList) {
            todo.setOnClickListener(new TodoOnClickListener());
        }

        return view;
    }
}
