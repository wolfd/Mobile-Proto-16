package io.wolfd.todoapp;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import io.wolfd.todoapp.listeners.TodoOnClickListener;

public class TodosAdapter extends ArrayAdapter<Todo> {
    public TodosAdapter(Context context, ArrayList<Todo> todos) {
        super(context, 0, todos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Todo todo = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_todo, parent, false);
        }
        // Lookup view for data population

        TextView todoText = (TextView) convertView.findViewById(R.id.todo_text);
        // Populate the data into the template view using the data object
        // There may be cases where getText will return null, although setText is resilient to this
        todoText.setText(todo.getText());

        // Get the linear layout that surrounds the text field (bigger tap area)
        View todoTextWrapper = convertView.findViewById(R.id.todo_text_wrapper);

        // Edit text listener
        todoTextWrapper.setOnClickListener(new TodoOnClickListener(getContext(), todo));

        // Nice extra functionality! I like it +1
        // remove item dialog
        todoTextWrapper.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // use the alert dialog builder to make the delete popup
                final AlertDialog editDialog = new AlertDialog.Builder(getContext())
                        .setMessage(R.string.delete_todo_item) // what should the popup say?
                        .setCancelable(true) // you should be able to exit without deleting
                        .setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                remove(todo);
                            }
                        })
                        .create();

                editDialog.show();
                return true;
            }
        });

        CheckBox todoCompleted = (CheckBox) convertView.findViewById(R.id.todo_checkbox_completed);

        // save the checked data in the object
        todoCompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = ((CheckBox) v).isChecked();

                if (isChecked) {
                    todo.setComplete(true);
                } else {
                    todo.setComplete(false);
                }
            }
        });

        // Return the completed view to render on screen
        return convertView;
    }
}