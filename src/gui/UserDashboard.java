package gui;

import manager.EmergencyManager;
import model.User;

import javax.swing.*;
import java.awt.*;

public class UserDashboard extends JFrame {

    private EmergencyManager manager;
    private User user;

    public UserDashboard(
            EmergencyManager manager,
            User user) {

        this.manager = manager;
        this.user = user;

        setTitle(
                "User Dashboard - Emergency Response Management System"
        );

        setSize(700, 500);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);
        setResizable(false);

        createUI();
    }

    private void createUI() {

        JPanel mainPanel =
                new JPanel(new BorderLayout());

        mainPanel.setBackground(
                Color.decode("#E8EDDF")
        );

        // =========================================
        // HEADER
        // =========================================

        JPanel headerPanel =
                new JPanel(new BorderLayout());

        headerPanel.setBackground(
                Color.decode("#242423")
        );

        JLabel titleLabel =
                new JLabel(
                        "EMERGENCY RESPONSE SYSTEM"
                );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        22
                )
        );

        titleLabel.setForeground(
                Color.decode("#E8EDDF")
        );

        titleLabel.setBorder(
                BorderFactory.createEmptyBorder(
                        18, 20, 18, 10
                )
        );

        JLabel userLabel =
                new JLabel(
                        "Welcome, " + user.getName(),
                        SwingConstants.RIGHT
                );

        userLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        userLabel.setForeground(
                Color.decode("#F5CB5C")
        );

        userLabel.setBorder(
                BorderFactory.createEmptyBorder(
                        18, 10, 18, 20
                )
        );

        headerPanel.add(
                titleLabel,
                BorderLayout.WEST
        );

        headerPanel.add(
                userLabel,
                BorderLayout.EAST
        );

        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );

        // =========================================
        // CENTER BUTTONS
        // =========================================

        JPanel buttonPanel =
                new JPanel(
                        new GridLayout(
                                2, 2, 15, 15
                        )
                );

        buttonPanel.setBackground(
                Color.decode("#E8EDDF")
        );

        buttonPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        40, 60, 40, 60
                )
        );

        JButton reportButton =
                new JButton(
                        "REPORT EMERGENCY"
                );

        JButton myEmergenciesButton =
                new JButton(
                        "MY EMERGENCIES"
                );

        JButton profileButton =
                new JButton(
                        "MY PROFILE"
                );

        JButton logoutButton =
                new JButton(
                        "LOGOUT"
                );

        stylePrimaryButton(reportButton);
        stylePrimaryButton(myEmergenciesButton);
        stylePrimaryButton(profileButton);
        styleSecondaryButton(logoutButton);

        buttonPanel.add(reportButton);
        buttonPanel.add(myEmergenciesButton);
        buttonPanel.add(profileButton);
        buttonPanel.add(logoutButton);

        mainPanel.add(
                buttonPanel,
                BorderLayout.CENTER
        );

        // =========================================
        // ACTIONS
        // =========================================

        reportButton.addActionListener(
                e -> openReportEmergency()
        );

        myEmergenciesButton.addActionListener(
                e -> openMyEmergencies()
        );

        profileButton.addActionListener(
                e -> showProfile()
        );

        logoutButton.addActionListener(
                e -> logout()
        );

        add(mainPanel);
    }

    // =========================================
    // REPORT EMERGENCY
    // =========================================

    private void openReportEmergency() {

        ReportEmergencyFrame frame =
                new ReportEmergencyFrame(
                        manager,
                        user
                );

        frame.setVisible(true);
    }

    // =========================================
    // MY EMERGENCIES
    // =========================================

    private void openMyEmergencies() {

        MyEmergenciesFrame frame =
                new MyEmergenciesFrame(
                        manager,
                        user
                );

        frame.setVisible(true);
    }

    // =========================================
    // PROFILE
    // =========================================

    private void showProfile() {

        String profile =
                "User ID: "
                        + user.getId()
                        + "\n\n"

                        + "Name: "
                        + user.getName()
                        + "\n\n"

                        + "Phone: "
                        + user.getPhone()
                        + "\n\n"

                        + "Email: "
                        + user.getEmail()
                        + "\n\n"

                        + "Username: "
                        + user.getUsername();

        JOptionPane.showMessageDialog(
                this,
                profile,
                "My Profile",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // =========================================
    // LOGOUT
    // =========================================

    private void logout() {

        int choice =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to logout?",
                        "Logout",
                        JOptionPane.YES_NO_OPTION
                );

        if (choice ==
                JOptionPane.YES_OPTION) {

            dispose();

            LoginFrame loginFrame =
                    new LoginFrame(manager);

            loginFrame.setVisible(true);
        }
    }

    // =========================================
    // PRIMARY BUTTON
    // =========================================

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

        button.setFocusPainted(false);
    }

    // =========================================
    // SECONDARY BUTTON
    // =========================================

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

        button.setFocusPainted(false);
    }
}