package io.wolfd.todoapp.data;

public class Todo {
    private long id;
    private String text;
    private boolean isComplete;

    public Todo(long id, String text, boolean isComplete) {
        this.id = id;
        this.text = text;
        this.isComplete = isComplete;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }
}
