package Views;

import Controller.UserController;
import Model.Task;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTask extends JFrame implements ActionListener {
    private JLabel taskIdLabel;
    private JLabel taskNameLabel;
    private JTextField taskIdField;
    private JTextField taskNameField;
    private JButton addTaskButton;
    private JButton goBackButton;

    UserController userController;
    User user;

    public AddTask() {
        this.userController=new UserController();
        this.user=userController.fetchLoggedInCustomer();
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        taskIdLabel = new JLabel("Task ID:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(taskIdLabel, constraints);

        taskIdField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 0;
        add(taskIdField, constraints);

        taskNameLabel = new JLabel("Task Name:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(taskNameLabel, constraints);

        taskNameField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        add(taskNameField, constraints);

        addTaskButton = new JButton("Add Task");
        addTaskButton.addActionListener(this);
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(addTaskButton, constraints);

        goBackButton = new JButton("Go Back");
        goBackButton.addActionListener(this);
        constraints.gridx = 1;
        constraints.gridy = 2;
        add(goBackButton, constraints);

        setTitle("Add Task");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == addTaskButton) {
            int taskId = Integer.parseInt(taskIdField.getText());
            String taskName = taskNameField.getText();
            Task task = new Task(taskId, taskName);
            UserController userController1 = new UserController();
            int result = userController1.taskCustomerPreparedStatement(task);
            if (result == 1) {
                JOptionPane.showMessageDialog(this, "Task added successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Task not added. Try again!");
            }
        } else if (event.getSource() == goBackButton) {
// Go back to Dashboard logic
            Dashboard dashboard = new Dashboard();
            dashboard.setVisible(true);
            this.dispose();
        }
    }

}