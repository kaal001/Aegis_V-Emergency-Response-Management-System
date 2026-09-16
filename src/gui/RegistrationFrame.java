package gui;

import manager.EmergencyManager;
import model.User;

import javax.swing.*;
import java.awt.*;

public class RegistrationFrame extends JFrame {

    private EmergencyManager manager;

    private JTextField idField;
    private JTextField nameField;
    private JTextField phoneField;
    private JTextField emailField;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public RegistrationFrame(EmergencyManager manager) {

        this.manager = manager;

        setTitle("User Registration");
        setSize(500, 500);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLocationRelativeTo(null);
        setResizable(false);

        createRegistrationUI();
    }

    private void createRegistrationUI() {

        JPanel mainPanel =
                new JPanel(new BorderLayout());

        mainPanel.setBackground(
                Color.decode("#E8EDDF")
        );

        // =========================
        // Title
        // =========================

        JLabel titleLabel =
                new JLabel(
                        "USER REGISTRATION",
                        SwingConstants.CENTER
                );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        titleLabel.setForeground(
                Color.decode("#242423")
        );

        titleLabel.setBorder(
                BorderFactory.createEmptyBorder(
                        25, 10, 20, 10
                )
        );

        mainPanel.add(
                titleLabel,
                BorderLayout.NORTH
        );

        // =========================
        // Form
        // =========================

        JPanel formPanel =
                new JPanel(
                        new GridLayout(
                                6, 2, 10, 15
                        )
                );

        formPanel.setBackground(
                Color.decode("#CFDBD5")
        );

        formPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        25, 40, 25, 40
                )
        );

        idField = new JTextField();
        nameField = new JTextField();
        phoneField = new JTextField();
        emailField = new JTextField();
        usernameField = new JTextField();
        passwordField = new JPasswordField();

        formPanel.add(
                new JLabel("User ID:")
        );

        formPanel.add(
                idField
        );

        formPanel.add(
                new JLabel("Full Name:")
        );

        formPanel.add(
                nameField
        );

        formPanel.add(
                new JLabel("Phone:")
        );

        formPanel.add(
                phoneField
        );

        formPanel.add(
                new JLabel("Email:")
        );

        formPanel.add(
                emailField
        );

        formPanel.add(
                new JLabel("Username:")
        );

        formPanel.add(
                usernameField
        );

        formPanel.add(
                new JLabel("Password:")
        );

        formPanel.add(
                passwordField
        );

        mainPanel.add(
                formPanel,
                BorderLayout.CENTER
        );

        // =========================
        // Buttons
        // =========================

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                15,
                                15
                        )
                );

        buttonPanel.setBackground(
                Color.decode("#E8EDDF")
        );

        JButton registerButton =
                new JButton("REGISTER");

        JButton cancelButton =
                new JButton("CANCEL");

        registerButton.setBackground(
                Color.decode("#F5CB5C")
        );

        registerButton.setForeground(
                Color.decode("#242423")
        );

        registerButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        12
                )
        );

        registerButton.setFocusPainted(false);

        cancelButton.setBackground(
                Color.decode("#333533")
        );

        cancelButton.setForeground(
                Color.WHITE
        );

        cancelButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        12
                )
        );

        cancelButton.setFocusPainted(false);

        buttonPanel.add(
                registerButton
        );

        buttonPanel.add(
                cancelButton
        );

        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        // =========================
        // Button Actions
        // =========================

        registerButton.addActionListener(
                e -> registerUser()
        );

        cancelButton.addActionListener(
                e -> dispose()
        );

        add(mainPanel);
    }

    // =========================
    // Register User
    // =========================

    private void registerUser() {

        String id =
                idField.getText().trim();

        String name =
                nameField.getText().trim();

        String phone =
                phoneField.getText().trim();

        String email =
                emailField.getText().trim();

        String username =
                usernameField.getText().trim();

        String password =
                new String(
                        passwordField.getPassword()
                );

        // =========================
        // Empty Field Check
        // =========================

        if (id.isEmpty()
                || name.isEmpty()
                || phone.isEmpty()
                || email.isEmpty()
                || username.isEmpty()
                || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill in all fields.",
                    "Registration Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // =========================
        // Phone Validation
        // =========================

        if (!isValidPhone(phone)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Phone number must contain only digits "
                            + "and be between 7 and 15 digits.",
                    "Invalid Phone",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // =========================
        // Email Validation
        // =========================

        if (!isValidEmail(email)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid email address.",
                    "Invalid Email",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // =========================
        // Password Validation
        // =========================

        if (password.length() < 6) {

            JOptionPane.showMessageDialog(
                    this,
                    "Password must be at least 6 characters long.",
                    "Invalid Password",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // =========================
        // Duplicate User ID
        // =========================

        if (manager.findUserById(id) != null) {

            JOptionPane.showMessageDialog(
                    this,
                    "User ID already exists.",
                    "Registration Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        // =========================
        // Duplicate Username
        // =========================

        for (User user :
                manager.getUsers()) {

            if (user.getUsername()
                    .equalsIgnoreCase(username)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Username already exists.",
                        "Registration Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }
        }

        // =========================
        // Create User
        // =========================

        User user =
                new User(
                        id,
                        name,
                        phone,
                        email,
                        username,
                        password
                );

        manager.addUser(user);

        // =========================
        // Save User Data
        // =========================

        manager.saveData();

        JOptionPane.showMessageDialog(
                this,
                "Registration successful!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
        );

        dispose();
    }

    // =========================
    // Phone Validation
    // =========================

    private boolean isValidPhone(
            String phone) {

        if (phone.length() < 7
                || phone.length() > 15) {

            return false;
        }

        for (int i = 0;
             i < phone.length();
             i++) {

            if (!Character.isDigit(
                    phone.charAt(i)
            )) {

                return false;
            }
        }

        return true;
    }

    // =========================
    // Email Validation
    // =========================

    private boolean isValidEmail(
            String email) {

        int atPosition =
                email.indexOf("@");

        int dotPosition =
                email.lastIndexOf(".");

        if (atPosition <= 0) {
            return false;
        }

        if (dotPosition <= atPosition + 1) {
            return false;
        }

        if (dotPosition ==
                email.length() - 1) {

            return false;
        }

        return true;
    }
}