package io.wolfd.todoapp;

public class Todo {
    // Nice class! Dope.
    private String text;
    private boolean isComplete;

    public Todo(String text, boolean isComplete) {
        this.text = text;
        this.isComplete = isComplete;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    // Never used, but good thought including it now, it would be useful in the future to include a 'clear all' function
    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }
}
