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

public class EmergencyManagementFrame extends JFrame {

    private EmergencyManager manager;

    private JTable emergencyTable;
    private DefaultTableModel tableModel;

    private JTextField searchField;

    private JComboBox<String> typeFilter;
    private JComboBox<String> priorityFilter;
    private JComboBox<String> statusFilter;

    private JComboBox<String> priorityComboBox;
    private JComboBox<String> statusComboBox;

    private JLabel selectedEmergencyLabel;

    private JButton updatePriorityButton;
    private JButton updateStatusButton;
    private JButton viewDetailsButton;
    private JButton assignTeamButton;

    private Emergency selectedEmergency;

    public EmergencyManagementFrame(
            EmergencyManager manager) {

        this.manager = manager;

        setTitle(
                "Emergency Management - Emergency Response Management System"
        );

        setSize(1100, 700);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLocationRelativeTo(null);
        setResizable(false);

        createUI();

        loadEmergencies(
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
                        "EMERGENCY MANAGEMENT",
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

        typeFilter.addItem("All Types");

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
        // Table
        // =========================

        String[] columns = {
                "Emergency ID",
                "Type",
                "Priority",
                "Location",
                "Status",
                "Assigned Team",
                "Reported By"
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

        // =========================
        // Selection Panel
        // =========================

        JPanel selectionPanel =
                new JPanel(
                        new BorderLayout(
                                10, 10
                        )
                );

        selectionPanel.setBackground(
                Color.decode("#CFDBD5")
        );

        selectionPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        10, 10, 10, 10
                )
        );

        selectedEmergencyLabel =
                new JLabel(
                        "No emergency selected."
                );

        selectedEmergencyLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        selectedEmergencyLabel.setForeground(
                Color.decode("#242423")
        );

        selectionPanel.add(
                selectedEmergencyLabel,
                BorderLayout.NORTH
        );

        JPanel updatePanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                10,
                                5
                        )
                );

        updatePanel.setBackground(
                Color.decode("#CFDBD5")
        );

        priorityComboBox =
                new JComboBox<>();

        for (Priority priority :
                Priority.values()) {

            priorityComboBox.addItem(
                    formatPriority(priority)
            );
        }

        statusComboBox =
                new JComboBox<>();

        for (EmergencyStatus status :
                EmergencyStatus.values()) {

            statusComboBox.addItem(
                    formatStatus(status)
            );
        }

        updatePriorityButton =
                new JButton(
                        "UPDATE PRIORITY"
                );

        updateStatusButton =
                new JButton(
                        "UPDATE STATUS"
                );

        viewDetailsButton =
                new JButton(
                        "VIEW DETAILS"
                );

        assignTeamButton =
                new JButton(
                        "ASSIGN TEAM"
                );

        JButton backButton =
                new JButton("BACK");

        stylePrimaryButton(
                updatePriorityButton
        );

        stylePrimaryButton(
                updateStatusButton
        );

        stylePrimaryButton(
                viewDetailsButton
        );

        stylePrimaryButton(
                assignTeamButton
        );

        styleSecondaryButton(
                backButton
        );

        updatePanel.add(
                new JLabel("Priority:")
        );

        updatePanel.add(
                priorityComboBox
        );

        updatePanel.add(
                updatePriorityButton
        );

        updatePanel.add(
                new JLabel("Status:")
        );

        updatePanel.add(
                statusComboBox
        );

        updatePanel.add(
                updateStatusButton
        );

        updatePanel.add(
                viewDetailsButton
        );

        updatePanel.add(
                assignTeamButton
        );

        updatePanel.add(
                backButton
        );

        selectionPanel.add(
                updatePanel,
                BorderLayout.CENTER
        );

        // =========================
        // Center
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
                selectionPanel,
                BorderLayout.SOUTH
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        // =========================
        // Events
        // =========================

        emergencyTable
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
                e -> searchEmergencies()
        );

        clearButton.addActionListener(
                e -> clearFilters()
        );

        updatePriorityButton.addActionListener(
                e -> updatePriority()
        );

        updateStatusButton.addActionListener(
                e -> updateStatus()
        );

        viewDetailsButton.addActionListener(
                e -> viewDetails()
        );

        assignTeamButton.addActionListener(
                e -> openTeamAssignment()
        );

        backButton.addActionListener(
                e -> dispose()
        );

        add(mainPanel);

        // Initially nothing is selected.
        setActionButtonsEnabled(false);
    }

    // =========================
    // Load Emergencies
    // =========================

    private void loadEmergencies(
            ArrayList<Emergency> emergencies) {

        tableModel.setRowCount(0);

        ArrayList<Emergency> sortedEmergencies =
                new ArrayList<>(emergencies);

        sortByPriority(
                sortedEmergencies
        );

        for (Emergency emergency :
                sortedEmergencies) {

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
                            emergency.getReportedBy()
                    }
            );
        }

        selectedEmergency = null;

        selectedEmergencyLabel.setText(
                "No emergency selected."
        );

        setActionButtonsEnabled(false);
    }

    // =========================
    // Sort By Priority
    // =========================

    private void sortByPriority(
            ArrayList<Emergency> emergencies) {

        for (int i = 0;
             i < emergencies.size() - 1;
             i++) {

            for (int j = 0;
                 j < emergencies.size() - 1 - i;
                 j++) {

                int firstPriority =
                        getPriorityRank(
                                emergencies.get(j)
                                        .getPriority()
                        );

                int secondPriority =
                        getPriorityRank(
                                emergencies.get(j + 1)
                                        .getPriority()
                        );

                if (firstPriority >
                        secondPriority) {

                    Emergency temp =
                            emergencies.get(j);

                    emergencies.set(
                            j,
                            emergencies.get(j + 1)
                    );

                    emergencies.set(
                            j + 1,
                            temp
                    );
                }
            }
        }
    }

    private int getPriorityRank(
            Priority priority) {

        if (priority == Priority.CRITICAL) {
            return 1;

        } else if (priority == Priority.HIGH) {
            return 2;

        } else if (priority == Priority.MEDIUM) {
            return 3;

        } else {
            return 4;
        }
    }

    // =========================
    // Enable / Disable Actions
    // =========================

    private void setActionButtonsEnabled(
            boolean enabled) {

        priorityComboBox.setEnabled(enabled);

        statusComboBox.setEnabled(enabled);

        updatePriorityButton.setEnabled(enabled);

        updateStatusButton.setEnabled(enabled);

        viewDetailsButton.setEnabled(enabled);

        assignTeamButton.setEnabled(enabled);
    }

    // =========================
    // Select Emergency
    // =========================

    private void selectEmergency() {

        int selectedRow =
                emergencyTable.getSelectedRow();

        if (selectedRow == -1) {

            selectedEmergency = null;

            selectedEmergencyLabel.setText(
                    "No emergency selected."
            );

            setActionButtonsEnabled(false);

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

        if (selectedEmergency != null) {

            selectedEmergencyLabel.setText(
                    "Selected: "
                            + selectedEmergency
                            .getEmergencyId()
                            + " | "
                            + formatEmergencyType(
                            selectedEmergency.getType()
                    )
                            + " | "
                            + formatPriority(
                            selectedEmergency.getPriority()
                    )
                            + " | "
                            + formatStatus(
                            selectedEmergency.getStatus()
                    )
            );

            priorityComboBox.setSelectedItem(
                    formatPriority(
                            selectedEmergency
                                    .getPriority()
                    )
            );

            statusComboBox.setSelectedItem(
                    formatStatus(
                            selectedEmergency
                                    .getStatus()
                    )
            );

            setActionButtonsEnabled(true);
        }
    }

    // =========================
    // Search
    // =========================

    private void searchEmergencies() {

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
                    ).equals(selectedType);

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

        loadEmergencies(result);

        if (result.isEmpty()) {

            selectedEmergencyLabel.setText(
                    "No emergencies found."
            );
        }
    }

    // =========================
    // Clear Filters
    // =========================

    private void clearFilters() {

        searchField.setText("");

        typeFilter.setSelectedIndex(0);

        priorityFilter.setSelectedIndex(0);

        statusFilter.setSelectedIndex(0);

        loadEmergencies(
                manager.getAllEmergencies()
        );
    }

    // =========================
    // Update Priority
    // =========================

    private void updatePriority() {

        if (selectedEmergency == null) {

            return;
        }

        Priority priority =
                convertPriority(
                        priorityComboBox
                                .getSelectedItem()
                                .toString()
                );

        boolean updated =
                manager.updateEmergencyPriority(
                        selectedEmergency
                                .getEmergencyId(),
                        priority
                );

        if (updated) {

            manager.saveData();

            JOptionPane.showMessageDialog(
                    this,
                    "Emergency priority updated successfully.",
                    "Update Successful",
                    JOptionPane.INFORMATION_MESSAGE
            );

            refreshCurrentView();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to update emergency priority.",
                    "Update Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================
    // Update Status
    // =========================

    private void updateStatus() {

        if (selectedEmergency == null) {

            return;
        }

        EmergencyStatus status =
                convertStatus(
                        statusComboBox
                                .getSelectedItem()
                                .toString()
                );

        boolean updated =
                manager.updateEmergencyStatus(
                        selectedEmergency
                                .getEmergencyId(),
                        status
                );

        if (updated) {

            manager.saveData();

            JOptionPane.showMessageDialog(
                    this,
                    "Emergency status updated successfully.",
                    "Update Successful",
                    JOptionPane.INFORMATION_MESSAGE
            );

            refreshCurrentView();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Status update is not allowed for this emergency.",
                    "Update Failed",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    // =========================
    // View Details
    // =========================

    private void viewDetails() {

        if (selectedEmergency == null) {
            return;
        }

        String assignedTeam =
                selectedEmergency
                        .getAssignedTeamId();

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
    // Team Assignment
    // =========================

    private void openTeamAssignment() {

        if (selectedEmergency == null) {
            return;
        }

        if (selectedEmergency.getStatus()
                != EmergencyStatus.PENDING) {

            JOptionPane.showMessageDialog(
                    this,
                    "Only pending emergencies can be assigned.",
                    "Assignment Not Allowed",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        TeamAssignmentFrame frame =
                new TeamAssignmentFrame(
                        manager
                );

        frame.setVisible(true);
    }

    // =========================
    // Refresh
    // =========================

    private void refreshCurrentView() {

        searchEmergencies();
    }

    // =========================
    // Formatting
    // =========================

    private String formatEmergencyType(
            EmergencyType type) {

        if (type == EmergencyType.MEDICAL) {
            return "Medical Emergency";

        } else if (
                type == EmergencyType.FIRE) {

            return "Fire Emergency";

        } else if (
                type == EmergencyType.ROAD_ACCIDENT) {

            return "Road Accident";

        } else if (
                type == EmergencyType.SECURITY) {

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
                EmergencyStatus.IN_PROGRESS) {

            return "In Progress";

        } else if (status ==
                EmergencyStatus.PENDING) {

            return "Pending";

        } else if (status ==
                EmergencyStatus.ASSIGNED) {

            return "Assigned";

        } else if (status ==
                EmergencyStatus.RESOLVED) {

            return "Resolved";

        } else {
            return "Cancelled";
        }
    }

    private Priority convertPriority(
            String priority) {

        if (priority.equals("Critical")) {
            return Priority.CRITICAL;

        } else if (priority.equals("High")) {
            return Priority.HIGH;

        } else if (priority.equals("Medium")) {
            return Priority.MEDIUM;

        } else {
            return Priority.LOW;
        }
    }

    private EmergencyStatus convertStatus(
            String status) {

        if (status.equals("Pending")) {
            return EmergencyStatus.PENDING;

        } else if (status.equals("Assigned")) {
            return EmergencyStatus.ASSIGNED;

        } else if (status.equals("In Progress")) {
            return EmergencyStatus.IN_PROGRESS;

        } else if (status.equals("Resolved")) {
            return EmergencyStatus.RESOLVED;

        } else {
            return EmergencyStatus.CANCELLED;
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
                        11
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
                        11
                )
        );

        button.setFocusPainted(false);
    }
}