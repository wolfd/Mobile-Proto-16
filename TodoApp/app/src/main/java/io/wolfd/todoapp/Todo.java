package io.wolfd.todoapp;

public class Todo {
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

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }
}
