package gui;

import manager.EmergencyManager;
import model.Admin;
import model.User;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JPanel {

    private MainFrame mainFrame;
    private EmergencyManager manager;

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame(
            MainFrame mainFrame,
            EmergencyManager manager
    ) {

        this.mainFrame = mainFrame;
        this.manager = manager;

        createLoginUI();
    }

    // =====================================================
    // COMPATIBILITY CONSTRUCTOR
    // =====================================================

    public LoginFrame(
            EmergencyManager manager
    ) {

        this.manager = manager;

        MainFrame frame =
                new MainFrame();

        this.mainFrame = frame;

        createLoginUI();

        frame.addScreen(
                "LOGIN",
                this
        );

        frame.showScreen(
                "LOGIN"
        );

        frame.setVisible(
                true
        );
    }

    // =====================================================
    // CREATE LOGIN UI
    // =====================================================

    private void createLoginUI() {

        setLayout(
                new BorderLayout()
        );

        setBackground(
                Color.decode("#E8EDDF")
        );

        // =================================================
        // TITLE
        // =================================================

        JLabel titleLabel =
                new JLabel(
                        "EMERGENCY RESPONSE SYSTEM",
                        SwingConstants.CENTER
                );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        25
                )
        );

        titleLabel.setForeground(
                Color.decode("#242423")
        );

        titleLabel.setBorder(
                BorderFactory.createEmptyBorder(
                        28,
                        10,
                        12,
                        10
                )
        );

        add(
                titleLabel,
                BorderLayout.NORTH
        );

        // =================================================
        // LOGIN CARD
        // =================================================

        JPanel card =
                new JPanel(
                        new BorderLayout()
                );

        card.setBackground(
                Color.decode("#CFDBD5")
        );

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                Color.decode("#333533")
                        ),
                        BorderFactory.createEmptyBorder(
                                22,
                                28,
                                22,
                                28
                        )
                )
        );

        card.setPreferredSize(
                new Dimension(
                        540,
                        285
                )
        );

        // =================================================
        // FORM
        // =================================================

        JPanel formPanel =
                new JPanel(
                        new GridBagLayout()
                );

        formPanel.setBackground(
                Color.decode("#CFDBD5")
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        6,
                        6,
                        6,
                        6
                );

        // =================================================
        // USERNAME LABEL
        // =================================================

        JLabel usernameLabel =
                new JLabel(
                        "Username:"
                );

        usernameLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        usernameLabel.setForeground(
                Color.decode("#242423")
        );

        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.weightx = 0;

        gbc.fill =
                GridBagConstraints.NONE;

        gbc.anchor =
                GridBagConstraints.WEST;

        formPanel.add(
                usernameLabel,
                gbc
        );

        // =================================================
        // USERNAME FIELD
        // =================================================

        usernameField =
                new JTextField();

        usernameField.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        usernameField.setPreferredSize(
                new Dimension(
                        300,
                        36
                )
        );

        gbc.gridx = 1;
        gbc.gridy = 0;

        gbc.weightx = 1;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        formPanel.add(
                usernameField,
                gbc
        );

        // =================================================
        // PASSWORD LABEL
        // =================================================

        JLabel passwordLabel =
                new JLabel(
                        "Password:"
                );

        passwordLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        passwordLabel.setForeground(
                Color.decode("#242423")
        );

        gbc.gridx = 0;
        gbc.gridy = 1;

        gbc.weightx = 0;

        gbc.fill =
                GridBagConstraints.NONE;

        gbc.anchor =
                GridBagConstraints.WEST;

        formPanel.add(
                passwordLabel,
                gbc
        );

        // =================================================
        // PASSWORD FIELD
        // =================================================

        passwordField =
                new JPasswordField();

        passwordField.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        passwordField.setPreferredSize(
                new Dimension(
                        300,
                        36
                )
        );

        gbc.gridx = 1;
        gbc.gridy = 1;

        gbc.weightx = 1;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        formPanel.add(
                passwordField,
                gbc
        );

        // =================================================
        // USER LOGIN BUTTON
        // =================================================

        JButton userLoginButton =
                new JButton(
                        "USER LOGIN"
                );

        stylePrimaryButton(
                userLoginButton
        );

        gbc.gridx = 0;
        gbc.gridy = 2;

        gbc.weightx = 1;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        formPanel.add(
                userLoginButton,
                gbc
        );

        // =================================================
        // ADMIN LOGIN BUTTON
        // =================================================

        JButton adminLoginButton =
                new JButton(
                        "ADMIN LOGIN"
                );

        stylePrimaryButton(
                adminLoginButton
        );

        gbc.gridx = 1;
        gbc.gridy = 2;

        gbc.weightx = 1;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        formPanel.add(
                adminLoginButton,
                gbc
        );

        // =================================================
        // REGISTER BUTTON
        // =================================================

        JButton registerButton =
                new JButton(
                        "REGISTER"
                );

        styleSecondaryButton(
                registerButton
        );

        gbc.gridx = 0;
        gbc.gridy = 3;

        gbc.gridwidth = 2;

        gbc.weightx = 1;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        formPanel.add(
                registerButton,
                gbc
        );

        card.add(
                formPanel,
                BorderLayout.CENTER
        );

        // =================================================
        // CENTER CARD
        // =================================================

        JPanel centerPanel =
                new JPanel(
                        new GridBagLayout()
                );

        centerPanel.setBackground(
                Color.decode("#E8EDDF")
        );

        centerPanel.add(
                card
        );

        add(
                centerPanel,
                BorderLayout.CENTER
        );

        // =================================================
        // INFO TEXT
        // =================================================

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

        infoLabel.setBorder(
                BorderFactory.createEmptyBorder(
                        4,
                        10,
                        14,
                        10
                )
        );

        add(
                infoLabel,
                BorderLayout.SOUTH
        );

        // =================================================
        // ACTIONS
        // =================================================

        userLoginButton.addActionListener(
                e -> userLogin()
        );

        adminLoginButton.addActionListener(
                e -> adminLogin()
        );

        registerButton.addActionListener(
                e -> openRegistration()
        );

        passwordField.addActionListener(
                e -> userLogin()
        );
    }

    // =====================================================
    // USER LOGIN
    // =====================================================

    private void userLogin() {

        String username =
                usernameField
                        .getText()
                        .trim();

        String password =
                new String(
                        passwordField
                                .getPassword()
                );

        if (username.isEmpty()
                || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    mainFrame,
                    "Please enter username and password.",
                    "Login Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (manager.getUsers().isEmpty()) {

            JOptionPane.showMessageDialog(
                    mainFrame,
                    "No registered user was found.\n"
                            + "Please register a user first.",
                    "Login Failed",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        User loggedInUser = null;

        for (User user :
                manager.getUsers()) {

            String savedUsername =
                    user.getUsername()
                            .trim();

            String savedPassword =
                    user.getPassword();

            if (savedUsername
                    .equalsIgnoreCase(
                            username
                    )
                    &&
                    savedPassword
                            .equals(
                                    password
                            )) {

                loggedInUser = user;

                break;
            }
        }

        if (loggedInUser != null) {

            JOptionPane.showMessageDialog(
                    mainFrame,
                    "User login successful.",
                    "Login Successful",
                    JOptionPane.INFORMATION_MESSAGE
            );

            openUserDashboard(
                    loggedInUser
            );

        } else {

            JOptionPane.showMessageDialog(
                    mainFrame,
                    "Invalid user username or password.\n"
                            + "Please check your credentials "
                            + "or register first.",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // ADMIN LOGIN
    // =====================================================

    private void adminLogin() {

        String username =
                usernameField
                        .getText()
                        .trim();

        String password =
                new String(
                        passwordField
                                .getPassword()
                );

        if (username.isEmpty()
                || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    mainFrame,
                    "Please enter username and password.",
                    "Login Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (manager.getAdmins().isEmpty()) {

            JOptionPane.showMessageDialog(
                    mainFrame,
                    "No admin account is available.",
                    "Login Failed",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        Admin loggedInAdmin = null;

        for (Admin admin :
                manager.getAdmins()) {

            String savedUsername =
                    admin.getUsername()
                            .trim();

            String savedPassword =
                    admin.getPassword();

            if (savedUsername
                    .equalsIgnoreCase(
                            username
                    )
                    &&
                    savedPassword
                            .equals(
                                    password
                            )) {

                loggedInAdmin = admin;

                break;
            }
        }

        if (loggedInAdmin != null) {

            JOptionPane.showMessageDialog(
                    mainFrame,
                    "Admin login successful.",
                    "Login Successful",
                    JOptionPane.INFORMATION_MESSAGE
            );

            openAdminDashboard(
                    loggedInAdmin
            );

        } else {

            JOptionPane.showMessageDialog(
                    mainFrame,
                    "Invalid admin username or password.",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // USER DASHBOARD
    // =====================================================

    private void openUserDashboard(
            User user
    ) {

        UserDashboard dashboard =
                new UserDashboard(
                        mainFrame,
                        manager,
                        user
                );

        mainFrame.addScreen(
                "USER_DASHBOARD",
                dashboard
        );

        mainFrame.clearNavigationHistory();

        mainFrame.showScreen(
                "USER_DASHBOARD"
        );
    }

    // =====================================================
    // ADMIN DASHBOARD
    // =====================================================

    private void openAdminDashboard(
            Admin admin
    ) {

        AdminDashboard dashboard =
                new AdminDashboard(
                        mainFrame,
                        manager,
                        admin
                );

        mainFrame.addScreen(
                "ADMIN_DASHBOARD",
                dashboard
        );

        mainFrame.clearNavigationHistory();

        mainFrame.showScreen(
                "ADMIN_DASHBOARD"
        );
    }

    // =====================================================
    // REGISTRATION
    // =====================================================

    private void openRegistration() {

        RegistrationFrame registrationFrame =
                new RegistrationFrame(
                        manager
                );

        registrationFrame.setVisible(
                true
        );
    }

    // =====================================================
    // PRIMARY BUTTON
    // =====================================================

    private void stylePrimaryButton(
            JButton button) {

        button.setBackground(
                Color.decode("#F5CB5C")
        );

        button.setForeground(
                Color.decode("#242423")
        );

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        button.setFocusPainted(
                false
        );
    }

    // =====================================================
    // SECONDARY BUTTON
    // =====================================================

    private void styleSecondaryButton(
            JButton button) {

        button.setBackground(
                Color.decode("#333533")
        );

        button.setForeground(
                Color.WHITE
        );

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        button.setFocusPainted(
                false
        );
    }
}