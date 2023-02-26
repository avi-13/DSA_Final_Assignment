package Views;

import Controller.UserController;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen extends JFrame implements ActionListener {
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton signupButton;
    public static int USER_ID=0;
    public LoginScreen() {
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

        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(loginButton, constraints);

        signupButton = new JButton("Sign Up");
        signupButton.addActionListener(this);
        constraints.gridx = 1;
        constraints.gridy = 2;
        add(signupButton, constraints);

        setTitle("Login Screen");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == loginButton) {
// Validate the username and password fields
            if (usernameField.getText().trim().isEmpty() || passwordField.getPassword().length == 0) {
                JOptionPane.showMessageDialog(this, "Username or password cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }        UserController userController = new UserController();
            User user = userController.loginCustomerPreparedStatement(usernameField.getText().trim(), new String(passwordField.getPassword()));
            if (user != null) {
                JOptionPane.showMessageDialog(this, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                // Add code here to move to the next screen or display the desired information
                Dashboard dashboard = new Dashboard();
                dashboard.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Username or password is incorrect!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (event.getSource() == signupButton) {
            // Show the sign-up screen
            SignUpScreen signUpScreen = new SignUpScreen();
            signUpScreen.setVisible(true);
            dispose();
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginScreen screen = new LoginScreen();
            screen.setVisible(true);
        });
    }
}
