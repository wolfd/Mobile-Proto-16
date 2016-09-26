package io.wolfd.todoapp.listeners;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import io.wolfd.todoapp.R;
import io.wolfd.todoapp.data.Todo;
import io.wolfd.todoapp.data.TodoDbHelper;

/**
 * On click listener for each todo item.
 * Needs the context to create the EditText widget.
 */
public class TodoOnClickListener implements View.OnClickListener {
    private final Context context;
    private Todo todo;
    private final TodoDbHelper dbHelper;

    public TodoOnClickListener(Context context, Todo todo, TodoDbHelper dbHelper) {
        this.context = context;
        this.todo = todo;
        this.dbHelper = dbHelper;
    }

    @Override
    public void onClick(View v) {
        final EditText input = new EditText(context);

        // set the current text to the current todo's current value
        input.setText(todo.getText());

        // use the alert dialog builder to make the edit popup
        final AlertDialog editDialog = new AlertDialog.Builder(context)
                .setView(input)
                .setMessage(R.string.set_todo_item_text) // what should the popup say?
                .setCancelable(true) // you should be able to exit without saving the new value
                .setPositiveButton(R.string.set_todo_item_text_save_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String savedText = input.getText().toString(); // get the new value
                        todo.setText(savedText); // save the new value
                        dbHelper.updateTodo(todo); // persist the change
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
}
