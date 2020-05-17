package br.com.tasks;

public class Task {

    private final String description;

    public String getDescription() {
        return description;
    }

    public int getLength() {
        return description.length();
    }

    public Task(String description) {
        this.description = description;
    }

    public void show() {
        System.out.println(description);
    }
}
