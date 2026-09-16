package gui;

import manager.EmergencyManager;
import model.Admin;
import model.User;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private EmergencyManager manager;

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame(EmergencyManager manager) {

        this.manager = manager;

        setTitle(
                "Emergency Response Management System"
        );

        setSize(500, 400);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);
        setResizable(false);

        createLoginUI();
    }

    private void createLoginUI() {

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
                        "EMERGENCY RESPONSE SYSTEM",
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
                        30, 10, 20, 10
                )
        );

        mainPanel.add(
                titleLabel,
                BorderLayout.NORTH
        );

        // =========================
        // Login Form
        // =========================

        JPanel formPanel =
                new JPanel(
                        new GridLayout(
                                5, 2, 10, 15
                        )
                );

        formPanel.setBackground(
                Color.decode("#CFDBD5")
        );

        formPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        25, 50, 25, 50
                )
        );

        JLabel usernameLabel =
                new JLabel("Username:");

        JLabel passwordLabel =
                new JLabel("Password:");

        usernameField =
                new JTextField();

        passwordField =
                new JPasswordField();

        JButton userLoginButton =
                new JButton("USER LOGIN");

        JButton adminLoginButton =
                new JButton("ADMIN LOGIN");

        JButton registerButton =
                new JButton("REGISTER");

        // =========================
        // Button Styling
        // =========================

        userLoginButton.setBackground(
                Color.decode("#F5CB5C")
        );

        adminLoginButton.setBackground(
                Color.decode("#F5CB5C")
        );

        registerButton.setBackground(
                Color.decode("#333533")
        );

        userLoginButton.setForeground(
                Color.decode("#242423")
        );

        adminLoginButton.setForeground(
                Color.decode("#242423")
        );

        registerButton.setForeground(
                Color.WHITE
        );

        userLoginButton.setFocusPainted(false);
        adminLoginButton.setFocusPainted(false);
        registerButton.setFocusPainted(false);

        // =========================
        // Add Components
        // =========================

        formPanel.add(usernameLabel);
        formPanel.add(usernameField);

        formPanel.add(passwordLabel);
        formPanel.add(passwordField);

        formPanel.add(userLoginButton);
        formPanel.add(adminLoginButton);

        formPanel.add(registerButton);
        formPanel.add(new JLabel(""));

        JLabel infoLabel =
                new JLabel(
                        "Register first if you do not have an account.",
                        SwingConstants.CENTER
                );

        infoLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        11
                )
        );

        infoLabel.setForeground(
                Color.decode("#333533")
        );

        formPanel.add(new JLabel(""));
        formPanel.add(infoLabel);

        mainPanel.add(
                formPanel,
                BorderLayout.CENTER
        );

        // =========================
        // Footer
        // =========================

        JLabel footerLabel =
                new JLabel(
                        "Aegis_V",
                        SwingConstants.CENTER
                );

        footerLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        footerLabel.setForeground(
                Color.decode("#333533")
        );

        footerLabel.setBorder(
                BorderFactory.createEmptyBorder(
                        10, 10, 15, 10
                )
        );

        mainPanel.add(
                footerLabel,
                BorderLayout.SOUTH
        );

        // =========================
        // Button Actions
        // =========================

        userLoginButton.addActionListener(
                e -> userLogin()
        );

        adminLoginButton.addActionListener(
                e -> adminLogin()
        );

        registerButton.addActionListener(
                e -> openRegistration()
        );

        add(mainPanel);
    }

    // =========================
    // User Login
    // =========================

    private void userLogin() {

        String username =
                usernameField.getText().trim();

        String password =
                new String(
                        passwordField.getPassword()
                );

        if (username.isEmpty()
                || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter username and password.",
                    "Login Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (manager.getUsers().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "No registered user was found.\n"
                            + "Please register a user first.",
                    "Login Failed",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        User loggedInUser = null;

        for (User user : manager.getUsers()) {

            String savedUsername =
                    user.getUsername().trim();

            String savedPassword =
                    user.getPassword();

            if (savedUsername.equalsIgnoreCase(username)
                    && savedPassword.equals(password)) {

                loggedInUser = user;
                break;
            }
        }

        if (loggedInUser != null) {

            JOptionPane.showMessageDialog(
                    this,
                    "User login successful.",
                    "Login Successful",
                    JOptionPane.INFORMATION_MESSAGE
            );

            dispose();

            UserDashboard dashboard =
                    new UserDashboard(
                            manager,
                            loggedInUser
                    );

            dashboard.setVisible(true);

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid user username or password.\n"
                            + "Please check your credentials "
                            + "or register first.",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================
    // Admin Login
    // =========================

    private void adminLogin() {

        String username =
                usernameField.getText().trim();

        String password =
                new String(
                        passwordField.getPassword()
                );

        if (username.isEmpty()
                || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter username and password.",
                    "Login Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (manager.getAdmins().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "No admin account is available.",
                    "Login Failed",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        Admin loggedInAdmin = null;

        for (Admin admin : manager.getAdmins()) {

            String savedUsername =
                    admin.getUsername().trim();

            String savedPassword =
                    admin.getPassword();

            if (savedUsername.equalsIgnoreCase(username)
                    && savedPassword.equals(password)) {

                loggedInAdmin = admin;
                break;
            }
        }

        if (loggedInAdmin != null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Admin login successful.",
                    "Login Successful",
                    JOptionPane.INFORMATION_MESSAGE
            );

            dispose();

            AdminDashboard dashboard =
                    new AdminDashboard(
                            manager,
                            loggedInAdmin
                    );

            dashboard.setVisible(true);

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid admin username or password.",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================
    // Registration
    // =========================

    private void openRegistration() {

        RegistrationFrame registrationFrame =
                new RegistrationFrame(manager);

        registrationFrame.setVisible(true);
    }
}