package io.wolfd.todoapp.data;

import android.provider.BaseColumns;

public final class TodoContract {
    private TodoContract() {}

    public static class TodoEntry implements BaseColumns {
        public static final String TABLE_NAME = "todo";
        public static final String COLUMN_NAME_TEXT = "todo_text";
        public static final String COLUMN_NAME_COMPLETED = "completed";
    }



}
