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

public class AdminDashboard extends JFrame {

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
            EmergencyManager manager,
            Admin admin) {

        this.manager = manager;
        this.admin = admin;

        setTitle(
                "Admin Dashboard - Emergency Response Management System"
        );

        setSize(1000, 700);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);
        setResizable(false);

        createDashboardUI();
        refreshDashboard();
    }

    private void createDashboardUI() {

        JPanel mainPanel =
                new JPanel(new BorderLayout());

        mainPanel.setBackground(
                Color.decode("#E8EDDF")
        );

        // =========================
        // Header
        // =========================

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
                        18, 25, 18, 10
                )
        );

        JLabel adminLabel =
                new JLabel(
                        "Admin: " + admin.getName(),
                        SwingConstants.RIGHT
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

        adminLabel.setBorder(
                BorderFactory.createEmptyBorder(
                        18, 10, 18, 25
                )
        );

        headerPanel.add(
                titleLabel,
                BorderLayout.WEST
        );

        headerPanel.add(
                adminLabel,
                BorderLayout.EAST
        );

        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );

        // =========================
        // Center
        // =========================

        JPanel centerPanel =
                new JPanel(
                        new BorderLayout(
                                15, 15
                        )
                );

        centerPanel.setBackground(
                Color.decode("#E8EDDF")
        );

        centerPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 25, 10, 25
                )
        );

        // =========================
        // Statistics
        // =========================

        JPanel statisticsPanel =
                new JPanel(
                        new GridLayout(
                                2, 4, 12, 12
                        )
                );

        statisticsPanel.setBackground(
                Color.decode("#E8EDDF")
        );

        totalLabel = createStatLabel("0");
        pendingLabel = createStatLabel("0");
        criticalLabel = createStatLabel("0");
        inProgressLabel = createStatLabel("0");
        resolvedLabel = createStatLabel("0");
        unassignedLabel = createStatLabel("0");
        availableTeamLabel = createStatLabel("0");
        busyTeamLabel = createStatLabel("0");

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

        centerPanel.add(
                statisticsPanel,
                BorderLayout.NORTH
        );

        // =========================
        // Recent Emergencies
        // =========================

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
                        10, 0, 10, 0
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
                new JTable(tableModel);

        emergencyTable.setRowHeight(28);

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

        centerPanel.add(
                recentPanel,
                BorderLayout.CENTER
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        // =========================
        // Bottom Buttons
        // =========================

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                12,
                                12
                        )
                );

        buttonPanel.setBackground(
                Color.decode("#E8EDDF")
        );

        JButton refreshButton =
                new JButton("REFRESH");

        JButton emergenciesButton =
                new JButton("EMERGENCIES");

        JButton teamsButton =
                new JButton("RESPONSE TEAMS");

        JButton historyButton =
                new JButton("HISTORY");

        JButton reportsButton =
                new JButton("REPORTS");

        JButton logoutButton =
                new JButton("LOGOUT");

        stylePrimaryButton(refreshButton);
        stylePrimaryButton(emergenciesButton);
        stylePrimaryButton(teamsButton);
        stylePrimaryButton(historyButton);
        stylePrimaryButton(reportsButton);

        styleLogoutButton(logoutButton);

        buttonPanel.add(refreshButton);
        buttonPanel.add(emergenciesButton);
        buttonPanel.add(teamsButton);
        buttonPanel.add(historyButton);
        buttonPanel.add(reportsButton);
        buttonPanel.add(logoutButton);

        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        // =========================
        // Actions
        // =========================

        refreshButton.addActionListener(
                e -> refreshDashboard()
        );

        emergenciesButton.addActionListener(
                e -> openEmergencyManagement()
        );

        teamsButton.addActionListener(
                e -> openTeamManagement()
        );

        historyButton.addActionListener(
                e -> openHistory()
        );

        reportsButton.addActionListener(
                e -> openReports()
        );

        logoutButton.addActionListener(
                e -> logout()
        );

        add(mainPanel);
    }

    // =========================
    // Navigation
    // =========================

    private void openEmergencyManagement() {

        EmergencyManagementFrame frame =
                new EmergencyManagementFrame(
                        manager
                );

        frame.setVisible(true);
    }

    private void openTeamManagement() {

        ResponseTeamManagementFrame frame =
                new ResponseTeamManagementFrame(
                        manager
                );

        frame.setVisible(true);
    }

    private void openHistory() {

        EmergencyHistoryFrame frame =
                new EmergencyHistoryFrame(
                        manager
                );

        frame.setVisible(true);
    }

    private void openReports() {

        ReportStatisticsFrame frame =
                new ReportStatisticsFrame(
                        manager
                );

        frame.setVisible(true);
    }

    // =========================
    // Statistics Card
    // =========================

    private JPanel createStatCard(
            String title,
            JLabel valueLabel) {

        JPanel card =
                new JPanel(new BorderLayout());

        card.setBackground(
                Color.decode("#CFDBD5")
        );

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                Color.decode("#333533")
                        ),
                        BorderFactory.createEmptyBorder(
                                10, 10, 10, 10
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

    // =========================
    // Refresh Dashboard
    // =========================

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

    // =========================
    // Recent Emergencies
    // =========================

    private void loadRecentEmergencies() {

        tableModel.setRowCount(0);

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
                assignedTeam = "Not Assigned";
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

    // =========================
    // Formatting
    // =========================

    private String formatEmergencyType(
            EmergencyType type) {

        if (type == EmergencyType.MEDICAL) {
            return "Medical Emergency";

        } else if (type == EmergencyType.FIRE) {
            return "Fire Emergency";

        } else if (type ==
                EmergencyType.ROAD_ACCIDENT) {

            return "Road Accident";

        } else if (type ==
                EmergencyType.SECURITY) {

            return "Security Emergency";

        } else {

            return "Natural Disaster";
        }
    }

    private String formatPriority(
            Priority priority) {

        if (priority == Priority.CRITICAL) {
            return "Critical";

        } else if (priority == Priority.HIGH) {
            return "High";

        } else if (priority == Priority.MEDIUM) {
            return "Medium";

        } else {

            return "Low";
        }
    }

    private String formatStatus(
            EmergencyStatus status) {

        if (status ==
                EmergencyStatus.PENDING) {

            return "Pending";

        } else if (status ==
                EmergencyStatus.ASSIGNED) {

            return "Assigned";

        } else if (status ==
                EmergencyStatus.IN_PROGRESS) {

            return "In Progress";

        } else if (status ==
                EmergencyStatus.RESOLVED) {

            return "Resolved";

        } else {

            return "Cancelled";
        }
    }

    // =========================
    // Button Styling
    // =========================

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
                        12
                )
        );

        button.setFocusPainted(false);
    }

    private void styleLogoutButton(
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
                        12
                )
        );

        button.setFocusPainted(false);
    }

    // =========================
    // Logout
    // =========================

    private void logout() {

        int choice =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to logout?",
                        "Logout",
                        JOptionPane.YES_NO_OPTION
                );

        if (choice == JOptionPane.YES_OPTION) {

            dispose();

            LoginFrame loginFrame =
                    new LoginFrame(manager);

            loginFrame.setVisible(true);
        }
    }
}