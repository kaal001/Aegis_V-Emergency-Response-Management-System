package gui;

import enums.EmergencyStatus;
import enums.EmergencyType;
import enums.Priority;
import manager.EmergencyManager;
import model.Emergency;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class EmergencyHistoryFrame extends JFrame {

    private EmergencyManager manager;

    private JTable historyTable;
    private DefaultTableModel tableModel;

    private JTextField searchField;

    private JComboBox<String> typeFilter;
    private JComboBox<String> priorityFilter;
    private JComboBox<String> statusFilter;

    private Emergency selectedEmergency;

    public EmergencyHistoryFrame(
            EmergencyManager manager) {

        this.manager = manager;

        setTitle(
                "Emergency History - Emergency Response Management System"
        );

        setSize(1100, 700);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLocationRelativeTo(null);
        setResizable(false);

        createUI();

        loadHistory(
                manager.getAllEmergencies()
        );
    }

    private void createUI() {

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout(
                                10, 10
                        )
                );

        mainPanel.setBackground(
                Color.decode("#E8EDDF")
        );

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 15, 15, 15
                )
        );

        // =========================
        // Header
        // =========================

        JLabel titleLabel =
                new JLabel(
                        "EMERGENCY HISTORY",
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
                        10, 10, 15, 10
                )
        );

        mainPanel.add(
                titleLabel,
                BorderLayout.NORTH
        );

        // =========================
        // Filter Panel
        // =========================

        JPanel filterPanel =
                new JPanel(
                        new GridLayout(
                                2, 4, 10, 8
                        )
                );

        filterPanel.setBackground(
                Color.decode("#CFDBD5")
        );

        filterPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        10, 10, 10, 10
                )
        );

        searchField =
                new JTextField();

        typeFilter =
                new JComboBox<>();

        priorityFilter =
                new JComboBox<>();

        statusFilter =
                new JComboBox<>();

        typeFilter.addItem(
                "All Types"
        );

        for (EmergencyType type :
                EmergencyType.values()) {

            typeFilter.addItem(
                    formatEmergencyType(type)
            );
        }

        priorityFilter.addItem(
                "All Priorities"
        );

        for (Priority priority :
                Priority.values()) {

            priorityFilter.addItem(
                    formatPriority(priority)
            );
        }

        statusFilter.addItem(
                "All Statuses"
        );

        for (EmergencyStatus status :
                EmergencyStatus.values()) {

            statusFilter.addItem(
                    formatStatus(status)
            );
        }

        JButton searchButton =
                new JButton("SEARCH");

        JButton clearButton =
                new JButton("CLEAR");

        stylePrimaryButton(searchButton);
        styleSecondaryButton(clearButton);

        filterPanel.add(
                new JLabel("Search:")
        );

        filterPanel.add(
                searchField
        );

        filterPanel.add(
                new JLabel("Type:")
        );

        filterPanel.add(
                typeFilter
        );

        filterPanel.add(
                new JLabel("Priority:")
        );

        filterPanel.add(
                priorityFilter
        );

        filterPanel.add(
                new JLabel("Status:")
        );

        filterPanel.add(
                statusFilter
        );

        JPanel filterWrapper =
                new JPanel(
                        new BorderLayout()
                );

        filterWrapper.setBackground(
                Color.decode("#E8EDDF")
        );

        filterWrapper.add(
                filterPanel,
                BorderLayout.CENTER
        );

        JPanel filterButtonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                10,
                                5
                        )
                );

        filterButtonPanel.setBackground(
                Color.decode("#E8EDDF")
        );

        filterButtonPanel.add(
                searchButton
        );

        filterButtonPanel.add(
                clearButton
        );

        filterWrapper.add(
                filterButtonPanel,
                BorderLayout.SOUTH
        );

        // =========================
        // History Table
        // =========================

        String[] columns = {
                "Emergency ID",
                "Type",
                "Priority",
                "Location",
                "Status",
                "Assigned Team",
                "Reported By",
                "Date / Time"
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

        historyTable =
                new JTable(tableModel);

        historyTable.setRowHeight(28);

        historyTable.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        historyTable.getTableHeader()
                .setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                12
                        )
                );

        historyTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane tableScrollPane =
                new JScrollPane(
                        historyTable
                );

        // =========================
        // Bottom Panel
        // =========================

        JPanel bottomPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                12,
                                10
                        )
                );

        bottomPanel.setBackground(
                Color.decode("#E8EDDF")
        );

        JButton detailsButton =
                new JButton(
                        "VIEW DETAILS"
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
                detailsButton
        );

        stylePrimaryButton(
                refreshButton
        );

        styleSecondaryButton(
                backButton
        );

        bottomPanel.add(
                detailsButton
        );

        bottomPanel.add(
                refreshButton
        );

        bottomPanel.add(
                backButton
        );

        // =========================
        // Main Center
        // =========================

        JPanel centerPanel =
                new JPanel(
                        new BorderLayout(
                                10, 10
                        )
                );

        centerPanel.setBackground(
                Color.decode("#E8EDDF")
        );

        centerPanel.add(
                filterWrapper,
                BorderLayout.NORTH
        );

        centerPanel.add(
                tableScrollPane,
                BorderLayout.CENTER
        );

        centerPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        // =========================
        // Table Selection
        // =========================

        historyTable
                .getSelectionModel()
                .addListSelectionListener(e -> {

                    if (!e.getValueIsAdjusting()) {
                        selectEmergency();
                    }
                });

        // =========================
        // Button Actions
        // =========================

        searchButton.addActionListener(
                e -> searchHistory()
        );

        clearButton.addActionListener(
                e -> clearFilters()
        );

        detailsButton.addActionListener(
                e -> viewDetails()
        );

        refreshButton.addActionListener(
                e -> loadHistory(
                        manager.getAllEmergencies()
                )
        );

        backButton.addActionListener(
                e -> dispose()
        );

        add(mainPanel);
    }

    // =========================
    // Load History
    // =========================

    private void loadHistory(
            ArrayList<Emergency> emergencies) {

        tableModel.setRowCount(0);

        for (Emergency emergency :
                emergencies) {

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
                            assignedTeam,
                            emergency.getReportedBy(),
                            emergency.getDateTime()
                    }
            );
        }

        selectedEmergency = null;
    }

    // =========================
    // Select Emergency
    // =========================

    private void selectEmergency() {

        int selectedRow =
                historyTable.getSelectedRow();

        if (selectedRow == -1) {
            selectedEmergency = null;
            return;
        }

        String emergencyId =
                tableModel
                        .getValueAt(
                                selectedRow,
                                0
                        )
                        .toString();

        selectedEmergency =
                manager.findEmergencyById(
                        emergencyId
                );
    }

    // =========================
    // Search History
    // =========================

    private void searchHistory() {

        String keyword =
                searchField.getText()
                        .trim()
                        .toLowerCase();

        String selectedType =
                typeFilter
                        .getSelectedItem()
                        .toString();

        String selectedPriority =
                priorityFilter
                        .getSelectedItem()
                        .toString();

        String selectedStatus =
                statusFilter
                        .getSelectedItem()
                        .toString();

        ArrayList<Emergency> result =
                new ArrayList<>();

        for (Emergency emergency :
                manager.getAllEmergencies()) {

            boolean keywordMatch =
                    keyword.isEmpty()
                            || emergency
                            .getEmergencyId()
                            .toLowerCase()
                            .contains(keyword)
                            || emergency
                            .getLocation()
                            .toLowerCase()
                            .contains(keyword)
                            || emergency
                            .getReportedBy()
                            .toLowerCase()
                            .contains(keyword);

            boolean typeMatch =
                    selectedType.equals(
                            "All Types"
                    )
                            || formatEmergencyType(
                            emergency.getType()
                    ).equals(
                            selectedType
                    );

            boolean priorityMatch =
                    selectedPriority.equals(
                            "All Priorities"
                    )
                            || formatPriority(
                            emergency.getPriority()
                    ).equals(
                            selectedPriority
                    );

            boolean statusMatch =
                    selectedStatus.equals(
                            "All Statuses"
                    )
                            || formatStatus(
                            emergency.getStatus()
                    ).equals(
                            selectedStatus
                    );

            if (keywordMatch
                    && typeMatch
                    && priorityMatch
                    && statusMatch) {

                result.add(emergency);
            }
        }

        loadHistory(result);
    }

    // =========================
    // Clear Filters
    // =========================

    private void clearFilters() {

        searchField.setText("");

        typeFilter.setSelectedIndex(0);

        priorityFilter.setSelectedIndex(0);

        statusFilter.setSelectedIndex(0);

        loadHistory(
                manager.getAllEmergencies()
        );
    }

    // =========================
    // View Details
    // =========================

    private void viewDetails() {

        if (selectedEmergency == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select an emergency first.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String assignedTeam =
                selectedEmergency.getAssignedTeamId();

        if (assignedTeam == null) {
            assignedTeam = "Not Assigned";
        }

        String details =
                "Emergency ID: "
                        + selectedEmergency
                        .getEmergencyId()
                        + "\n\n"
                        + "Reported By: "
                        + selectedEmergency
                        .getReportedBy()
                        + "\n"
                        + "Type: "
                        + formatEmergencyType(
                        selectedEmergency
                                .getType()
                )
                        + "\n"
                        + "Priority: "
                        + formatPriority(
                        selectedEmergency
                                .getPriority()
                )
                        + "\n"
                        + "Location: "
                        + selectedEmergency
                        .getLocation()
                        + "\n"
                        + "Description: "
                        + selectedEmergency
                        .getDescription()
                        + "\n"
                        + "Status: "
                        + formatStatus(
                        selectedEmergency
                                .getStatus()
                )
                        + "\n"
                        + "Assigned Team: "
                        + assignedTeam
                        + "\n"
                        + "Date/Time: "
                        + selectedEmergency
                        .getDateTime();

        JOptionPane.showMessageDialog(
                this,
                details,
                "Emergency Details",
                JOptionPane.INFORMATION_MESSAGE
        );
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

        button.setFocusPainted(false);
    }
}