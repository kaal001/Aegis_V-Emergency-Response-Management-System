package gui;

import manager.EmergencyManager;

import javax.swing.*;
import java.awt.*;

public class ManagementTabbedPanel extends JPanel {

    private MainFrame mainFrame;
    private EmergencyManager manager;

    private JTabbedPane tabbedPane;

    public ManagementTabbedPanel(
            MainFrame mainFrame,
            EmergencyManager manager) {

        this.mainFrame = mainFrame;
        this.manager = manager;

        createUI();
    }

    // =========================================
    // CREATE UI
    // =========================================

    private void createUI() {

        setLayout(new BorderLayout());

        setBackground(
                Color.decode("#E8EDDF")
        );

        // =========================================
        // HEADER
        // =========================================

        JPanel headerPanel =
                new JPanel(
                        new BorderLayout()
                );

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
                        18,
                        25,
                        18,
                        10
                )
        );

        JLabel sectionLabel =
                new JLabel(
                        "MANAGEMENT"
                );

        sectionLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        sectionLabel.setForeground(
                Color.decode("#F5CB5C")
        );

        sectionLabel.setBorder(
                BorderFactory.createEmptyBorder(
                        18,
                        10,
                        18,
                        25
                )
        );

        headerPanel.add(
                titleLabel,
                BorderLayout.WEST
        );

        headerPanel.add(
                sectionLabel,
                BorderLayout.EAST
        );

        add(
                headerPanel,
                BorderLayout.NORTH
        );

        // =========================================
        // MAIN AREA
        // =========================================

        JPanel mainArea =
                new JPanel(
                        new BorderLayout()
                );

        mainArea.setBackground(
                Color.decode("#E8EDDF")
        );

        // =========================================
        // LEFT SIDEBAR
        // =========================================

        JPanel sidebarPanel =
                new JPanel();

        sidebarPanel.setLayout(
                new BoxLayout(
                        sidebarPanel,
                        BoxLayout.Y_AXIS
                )
        );

        sidebarPanel.setBackground(
                Color.decode("#333533")
        );

        sidebarPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        10,
                        20,
                        10
                )
        );

        sidebarPanel.setPreferredSize(
                new Dimension(
                        190,
                        0
                )
        );

        JLabel menuTitle =
                new JLabel(
                        "ADMIN MENU"
                );

        menuTitle.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        menuTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        menuTitle.setForeground(
                Color.decode("#F5CB5C")
        );

        menuTitle.setBorder(
                BorderFactory.createEmptyBorder(
                        0,
                        0,
                        20,
                        0
                )
        );

        sidebarPanel.add(
                menuTitle
        );

        // =========================================
        // DASHBOARD
        // =========================================

        JButton dashboardButton =
                createSidebarButton(
                        "DASHBOARD"
                );

        dashboardButton.addActionListener(
                e -> goToDashboard()
        );

        sidebarPanel.add(
                dashboardButton
        );

        sidebarPanel.add(
                Box.createVerticalStrut(10)
        );

        // =========================================
        // MANAGEMENT
        // =========================================

        JButton managementButton =
                createSidebarButton(
                        "MANAGEMENT"
                );

        managementButton.setBackground(
                Color.decode("#CFDBD5")
        );

        managementButton.addActionListener(
                e -> {
                    // Already on management screen.
                    refreshCurrentTab();
                }
        );

        sidebarPanel.add(
                managementButton
        );

        sidebarPanel.add(
                Box.createVerticalStrut(10)
        );

        // =========================================
        // HISTORY
        // =========================================

        JButton historyButton =
                createSidebarButton(
                        "HISTORY"
                );

        historyButton.addActionListener(
                e -> openHistory()
        );

        sidebarPanel.add(
                historyButton
        );

        sidebarPanel.add(
                Box.createVerticalStrut(10)
        );

        // =========================================
        // REPORTS
        // =========================================

        JButton reportsButton =
                createSidebarButton(
                        "REPORTS"
                );

        reportsButton.addActionListener(
                e -> openReports()
        );

        sidebarPanel.add(
                reportsButton
        );

        // Push logout to bottom
        sidebarPanel.add(
                Box.createVerticalGlue()
        );

        // =========================================
        // LOGOUT
        // =========================================

        JButton logoutButton =
                createSidebarLogoutButton(
                        "LOGOUT"
                );

        logoutButton.addActionListener(
                e -> logout()
        );

        sidebarPanel.add(
                logoutButton
        );

        mainArea.add(
                sidebarPanel,
                BorderLayout.WEST
        );

        // =========================================
        // RIGHT CONTENT AREA
        // =========================================

        JPanel contentPanel =
                new JPanel(
                        new BorderLayout(
                                12,
                                12
                        )
                );

        contentPanel.setBackground(
                Color.decode("#E8EDDF")
        );

        contentPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        20,
                        20,
                        25
                )
        );

        // =========================================
        // CONTENT HEADER
        // =========================================

        JPanel contentHeader =
                new JPanel(
                        new BorderLayout()
                );

        contentHeader.setBackground(
                Color.decode("#E8EDDF")
        );

        JLabel contentTitle =
                new JLabel(
                        "MANAGEMENT"
                );

        contentTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        contentTitle.setForeground(
                Color.decode("#242423")
        );

        // Refresh button in panel corner
        JButton refreshButton =
                new JButton(
                        "REFRESH"
                );

        styleRefreshButton(
                refreshButton
        );

        refreshButton.addActionListener(
                e -> refreshCurrentTab()
        );

        contentHeader.add(
                contentTitle,
                BorderLayout.WEST
        );

        contentHeader.add(
                refreshButton,
                BorderLayout.EAST
        );

        contentPanel.add(
                contentHeader,
                BorderLayout.NORTH
        );

        // =========================================
        // TABBED PANE
        // =========================================

        tabbedPane =
                new JTabbedPane();

        tabbedPane.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        12
                )
        );

        /*
         * Emergency Management
         */
        EmergencyManagementFrame emergencyPanel =
                new EmergencyManagementFrame(
                        mainFrame,
                        manager
                );

        /*
         * Response Team Management
         */
        ResponseTeamManagementFrame teamPanel =
                new ResponseTeamManagementFrame(
                        mainFrame,
                        manager
                );

        /*
         * User Management
         */
        UserManagementFrame userPanel =
                new UserManagementFrame(
                        mainFrame,
                        manager
                );

        /*
         * Assignment Management
         */
        TeamAssignmentFrame assignmentPanel =
                new TeamAssignmentFrame(
                        mainFrame,
                        manager
                );

        // =========================================
        // ADD TABS
        // =========================================

        tabbedPane.addTab(
                "Emergencies",
                emergencyPanel
        );

        tabbedPane.addTab(
                "Response Teams",
                teamPanel
        );

        tabbedPane.addTab(
                "Users",
                userPanel
        );

        tabbedPane.addTab(
                "Assignments",
                assignmentPanel
        );

        contentPanel.add(
                tabbedPane,
                BorderLayout.CENTER
        );

        mainArea.add(
                contentPanel,
                BorderLayout.CENTER
        );

        add(
                mainArea,
                BorderLayout.CENTER
        );
    }

    // =========================================
    // REFRESH CURRENT TAB
    // =========================================

    private void refreshCurrentTab() {

        int selectedIndex =
                tabbedPane.getSelectedIndex();

        if (selectedIndex == 0) {

            /*
             * Recreate emergency panel
             */
            EmergencyManagementFrame emergencyPanel =
                    new EmergencyManagementFrame(
                            mainFrame,
                            manager
                    );

            tabbedPane.setComponentAt(
                    0,
                    emergencyPanel
            );

        } else if (selectedIndex == 1) {

            /*
             * Recreate team panel
             */
            ResponseTeamManagementFrame teamPanel =
                    new ResponseTeamManagementFrame(
                            mainFrame,
                            manager
                    );

            tabbedPane.setComponentAt(
                    1,
                    teamPanel
            );

        } else if (selectedIndex == 2) {

            /*
             * Recreate user panel
             */
            UserManagementFrame userPanel =
                    new UserManagementFrame(
                            mainFrame,
                            manager
                    );

            tabbedPane.setComponentAt(
                    2,
                    userPanel
            );

        } else if (selectedIndex == 3) {

            /*
             * Recreate assignment panel
             */
            TeamAssignmentFrame assignmentPanel =
                    new TeamAssignmentFrame(
                            mainFrame,
                            manager
                    );

            tabbedPane.setComponentAt(
                    3,
                    assignmentPanel
            );
        }

        tabbedPane.revalidate();
        tabbedPane.repaint();
    }

    // =========================================
    // DASHBOARD
    // =========================================

    private void goToDashboard() {

        mainFrame.goBack();
    }

    // =========================================
    // HISTORY
    // =========================================

    private void openHistory() {

        EmergencyHistoryFrame historyFrame =
                new EmergencyHistoryFrame(
                        mainFrame,
                        manager
                );

        mainFrame.addScreen(
                "EMERGENCY_HISTORY",
                historyFrame
        );

        mainFrame.showScreen(
                "EMERGENCY_HISTORY"
        );
    }

    // =========================================
    // REPORTS
    // =========================================

    private void openReports() {

        ReportStatisticsFrame reportsFrame =
                new ReportStatisticsFrame(
                        mainFrame,
                        manager
                );

        mainFrame.addScreen(
                "REPORT_STATISTICS",
                reportsFrame
        );

        mainFrame.showScreen(
                "REPORT_STATISTICS"
        );
    }

    // =========================================
    // SIDEBAR BUTTON
    // =========================================

    private JButton createSidebarButton(
            String text) {

        JButton button =
                new JButton(
                        text
                );

        button.setMaximumSize(
                new Dimension(
                        170,
                        42
                )
        );

        button.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

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
                        12
                )
        );

        button.setFocusPainted(
                false
        );

        button.setBorder(
                BorderFactory.createEmptyBorder(
                        8,
                        10,
                        8,
                        10
                )
        );

        return button;
    }

    // =========================================
    // LOGOUT BUTTON
    // =========================================

    private JButton createSidebarLogoutButton(
            String text) {

        JButton button =
                new JButton(
                        text
                );

        button.setMaximumSize(
                new Dimension(
                        170,
                        42
                )
        );

        button.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        button.setBackground(
                Color.decode("#242423")
        );

        button.setForeground(
                Color.WHITE
        );

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        12
                )
        );

        button.setFocusPainted(
                false
        );

        button.setBorder(
                BorderFactory.createEmptyBorder(
                        8,
                        10,
                        8,
                        10
                )
        );

        return button;
    }

    // =========================================
    // REFRESH BUTTON
    // =========================================

    private void styleRefreshButton(
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
                        11
                )
        );

        button.setFocusPainted(
                false
        );

        button.setBorder(
                BorderFactory.createEmptyBorder(
                        8,
                        14,
                        8,
                        14
                )
        );
    }

    // =========================================
    // LOGOUT
    // =========================================

    private void logout() {

        int choice =
                JOptionPane.showConfirmDialog(
                        mainFrame,
                        "Are you sure you want to logout?",
                        "Logout",
                        JOptionPane.YES_NO_OPTION
                );

        if (choice ==
                JOptionPane.YES_OPTION) {

            mainFrame.clearNavigationHistory();

            mainFrame.showScreen(
                    "LOGIN"
            );
        }
    }
}