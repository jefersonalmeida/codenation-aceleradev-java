package br.com.tasks;

public class TaskArray {
    Task[] tasks;
    int count = 0;

    public TaskArray(int length) {
        this.tasks = new Task[length];
    }

    public void add(Task task) {
        this.tasks[count] = task;
        this.count++;
    }

    public void destroy(int position) {
        this.tasks[position] = null;
    }

    public void showTasks() {
        for (Task task : this.tasks) {
            task.show();
        }
    }
}
