package br.com.tasks;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks = new ArrayList<>();

    public void add(Task task) {
        if(task != null && task.getLength() <= 20) {
            this.tasks.add(task);
        } else {
            System.out.println("A tarefa é inválida");
        }
    }

    public void add(String description) {
        Task task = new Task(description);
        add(task);
    }

    public void destroy(int position) {
        if(position < this.tasks.size()) {
            this.tasks.remove(position);
        } else {
            System.out.println("A tarefa não existe");
        }
    }

    public void destroy(Task task) {
        this.tasks.remove(task);
    }

    public Task search(String description) {
        for (Task task: this.tasks) {
            if(description.equals(task.getDescription())) {
                return task;
            }
        }
        System.out.println("Tarefa não encontrada");
        return null;
    }

    public void showTasks() {
        for (Task task: this.tasks) {
            task.show();
        }
    }
}
