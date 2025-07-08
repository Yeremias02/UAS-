package model;

public class Task {
    private int id;
    private String description;

    public Task(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() { return id; }
    public String getDescription() { return description; }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return id + "," + description;
    }

    public static Task fromString(String line) {
        String[] parts = line.split(",", 2);
        return new Task(Integer.parseInt(parts[0]), parts[1]);
    }
}
