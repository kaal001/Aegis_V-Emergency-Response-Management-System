package gui;

import enums.EmergencyStatus;
import enums.EmergencyType;
import enums.Priority;
import manager.EmergencyManager;
import model.Admin;
import model.Emergency;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class AdminDashboard extends JPanel {

    private MainFrame mainFrame;
    private EmergencyManager manager;
    private Admin admin;

    private JLabel totalLabel;
    private JLabel pendingLabel;
    private JLabel criticalLabel;
    private JLabel inProgressLabel;
    private JLabel resolvedLabel;
    private JLabel unassignedLabel;
    private JLabel availableTeamLabel;
    private JLabel busyTeamLabel;

    private DefaultTableModel tableModel;
    private JTable emergencyTable;

    public AdminDashboard(
            MainFrame mainFrame,
            EmergencyManager manager,
            Admin admin) {

        this.mainFrame = mainFrame;
        this.manager = manager;
        this.admin = admin;

        createDashboardUI();
        refreshDashboard();
    }

    // =========================================
    // CREATE DASHBOARD UI
    // =========================================

    private void createDashboardUI() {

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

        // =========================================
        // HEADER RIGHT SIDE
        // =========================================

        JPanel headerRightPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                12,
                                12
                        )
                );

        headerRightPanel.setOpaque(false);

        JButton refreshButton =
                new JButton("REFRESH");

        styleRefreshButton(refreshButton);

        JLabel adminLabel =
                new JLabel(
                        "Admin: " + admin.getName()
                );

        adminLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        adminLabel.setForeground(
                Color.decode("#F5CB5C")
        );

        refreshButton.addActionListener(
                e -> refreshDashboard()
        );

        headerRightPanel.add(
                refreshButton
        );

        headerRightPanel.add(
                adminLabel
        );

        headerPanel.add(
                titleLabel,
                BorderLayout.WEST
        );

        headerPanel.add(
                headerRightPanel,
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

        // Sidebar title
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
        // DASHBOARD BUTTON
        // =========================================

        JButton dashboardButton =
                createSidebarButton(
                        "DASHBOARD"
                );

        dashboardButton.addActionListener(
                e -> refreshDashboard()
        );

        sidebarPanel.add(
                dashboardButton
        );

        sidebarPanel.add(
                Box.createVerticalStrut(10)
        );

        // =========================================
        // MANAGEMENT BUTTON
        // =========================================

        JButton managementButton =
                createSidebarButton(
                        "MANAGEMENT"
                );

        managementButton.addActionListener(
                e -> openManagement()
        );

        sidebarPanel.add(
                managementButton
        );

        sidebarPanel.add(
                Box.createVerticalStrut(10)
        );

        // =========================================
        // HISTORY BUTTON
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
        // REPORTS BUTTON
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
        // LOGOUT BUTTON
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
                                15,
                                15
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
        // CONTENT TITLE
        // =========================================

        JLabel dashboardTitle =
                new JLabel(
                        "ADMIN DASHBOARD"
                );

        dashboardTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        dashboardTitle.setForeground(
                Color.decode("#242423")
        );

        dashboardTitle.setBorder(
                BorderFactory.createEmptyBorder(
                        0,
                        0,
                        8,
                        0
                )
        );

        contentPanel.add(
                dashboardTitle,
                BorderLayout.NORTH
        );

        // =========================================
        // CENTER CONTENT
        // =========================================

        JPanel centerContent =
                new JPanel(
                        new BorderLayout(
                                15,
                                15
                        )
                );

        centerContent.setBackground(
                Color.decode("#E8EDDF")
        );

        // =========================================
        // STATISTICS
        // =========================================

        JPanel statisticsPanel =
                new JPanel(
                        new GridLayout(
                                2,
                                4,
                                12,
                                12
                        )
                );

        statisticsPanel.setBackground(
                Color.decode("#E8EDDF")
        );

        totalLabel =
                createStatLabel("0");

        pendingLabel =
                createStatLabel("0");

        criticalLabel =
                createStatLabel("0");

        inProgressLabel =
                createStatLabel("0");

        resolvedLabel =
                createStatLabel("0");

        unassignedLabel =
                createStatLabel("0");

        availableTeamLabel =
                createStatLabel("0");

        busyTeamLabel =
                createStatLabel("0");

        statisticsPanel.add(
                createStatCard(
                        "TOTAL EMERGENCIES",
                        totalLabel
                )
        );

        statisticsPanel.add(
                createStatCard(
                        "PENDING",
                        pendingLabel
                )
        );

        statisticsPanel.add(
                createStatCard(
                        "CRITICAL",
                        criticalLabel
                )
        );

        statisticsPanel.add(
                createStatCard(
                        "IN PROGRESS",
                        inProgressLabel
                )
        );

        statisticsPanel.add(
                createStatCard(
                        "RESOLVED",
                        resolvedLabel
                )
        );

        statisticsPanel.add(
                createStatCard(
                        "UNASSIGNED",
                        unassignedLabel
                )
        );

        statisticsPanel.add(
                createStatCard(
                        "AVAILABLE TEAMS",
                        availableTeamLabel
                )
        );

        statisticsPanel.add(
                createStatCard(
                        "BUSY TEAMS",
                        busyTeamLabel
                )
        );

        centerContent.add(
                statisticsPanel,
                BorderLayout.NORTH
        );

        // =========================================
        // RECENT EMERGENCIES
        // =========================================

        JPanel recentPanel =
                new JPanel(
                        new BorderLayout()
                );

        recentPanel.setBackground(
                Color.decode("#E8EDDF")
        );

        JLabel recentTitle =
                new JLabel(
                        "RECENT EMERGENCIES"
                );

        recentTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        17
                )
        );

        recentTitle.setForeground(
                Color.decode("#242423")
        );

        recentTitle.setBorder(
                BorderFactory.createEmptyBorder(
                        5,
                        0,
                        8,
                        0
                )
        );

        recentPanel.add(
                recentTitle,
                BorderLayout.NORTH
        );

        String[] columns = {
                "Emergency ID",
                "Type",
                "Priority",
                "Location",
                "Status",
                "Assigned Team"
        };

        tableModel =
                new DefaultTableModel(
                        columns,
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column) {

                        return false;
                    }
                };

        emergencyTable =
                new JTable(
                        tableModel
                );

        emergencyTable.setRowHeight(
                28
        );

        emergencyTable.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        emergencyTable.getTableHeader()
                .setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                12
                        )
                );

        emergencyTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane tableScrollPane =
                new JScrollPane(
                        emergencyTable
                );

        recentPanel.add(
                tableScrollPane,
                BorderLayout.CENTER
        );

        centerContent.add(
                recentPanel,
                BorderLayout.CENTER
        );

        contentPanel.add(
                centerContent,
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
    // MANAGEMENT
    // =========================================

    private void openManagement() {

        ManagementTabbedPanel managementPanel =
                new ManagementTabbedPanel(
                        mainFrame,
                        manager
                );

        mainFrame.addScreen(
                "MANAGEMENT",
                managementPanel
        );

        mainFrame.showScreen(
                "MANAGEMENT"
        );
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
    // SIDEBAR LOGOUT BUTTON
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
    // STAT CARD
    // =========================================

    private JPanel createStatCard(
            String title,
            JLabel valueLabel) {

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
                                10,
                                10,
                                10,
                                10
                        )
                )
        );

        JLabel titleLabel =
                new JLabel(
                        title,
                        SwingConstants.CENTER
                );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        11
                )
        );

        titleLabel.setForeground(
                Color.decode("#333533")
        );

        card.add(
                titleLabel,
                BorderLayout.NORTH
        );

        card.add(
                valueLabel,
                BorderLayout.CENTER
        );

        return card;
    }

    // =========================================
    // STAT LABEL
    // =========================================

    private JLabel createStatLabel(
            String value) {

        JLabel label =
                new JLabel(
                        value,
                        SwingConstants.CENTER
                );

        label.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        25
                )
        );

        label.setForeground(
                Color.decode("#242423")
        );

        return label;
    }

    // =========================================
    // REFRESH DASHBOARD DATA
    // =========================================

    private void refreshDashboard() {

        totalLabel.setText(
                String.valueOf(
                        manager.getTotalEmergencyCount()
                )
        );

        pendingLabel.setText(
                String.valueOf(
                        manager.getPendingCount()
                )
        );

        criticalLabel.setText(
                String.valueOf(
                        manager.getCriticalCount()
                )
        );

        inProgressLabel.setText(
                String.valueOf(
                        manager.getInProgressCount()
                )
        );

        resolvedLabel.setText(
                String.valueOf(
                        manager.getResolvedCount()
                )
        );

        unassignedLabel.setText(
                String.valueOf(
                        manager.getUnassignedEmergencyCount()
                )
        );

        availableTeamLabel.setText(
                String.valueOf(
                        manager.getAvailableTeamCount()
                )
        );

        busyTeamLabel.setText(
                String.valueOf(
                        manager.getBusyTeamCount()
                )
        );

        loadRecentEmergencies();
    }

    // =========================================
    // RECENT EMERGENCIES
    // =========================================

    private void loadRecentEmergencies() {

        tableModel.setRowCount(
                0
        );

        ArrayList<Emergency> emergencies =
                manager.getAllEmergencies();

        int startIndex =
                Math.max(
                        0,
                        emergencies.size() - 10
                );

        for (int i = startIndex;
             i < emergencies.size();
             i++) {

            Emergency emergency =
                    emergencies.get(i);

            String assignedTeam =
                    emergency.getAssignedTeamId();

            if (assignedTeam == null) {

                assignedTeam =
                        "Not Assigned";
            }

            tableModel.addRow(
                    new Object[]{
                            emergency.getEmergencyId(),
                            formatEmergencyType(
                                    emergency.getType()
                            ),
                            formatPriority(
                                    emergency.getPriority()
                            ),
                            emergency.getLocation(),
                            formatStatus(
                                    emergency.getStatus()
                            ),
                            assignedTeam
                    }
            );
        }
    }

    // =========================================
    // FORMAT TYPE
    // =========================================

    private String formatEmergencyType(
            EmergencyType type) {

        if (type ==
                EmergencyType.MEDICAL) {

            return "Medical Emergency";

        } else if (type ==
                EmergencyType.FIRE) {

            return "Fire Emergency";

        } else if (
                type ==
                        EmergencyType.ROAD_ACCIDENT) {

            return "Road Accident";

        } else if (
                type ==
                        EmergencyType.SECURITY) {

            return "Security Emergency";

        } else {

            return "Natural Disaster";
        }
    }

    // =========================================
    // FORMAT PRIORITY
    // =========================================

    private String formatPriority(
            Priority priority) {

        if (priority ==
                Priority.CRITICAL) {

            return "Critical";

        } else if (
                priority ==
                        Priority.HIGH) {

            return "High";

        } else if (
                priority ==
                        Priority.MEDIUM) {

            return "Medium";

        } else {

            return "Low";
        }
    }

    // =========================================
    // FORMAT STATUS
    // =========================================

    private String formatStatus(
            EmergencyStatus status) {

        if (status ==
                EmergencyStatus.PENDING) {

            return "Pending";

        } else if (
                status ==
                        EmergencyStatus.ASSIGNED) {

            return "Assigned";

        } else if (
                status ==
                        EmergencyStatus.IN_PROGRESS) {

            return "In Progress";

        } else if (
                status ==
                        EmergencyStatus.RESOLVED) {

            return "Resolved";

        } else {

            return "Cancelled";
        }
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