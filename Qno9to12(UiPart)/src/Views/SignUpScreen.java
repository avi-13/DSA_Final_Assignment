package Views;

import Controller.UserController;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpScreen extends JFrame implements ActionListener {
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel emailLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private JButton signupButton;
    private JButton backButton;

    public SignUpScreen() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        usernameLabel = new JLabel("Username:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(usernameLabel, constraints);

        usernameField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 0;
        add(usernameField, constraints);

        passwordLabel = new JLabel("Password:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(passwordLabel, constraints);

        passwordField = new JPasswordField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        add(passwordField, constraints);

        emailLabel = new JLabel("Email:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(emailLabel, constraints);

        emailField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 2;
        add(emailField, constraints);

        signupButton = new JButton("Sign Up");
        signupButton.addActionListener(this);
        constraints.gridx = 1;
        constraints.gridy = 3;
        add(signupButton, constraints);

        backButton = new JButton("Go Back");
        backButton.addActionListener(this);
        constraints.gridx = 0;
        constraints.gridy = 3;
        add(backButton, constraints);

        setTitle("SignUp Screen");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == signupButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String email = emailField.getText();

            if (!validateUsername(username)) {
                JOptionPane.showMessageDialog(this, "Invalid username. Please enter a valid username.");
            } else if (!validatePassword(password)) {
                JOptionPane.showMessageDialog(this, "Invalid password. Please enter a valid password.");
            } else if (!validateEmail(email)) {
                JOptionPane.showMessageDialog(this, "Invalid email. Please enter a valid email address.");
            } else {
                User user = new User(username, password, email);
                UserController userController = new UserController();
                int result = userController.registerCustomerPreparedStatement(user);
                if (result == 1) {
                    JOptionPane.showMessageDialog(this, "Sign up successful!");
                } else {
                    JOptionPane.showMessageDialog(this, "Sign up failed. Please try again later.");
                }
            }
        } else if (event.getSource() == backButton) {
            LoginScreen loginScreen = new LoginScreen();
            loginScreen.setVisible(true);
            dispose();
        }
    }


    private boolean validateUsername(String username) {
// validation logic for username
        return !username.isEmpty();
    }

    private boolean validatePassword(String password) {
// validation logic for password
        return password.length() >= 8;
    }

    private boolean validateEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }




}
