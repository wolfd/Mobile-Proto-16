package io.wolfd.mobileproto.lesson3.listeners;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import io.wolfd.mobileproto.lesson3.R;

/**
 * On click listener for each todo item.
 * Needs the context to create the EditText widget.
 */
public class TodoOnClickListener implements View.OnClickListener {
    private final Context context;

    public TodoOnClickListener(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        final TextView currentTodo = (TextView) v;
        final EditText input = new EditText(context);

        // set the current text to the current todo's current value
        // TODO: bug, I don't think this is working
        input.setText(currentTodo.getEditableText());

        // use the alert dialog builder to make the edit popup
        final AlertDialog editDialog = new AlertDialog.Builder(context)
                .setView(input)
                .setMessage(R.string.set_todo_item_text) // what should the popup say?
                .setCancelable(true) // you should be able to exit without saving the new value
                .setPositiveButton(R.string.set_todo_item_text_save_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String savedText = input.getText().toString(); // get the new value
                        currentTodo.setText(savedText); // save the new value
                    }
                })
                .create();

        // show the keyboard when you open the edit popup
        editDialog.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE
        );

        editDialog.show();
    }
}
