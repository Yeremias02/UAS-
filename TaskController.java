package controller;

import model.Task;
import java.io.*;
import java.util.*;

public class TaskController {
    private final String filePath = "data/tasks.txt";
    private List<Task> taskList = new ArrayList<>();

    public TaskController() {
        loadTasks();
    }

    public List<Task> getTasks() {
        return taskList;
    }

    public void addTask(String description) {
        int id = taskList.size() > 0 ? taskList.get(taskList.size() - 1).getId() + 1 : 1;
        taskList.add(new Task(id, description));
        saveTasks();
    }

    public void updateTask(int id, String newDescription) {
        for (Task task : taskList) {
            if (task.getId() == id) {
                task.setDescription(newDescription);
                break;
            }
        }
        saveTasks();
    }

    public void deleteTask(int id) {
        taskList.removeIf(t -> t.getId() == id);
        saveTasks();
    }

    private void loadTasks() {
        taskList.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                taskList.add(Task.fromString(line));
            }
        } catch (IOException e) {
            System.out.println("File not found. Starting with empty list.");
        }
    }

    private void saveTasks() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : taskList) {
                bw.write(task.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
