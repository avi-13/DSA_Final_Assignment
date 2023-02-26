package Views;


import Controller.UserController;
import Model.task_screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

public class AddTaskScreen extends JFrame implements ActionListener {
    private JLabel jobIdLabel;
    private JComboBox jobIdComboBox;
    private JLabel task1Label;
    private JComboBox task1ComboBox;
    private JLabel task2Label;
    private JComboBox task2ComboBox;
    private JButton addTaskButton;
    private JButton backButton;
    public AddTaskScreen() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        jobIdLabel = new JLabel("Job ID:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(jobIdLabel, constraints);

        jobIdComboBox = new JComboBox();
        jobIdComboBox.addItem("Select Job ID");
        // Add job IDs to the combo box
        try {
            String username = "root";
            String password = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            // establishing connection in the connection object
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/dsaAssignment",
                    username,
                    password);
            PreparedStatement statement = connection.prepareStatement("SELECT jobId FROM job");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                jobIdComboBox.addItem(resultSet.getString("jobId"));
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        constraints.gridx = 1;
        constraints.gridy = 0;
        add(jobIdComboBox, constraints);

        task1Label = new JLabel("Task:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(task1Label, constraints);

        task1ComboBox = new JComboBox();
        task1ComboBox.addItem("Select Task");
        try {
            String username = "root";
            String password = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dsaAssignment", username, password);
            PreparedStatement statement = connection.prepareStatement("SELECT taskName FROM task");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                task1ComboBox.addItem(resultSet.getString("taskName"));
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        constraints.gridx = 1;
        constraints.gridy = 1;
        add(task1ComboBox, constraints);

        task2Label = new JLabel("Depends upon:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(task2Label, constraints);

        task2ComboBox = new JComboBox();
        task2ComboBox.addItem("Select Task");
        try {
            String username = "root";
            String password = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dsaassignment", username, password);
            PreparedStatement statement = connection.prepareStatement("SELECT taskName FROM task");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                task2ComboBox.addItem(resultSet.getString("taskName"));
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        constraints.gridx = 1;
        constraints.gridy = 2;
        add(task2ComboBox, constraints);


        addTaskButton = new JButton("Add Task");
        addTaskButton.addActionListener(this);
        constraints.gridx = 1;
        constraints.gridy = 3;
        add(addTaskButton, constraints);

        backButton = new JButton("Go Back");
        backButton.addActionListener(this);
        constraints.gridx = 0;
        constraints.gridy = 3;
        add(backButton, constraints);

        setTitle("Add Task Screen");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == addTaskButton) {
            String jobId = jobIdComboBox.getSelectedItem().toString();
            String task1 = task1ComboBox.getSelectedItem().toString();
            String task2 = task2ComboBox.getSelectedItem().toString();
            if (jobId.equals("Select Job ID") || task1.equals("Select Task") || task2.equals("Select Task")) {
                JOptionPane.showMessageDialog(this, "Please select a Job ID and Tasks");
            } else {
                // Create a hashmap to store task names and their corresponding node indices
                HashMap<String, Integer> taskMap = new HashMap<>();
                int index = 0;
                taskMap.put(task1, index++);
                taskMap.put(task2, index++);

                // Create a graph object
                Graph graph = new Graph(index);
                graph.addEdge(taskMap.get(task1), taskMap.get(task2));

                // Call the topological sort method
                try {
                    graph.topologicalSort();
                    UserController userController = new UserController();
                    task_screen taskScreen = new task_screen(Integer.parseInt(jobId), task1, task2);
                    int result = userController.taskScreenCustomerPreparedStatement(taskScreen);
                    if (result == 1) {
                        JOptionPane.showMessageDialog(this, "Task added successfully");
                    } else {
                        JOptionPane.showMessageDialog(this, "Error adding task");
                    }
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(this, "Error: The graph has a cycle!");
                }
            }
        } else if (event.getSource() == backButton) {
            Dashboard dashboard = new Dashboard();
            dashboard.setVisible(true);
            this.dispose();
        }
    }

}


