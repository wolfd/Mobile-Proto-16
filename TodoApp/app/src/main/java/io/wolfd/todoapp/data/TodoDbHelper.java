package io.wolfd.todoapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/*
Everything looks pretty solid here - nice work
 */

public class TodoDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Todo.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TodoContract.TodoEntry.TABLE_NAME + " (" +
                    TodoContract.TodoEntry._ID + " INTEGER PRIMARY KEY," +
                    TodoContract.TodoEntry.COLUMN_NAME_TEXT + TEXT_TYPE + COMMA_SEP +
                    TodoContract.TodoEntry.COLUMN_NAME_COMPLETED + TEXT_TYPE + " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TodoContract.TodoEntry.TABLE_NAME;

    public TodoDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES); // who needs 'em anyway?
        onCreate(db);
    }

    public ArrayList<Todo> getTodos() {
        final SQLiteDatabase db = getReadableDatabase();

        final String[] projection = {
                TodoContract.TodoEntry._ID,
                TodoContract.TodoEntry.COLUMN_NAME_TEXT,
                TodoContract.TodoEntry.COLUMN_NAME_COMPLETED
        };

        final String sortOrder =
                TodoContract.TodoEntry._ID + " DESC";

        final Cursor cursor = db.query(TodoContract.TodoEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        final ArrayList<Todo> todoItems = new ArrayList<>();

        // if there aren't any items
        if (!cursor.moveToFirst()) {
            cursor.close();
            db.close();
            return todoItems;
        }

        // loop through all the items in the database and create a Todo from them, and send it back.
        do {
            long todoId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(TodoContract.TodoEntry._ID)
            );

            String todoText = cursor.getString(
                    cursor.getColumnIndexOrThrow(TodoContract.TodoEntry.COLUMN_NAME_TEXT)
            );

            int todoCompleted = cursor.getInt(
                    cursor.getColumnIndexOrThrow(TodoContract.TodoEntry.COLUMN_NAME_COMPLETED)
            );

            todoItems.add(new Todo(
                    todoId,
                    todoText,
                    todoCompleted > 0
            ));

            cursor.moveToNext();
        } while (!cursor.isLast());

        cursor.close();
        db.close();

        return todoItems;
    }

    public Todo createTodo() {
        final SQLiteDatabase db = getWritableDatabase();
        final ContentValues values = new ContentValues();

        final String defaultText = "tap here to set";
        final int defaultCompleted = 0;

        values.put(TodoContract.TodoEntry.COLUMN_NAME_TEXT, defaultText);
        values.put(TodoContract.TodoEntry.COLUMN_NAME_COMPLETED, defaultCompleted);

        final long rowId = db.insert(TodoContract.TodoEntry.TABLE_NAME, null, values);

        db.close();

        return new Todo(rowId, defaultText, defaultCompleted > 0);
    }

    public void updateTodo(Todo todo) {
        SQLiteDatabase db = getWritableDatabase();

        // New value for both columns
        ContentValues values = new ContentValues();
        values.put(TodoContract.TodoEntry.COLUMN_NAME_TEXT, todo.getText());
        values.put(TodoContract.TodoEntry.COLUMN_NAME_COMPLETED, todo.isComplete());

        // Which row to update, based on the ID
        String selection = TodoContract.TodoEntry._ID + " = ?";
        String[] selectionArgs = { Long.toString(todo.getId()) };

        db.update(
                TodoContract.TodoEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );

        db.close();
    }

    public void deleteTodo(Todo todo) {
        SQLiteDatabase db = getWritableDatabase();

        // Which row to delete, based on the ID
        String selection = TodoContract.TodoEntry._ID + " = ?";
        String[] selectionArgs = { Long.toString(todo.getId()) };

        db.delete(
                TodoContract.TodoEntry.TABLE_NAME,
                selection,
                selectionArgs
        );

        db.close();
    }
}
