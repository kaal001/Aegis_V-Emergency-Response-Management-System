package gui;

import enums.EmergencyStatus;
import enums.EmergencyType;
import enums.Priority;
import enums.TeamType;
import manager.EmergencyManager;
import model.ResponseTeam;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ReportStatisticsFrame extends JPanel {

    private MainFrame mainFrame;
    private EmergencyManager manager;

    private JLabel totalLabel;
    private JLabel criticalLabel;
    private JLabel pendingLabel;
    private JLabel resolvedLabel;
    private JLabel availableTeamLabel;
    private JLabel busyTeamLabel;

    private DefaultTableModel typeTableModel;
    private DefaultTableModel priorityTableModel;
    private DefaultTableModel statusTableModel;
    private DefaultTableModel teamTableModel;

    public ReportStatisticsFrame(
            MainFrame mainFrame,
            EmergencyManager manager) {

        this.mainFrame = mainFrame;
        this.manager = manager;

        createUI();

        refreshReports();
    }

    // =========================================
    // CREATE UI
    // =========================================

    private void createUI() {

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        mainPanel.setBackground(
                Color.decode("#E8EDDF")
        );

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        15,
                        15,
                        15
                )
        );

        // =========================================
        // HEADER
        // =========================================

        JLabel titleLabel =
                new JLabel(
                        "REPORTS & STATISTICS",
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
                        5,
                        5,
                        10,
                        5
                )
        );

        mainPanel.add(
                titleLabel,
                BorderLayout.NORTH
        );

        // =========================================
        // CONTENT
        // =========================================

        JPanel contentPanel =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        contentPanel.setBackground(
                Color.decode("#E8EDDF")
        );

        // =========================================
        // SUMMARY CARDS
        // =========================================

        JPanel summaryPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                6,
                                10,
                                10
                        )
                );

        summaryPanel.setBackground(
                Color.decode("#E8EDDF")
        );

        totalLabel =
                createValueLabel("0");

        criticalLabel =
                createValueLabel("0");

        pendingLabel =
                createValueLabel("0");

        resolvedLabel =
                createValueLabel("0");

        availableTeamLabel =
                createValueLabel("0");

        busyTeamLabel =
                createValueLabel("0");

        summaryPanel.add(
                createSummaryCard(
                        "TOTAL",
                        totalLabel
                )
        );

        summaryPanel.add(
                createSummaryCard(
                        "CRITICAL",
                        criticalLabel
                )
        );

        summaryPanel.add(
                createSummaryCard(
                        "PENDING",
                        pendingLabel
                )
        );

        summaryPanel.add(
                createSummaryCard(
                        "RESOLVED",
                        resolvedLabel
                )
        );

        summaryPanel.add(
                createSummaryCard(
                        "AVAILABLE TEAMS",
                        availableTeamLabel
                )
        );

        summaryPanel.add(
                createSummaryCard(
                        "BUSY TEAMS",
                        busyTeamLabel
                )
        );

        contentPanel.add(
                summaryPanel,
                BorderLayout.NORTH
        );

        // =========================================
        // TABLES
        // =========================================

        JPanel tablesPanel =
                new JPanel(
                        new GridLayout(
                                2,
                                2,
                                10,
                                10
                        )
                );

        tablesPanel.setBackground(
                Color.decode("#E8EDDF")
        );

        // -----------------------------------------
        // TYPE TABLE
        // -----------------------------------------

        typeTableModel =
                new DefaultTableModel(
                        new String[]{
                                "Emergency Type",
                                "Count"
                        },
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column) {

                        return false;
                    }
                };

        JTable typeTable =
                createTable(
                        typeTableModel
                );

        tablesPanel.add(
                createTablePanel(
                        "EMERGENCIES BY TYPE",
                        typeTable
                )
        );

        // -----------------------------------------
        // PRIORITY TABLE
        // -----------------------------------------

        priorityTableModel =
                new DefaultTableModel(
                        new String[]{
                                "Priority",
                                "Count"
                        },
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column) {

                        return false;
                    }
                };

        JTable priorityTable =
                createTable(
                        priorityTableModel
                );

        tablesPanel.add(
                createTablePanel(
                        "EMERGENCIES BY PRIORITY",
                        priorityTable
                )
        );

        // -----------------------------------------
        // STATUS TABLE
        // -----------------------------------------

        statusTableModel =
                new DefaultTableModel(
                        new String[]{
                                "Status",
                                "Count"
                        },
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column) {

                        return false;
                    }
                };

        JTable statusTable =
                createTable(
                        statusTableModel
                );

        tablesPanel.add(
                createTablePanel(
                        "EMERGENCIES BY STATUS",
                        statusTable
                )
        );

        // -----------------------------------------
        // TEAM TABLE
        // -----------------------------------------

        teamTableModel =
                new DefaultTableModel(
                        new String[]{
                                "Team ID",
                                "Team Name",
                                "Type",
                                "Status"
                        },
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column) {

                        return false;
                    }
                };

        JTable teamTable =
                createTable(
                        teamTableModel
                );

        tablesPanel.add(
                createTablePanel(
                        "RESPONSE TEAM STATUS",
                        teamTable
                )
        );

        contentPanel.add(
                tablesPanel,
                BorderLayout.CENTER
        );

        mainPanel.add(
                contentPanel,
                BorderLayout.CENTER
        );

        // =========================================
        // BUTTON PANEL
        // =========================================

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                12,
                                5
                        )
                );

        buttonPanel.setBackground(
                Color.decode("#E8EDDF")
        );

        JButton refreshButton =
                new JButton(
                        "REFRESH"
                );

        JButton backButton =
                new JButton(
                        "BACK"
                );

        stylePrimaryButton(
                refreshButton
        );

        styleSecondaryButton(
                backButton
        );

        buttonPanel.add(
                refreshButton
        );

        buttonPanel.add(
                backButton
        );

        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        // =========================================
        // BUTTON ACTIONS
        // =========================================

        refreshButton.addActionListener(
                e -> refreshReports()
        );

        backButton.addActionListener(
                e -> mainFrame.goBack()
        );

        // =========================================
        // ADD MAIN PANEL
        // =========================================

        setLayout(
                new BorderLayout()
        );

        add(
                mainPanel,
                BorderLayout.CENTER
        );
    }

    // =========================================
    // REFRESH REPORTS
    // =========================================

    private void refreshReports() {

        updateSummary();

        updateTypeTable();

        updatePriorityTable();

        updateStatusTable();

        updateTeamTable();
    }

    // =========================================
    // SUMMARY
    // =========================================

    private void updateSummary() {

        totalLabel.setText(
                String.valueOf(
                        manager.getTotalEmergencyCount()
                )
        );

        criticalLabel.setText(
                String.valueOf(
                        manager.getCriticalCount()
                )
        );

        pendingLabel.setText(
                String.valueOf(
                        manager.getPendingCount()
                )
        );

        resolvedLabel.setText(
                String.valueOf(
                        manager.getResolvedCount()
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
    }

    // =========================================
    // TYPE TABLE
    // =========================================

    private void updateTypeTable() {

        typeTableModel.setRowCount(
                0
        );

        EmergencyType[] types =
                EmergencyType.values();

        for (int i = 0;
             i < types.length;
             i++) {

            EmergencyType type =
                    types[i];

            int count =
                    manager
                            .getEmergenciesByType(
                                    type
                            )
                            .size();

            typeTableModel.addRow(
                    new Object[]{
                            formatEmergencyType(
                                    type
                            ),
                            count
                    }
            );
        }
    }

    // =========================================
    // PRIORITY TABLE
    // =========================================

    private void updatePriorityTable() {

        priorityTableModel.setRowCount(
                0
        );

        Priority[] priorities =
                Priority.values();

        for (int i = 0;
             i < priorities.length;
             i++) {

            Priority priority =
                    priorities[i];

            int count =
                    manager
                            .getEmergenciesByPriority(
                                    priority
                            )
                            .size();

            priorityTableModel.addRow(
                    new Object[]{
                            formatPriority(
                                    priority
                            ),
                            count
                    }
            );
        }
    }

    // =========================================
    // STATUS TABLE
    // =========================================

    private void updateStatusTable() {

        statusTableModel.setRowCount(
                0
        );

        EmergencyStatus[] statuses =
                EmergencyStatus.values();

        for (int i = 0;
             i < statuses.length;
             i++) {

            EmergencyStatus status =
                    statuses[i];

            int count =
                    manager
                            .getEmergenciesByStatus(
                                    status
                            )
                            .size();

            statusTableModel.addRow(
                    new Object[]{
                            formatStatus(
                                    status
                            ),
                            count
                    }
            );
        }
    }

    // =========================================
    // TEAM TABLE
    // =========================================

    private void updateTeamTable() {

        teamTableModel.setRowCount(
                0
        );

        ArrayList<ResponseTeam> teams =
                manager.getAllTeams();

        for (int i = 0;
             i < teams.size();
             i++) {

            ResponseTeam team =
                    teams.get(i);

            String availability;

            if (team.isAvailable()) {

                availability =
                        "Available";

            } else {

                availability =
                        "Busy";
            }

            teamTableModel.addRow(
                    new Object[]{
                            team.getTeamId(),
                            team.getTeamName(),
                            formatTeamType(
                                    team.getTeamType()
                            ),
                            availability
                    }
            );
        }
    }

    // =========================================
    // CREATE TABLE
    // =========================================

    private JTable createTable(
            DefaultTableModel model) {

        JTable table =
                new JTable(
                        model
                );

        table.setRowHeight(
                26
        );

        table.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        table.getTableHeader()
                .setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                12
                        )
                );

        table.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        return table;
    }

    // =========================================
    // TABLE PANEL
    // =========================================

    private JPanel createTablePanel(
            String title,
            JTable table) {

        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );

        panel.setBackground(
                Color.decode("#CFDBD5")
        );

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                Color.decode("#333533")
                        ),
                        BorderFactory.createEmptyBorder(
                                7,
                                7,
                                7,
                                7
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
                        12
                )
        );

        titleLabel.setForeground(
                Color.decode("#242423")
        );

        titleLabel.setBorder(
                BorderFactory.createEmptyBorder(
                        2,
                        2,
                        7,
                        2
                )
        );

        panel.add(
                titleLabel,
                BorderLayout.NORTH
        );

        JScrollPane scrollPane =
                new JScrollPane(
                        table
                );

        panel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        return panel;
    }

    // =========================================
    // SUMMARY CARD
    // =========================================

    private JPanel createSummaryCard(
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
                                7,
                                7,
                                7,
                                7
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
                        10
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
    // VALUE LABEL
    // =========================================

    private JLabel createValueLabel(
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
                        22
                )
        );

        label.setForeground(
                Color.decode("#242423")
        );

        return label;
    }

    // =========================================
    // FORMAT TYPE
    // =========================================

    private String formatEmergencyType(EmergencyType type) {

        switch (type) {

            case MEDICAL:
                return "Medical Emergency";

            case FIRE:
                return "Fire Emergency";

            case ROAD_ACCIDENT:
                return "Road Accident";

            case SECURITY:
                return "Security Emergency";

            case NATURAL_DISASTER:
                return "Natural Disaster";

            case GAS_LEAK:
                return "Gas Leak";

            case ELECTRICAL_EMERGENCY:
                return "Electrical Emergency";

            case BUILDING_COLLAPSE:
                return "Building Collapse";

            case INDUSTRIAL_ACCIDENT:
                return "Industrial Accident";

            case MISSING_PERSON:
                return "Missing Person";

            case WATER_FLOOD_EMERGENCY:
                return "Water / Flood Emergency";

            case CUSTOM:
                return "Other / Custom";

            default:
                return "Unknown";
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

        } else if (priority ==
                Priority.HIGH) {

            return "High";

        } else if (priority ==
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
    // FORMAT TEAM TYPE
    // =========================================

    private String formatTeamType(
            TeamType type) {

        if (type ==
                TeamType.AMBULANCE) {

            return "Ambulance";

        } else if (type ==
                TeamType.FIRE) {

            return "Fire";

        } else if (type ==
                TeamType.RESCUE) {

            return "Rescue";

        } else {

            return "Security";
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
                        12
                )
        );

        button.setFocusPainted(
                false
        );
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
                        12
                )
        );

        button.setFocusPainted(
                false
        );
    }
}