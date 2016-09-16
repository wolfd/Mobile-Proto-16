package io.wolfd.mobileproto.lesson3.listeners;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import io.wolfd.mobileproto.lesson3.R;

public class TodoOnClickListener implements View.OnClickListener {
    private final Context context;

    public TodoOnClickListener(Context context) {
        this.context = context;
    }


    @Override
    public void onClick(View v) {
        final TextView currentTodo = (TextView) v;
        final EditText input = new EditText(context);

        input.setText(currentTodo.getEditableText());

        final AlertDialog editDialog = new AlertDialog.Builder(context)
                .setView(input)
                .setMessage(R.string.set_todo_item_text)
                .setCancelable(true)
                .setPositiveButton(R.string.set_todo_item_text_save_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String savedText = input.getText().toString();
                        currentTodo.setText(savedText);
                    }
                })
                .create();

        editDialog.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE
        );

        editDialog.show();
    }
}
