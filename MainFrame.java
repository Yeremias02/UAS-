package view;

import controller.TaskController;
import model.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {
    private TaskController controller = new TaskController();
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private JList<String> taskList = new JList<>(listModel);
    private JTextField taskField = new JTextField();

    public MainFrame() {
        setTitle("To-Do List");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        refreshList();

        add(new JScrollPane(taskList), BorderLayout.CENTER);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(taskField, BorderLayout.CENTER);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            if (!taskField.getText().trim().isEmpty()) {
                controller.addTask(taskField.getText().trim());
                taskField.setText("");
                refreshList();
            }
        });

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(e -> {
            int index = taskList.getSelectedIndex();
            if (index >= 0) {
                int id = controller.getTasks().get(index).getId();
                controller.updateTask(id, taskField.getText().trim());
                refreshList();
            }
        });

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
            int index = taskList.getSelectedIndex();
            if (index >= 0) {
                int id = controller.getTasks().get(index).getId();
                controller.deleteTask(id);
                refreshList();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);
        add(panel, BorderLayout.SOUTH);
    }

    private void refreshList() {
        listModel.clear();
        for (Task task : controller.getTasks()) {
            listModel.addElement(task.getDescription());
        }
    }
}
