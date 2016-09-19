package io.wolfd.mobileproto.lesson3.listeners;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import io.wolfd.mobileproto.lesson3.R;

/**
 * On click listener for each todo item.
 * Needs the context to create the EditText widget.
 * I like your custom Listener implementation. I would advise against statically holding a Context
 * reference because you can get it from the View that's passed into onClick (which guaranttes you
 * get the right reference).
 */
public class TodoOnClickListener implements View.OnClickListener {

    public TodoOnClickListener() {
    }

    @Override
    public void onClick(View v) {
        final TextView currentTodo = (TextView) v;
        final EditText input = new EditText(v.getContext());

        // set the current text to the current todo's current value
        // TODO: bug, I don't think this is working
        // Nope doesn't work. I like the idea though.

        // I really appreciate all the effort you put into commenting. You'll probably love to hear
        // this, but these comments are probably superfluous. You don't have to explain what each
        // function is doing for a built in Android view. Thanks thought!
        // use the alert dialog builder to make the edit popup
        final AlertDialog editDialog = new AlertDialog.Builder(v.getContext())
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

        // This works! I'm not sure entirely what your issue was. It was either something to do with
        // How you were passing around a "Context" reference which was kinda weird, or that you were
        // Reference getEditableText instead of getText().
        input.setText(currentTodo.getText().toString());

        // This is an example of a comment that's actually quite helpful. Thanks!
        // show the keyboard when you open the edit popup
        editDialog.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE
        );

        editDialog.show();
    }
}
