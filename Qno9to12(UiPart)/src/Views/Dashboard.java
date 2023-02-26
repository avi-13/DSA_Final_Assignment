package Views;

import Controller.UserController;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame implements ActionListener {
    private JButton addTaskButton;
    private JButton createJobButton;
    private JButton scheduleButton;
    private JButton addtasktojob;
    UserController userController;
    User user;
    private JLabel welcome;
    public Dashboard() {
        this.userController=new UserController();
        this.user=userController.fetchLoggedInCustomer();
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        welcome = new JLabel(("Welcome, " + user.getUsername()));
        welcome.setFont(new Font("Arial", Font.BOLD, 20));
        welcome.setHorizontalAlignment(JLabel.CENTER);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 4;
        add(welcome, constraints);

        addTaskButton = new JButton("Add Task");
        addTaskButton.addActionListener(this);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        add(addTaskButton, constraints);

        createJobButton = new JButton("Create Job");
        createJobButton.addActionListener(this);
        constraints.gridx = 1;
        constraints.gridy = 1;
        add(createJobButton, constraints);

        addtasktojob = new JButton("Add task to job");
        addtasktojob.addActionListener(this);
        constraints.gridx = 2;
        constraints.gridy = 1;
        add(addtasktojob, constraints);

        scheduleButton = new JButton("Schedule");
        scheduleButton.addActionListener(this);
        constraints.gridx = 3;
        constraints.gridy = 1;
        add(scheduleButton, constraints);

        setTitle("Dashboard");
        setSize(500, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == addTaskButton) {
            // Add Task logic
            AddTask addTask = new AddTask();
            addTask.setVisible(true);
            this.dispose();
        } else if (event.getSource() == createJobButton) {
            // Create Job logic
            // Add Task logic
            CreateJobScreen createJobScreen = new CreateJobScreen();
            createJobScreen.setVisible(true);
            this.dispose();

        }else if (event.getSource() == addtasktojob) {
            // Create Job logic
            // Add Task logic
            AddTaskScreen addTaskScreen = new AddTaskScreen();
            addTaskScreen.setVisible(true);
            this.dispose();
        }
        else if (event.getSource() == scheduleButton) {
            ScheduleJobScreen scheduleJobScreen=new ScheduleJobScreen();
            scheduleJobScreen.setVisible(true);
            this.dispose();
            // Schedule logic
        }
    }
}