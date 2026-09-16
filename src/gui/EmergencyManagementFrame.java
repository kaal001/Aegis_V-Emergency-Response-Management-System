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

public class EmergencyManagementFrame extends JPanel {

    private MainFrame mainFrame;
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

    private Emergency selectedEmergency;

    public EmergencyManagementFrame(
            MainFrame mainFrame,
            EmergencyManager manager) {

        this.mainFrame = mainFrame;
        this.manager = manager;

        createUI();

        loadEmergencies(
                manager.getAllEmergencies()
        );
    }

    // =========================
    // CREATE UI
    // =========================

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

        // =========================
        // HEADER
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
                        10,
                        10,
                        15,
                        10
                )
        );

        mainPanel.add(
                titleLabel,
                BorderLayout.NORTH
        );

        // =========================
        // FILTER PANEL
        // =========================

        JPanel filterPanel =
                new JPanel(
                        new GridLayout(
                                2,
                                4,
                                10,
                                8
                        )
                );

        filterPanel.setBackground(
                Color.decode("#CFDBD5")
        );

        filterPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        10,
                        10,
                        10
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

        JButton searchButton =
                new JButton(
                        "SEARCH"
                );

        JButton clearButton =
                new JButton(
                        "CLEAR"
                );

        stylePrimaryButton(
                searchButton
        );

        styleSecondaryButton(
                clearButton
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
        // TABLE
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

        // =========================
        // SELECTION PANEL
        // =========================

        JPanel selectionPanel =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        selectionPanel.setBackground(
                Color.decode("#CFDBD5")
        );

        selectionPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        10,
                        10,
                        10
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

        JButton updatePriorityButton =
                new JButton(
                        "UPDATE PRIORITY"
                );

        JButton updateStatusButton =
                new JButton(
                        "UPDATE STATUS"
                );

        JButton viewDetailsButton =
                new JButton(
                        "VIEW DETAILS"
                );

        JButton assignTeamButton =
                new JButton(
                        "ASSIGN TEAM"
                );

        JButton deleteButton =
                new JButton(
                        "DELETE"
                );

        JButton backButton =
                new JButton(
                        "BACK"
                );

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

        styleDeleteButton(
                deleteButton
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
                deleteButton
        );

        updatePanel.add(
                backButton
        );

        selectionPanel.add(
                updatePanel,
                BorderLayout.CENTER
        );

        // =========================
        // CENTER
        // =========================

        JPanel centerPanel =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
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
        // TABLE SELECTION
        // =========================

        emergencyTable
                .getSelectionModel()
                .addListSelectionListener(
                        e -> {

                            if (!e.getValueIsAdjusting()) {

                                selectEmergency();
                            }
                        }
                );

        // =========================
        // BUTTON ACTIONS
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

        deleteButton.addActionListener(
                e -> deleteEmergency()
        );

        backButton.addActionListener(
                e -> mainFrame.goBack()
        );

        // =========================
        // ADD PANEL
        // =========================

        setLayout(
                new BorderLayout()
        );

        add(
                mainPanel,
                BorderLayout.CENTER
        );
    }

    // =========================
    // LOAD EMERGENCIES
    // =========================

    private void loadEmergencies(
            ArrayList<Emergency> emergencies) {

        tableModel.setRowCount(
                0
        );

        ArrayList<Emergency> sortedEmergencies =
                new ArrayList<>(
                        emergencies
                );

        sortByPriority(
                sortedEmergencies
        );

        for (Emergency emergency :
                sortedEmergencies) {

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
                            assignedTeam,
                            emergency.getReportedBy()
                    }
            );
        }

        selectedEmergency =
                null;

        selectedEmergencyLabel.setText(
                "No emergency selected."
        );
    }

    // =========================
    // SORT BY PRIORITY
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
                                emergencies
                                        .get(j)
                                        .getPriority()
                        );

                int secondPriority =
                        getPriorityRank(
                                emergencies
                                        .get(j + 1)
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

    // =========================
    // PRIORITY RANK
    // =========================

    private int getPriorityRank(
            Priority priority) {

        if (priority ==
                Priority.CRITICAL) {

            return 1;

        } else if (priority ==
                Priority.HIGH) {

            return 2;

        } else if (priority ==
                Priority.MEDIUM) {

            return 3;

        } else {

            return 4;
        }
    }

    // =========================
    // SELECT EMERGENCY
    // =========================

    private void selectEmergency() {

        int selectedRow =
                emergencyTable.getSelectedRow();

        if (selectedRow == -1) {

            selectedEmergency =
                    null;

            selectedEmergencyLabel.setText(
                    "No emergency selected."
            );

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
                            selectedEmergency
                                    .getType()
                    )
                            + " | "
                            + formatPriority(
                            selectedEmergency
                                    .getPriority()
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
        }
    }

    // =========================
    // SEARCH
    // =========================

    private void searchEmergencies() {

        String keyword =
                searchField
                        .getText()
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

                result.add(
                        emergency
                );
            }
        }

        loadEmergencies(
                result
        );
    }

    // =========================
    // CLEAR FILTERS
    // =========================

    private void clearFilters() {

        searchField.setText(
                ""
        );

        typeFilter.setSelectedIndex(
                0
        );

        priorityFilter.setSelectedIndex(
                0
        );

        statusFilter.setSelectedIndex(
                0
        );

        loadEmergencies(
                manager.getAllEmergencies()
        );
    }

    // =========================
    // UPDATE PRIORITY
    // =========================

    private void updatePriority() {

        if (selectedEmergency == null) {

            JOptionPane.showMessageDialog(
                    mainFrame,
                    "Please select an emergency first.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE
            );

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
                    mainFrame,
                    "Emergency priority updated successfully.",
                    "Update Successful",
                    JOptionPane.INFORMATION_MESSAGE
            );

            refreshCurrentView();

        } else {

            JOptionPane.showMessageDialog(
                    mainFrame,
                    "Unable to update emergency priority.",
                    "Update Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================
    // UPDATE STATUS
    // =========================

    private void updateStatus() {

        if (selectedEmergency == null) {

            JOptionPane.showMessageDialog(
                    mainFrame,
                    "Please select an emergency first.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE
            );

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
                    mainFrame,
                    "Emergency status updated successfully.",
                    "Update Successful",
                    JOptionPane.INFORMATION_MESSAGE
            );

            refreshCurrentView();

        } else {

            JOptionPane.showMessageDialog(
                    mainFrame,
                    "Unable to update emergency status.",
                    "Update Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================
    // DELETE EMERGENCY
    // =========================

    private void deleteEmergency() {

        if (selectedEmergency == null) {

            JOptionPane.showMessageDialog(
                    mainFrame,
                    "Please select an emergency first.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int choice =
                JOptionPane.showConfirmDialog(
                        mainFrame,
                        "Are you sure you want to delete emergency "
                                + selectedEmergency.getEmergencyId()
                                + "?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

        if (choice != JOptionPane.YES_OPTION) {
            return;
        }

        String emergencyId =
                selectedEmergency.getEmergencyId();

        manager.removeEmergency(
                emergencyId
        );

        manager.saveData();

        JOptionPane.showMessageDialog(
                mainFrame,
                "Emergency deleted successfully.",
                "Delete Successful",
                JOptionPane.INFORMATION_MESSAGE
        );

        selectedEmergency =
                null;

        emergencyTable.clearSelection();

        refreshCurrentView();
    }

    // =========================
    // VIEW DETAILS
    // =========================

    private void viewDetails() {

        if (selectedEmergency == null) {

            JOptionPane.showMessageDialog(
                    mainFrame,
                    "Please select an emergency first.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String assignedTeam =
                selectedEmergency
                        .getAssignedTeamId();

        if (assignedTeam == null) {

            assignedTeam =
                    "Not Assigned";
        }

        // =========================
        // CREATE DIALOG
        // =========================

        JDialog detailsDialog =
                new JDialog(
                        mainFrame,
                        "Emergency Details",
                        true
                );

        detailsDialog.setSize(
                520,
                500
        );

        detailsDialog.setLocationRelativeTo(
                mainFrame
        );

        detailsDialog.setLayout(
                new BorderLayout(
                        10,
                        10
                )
        );

        // =========================
        // TITLE
        // =========================

        JLabel titleLabel =
                new JLabel(
                        "EMERGENCY DETAILS",
                        SwingConstants.CENTER
                );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        titleLabel.setForeground(
                Color.decode("#242423")
        );

        titleLabel.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        10,
                        10,
                        10
                )
        );

        // =========================
        // DETAILS AREA
        // =========================

        JTextArea detailsArea =
                new JTextArea();

        detailsArea.setText(
                "Emergency ID: "
                        + selectedEmergency.getEmergencyId()
                        + "\n\n"

                        + "Reported By: "
                        + selectedEmergency.getReportedBy()
                        + "\n\n"

                        + "Type: "
                        + formatEmergencyType(
                        selectedEmergency.getType()
                )
                        + "\n\n"

                        + "Priority: "
                        + formatPriority(
                        selectedEmergency.getPriority()
                )
                        + "\n\n"

                        + "Location: "
                        + selectedEmergency.getLocation()
                        + "\n\n"

                        + "Description: "
                        + selectedEmergency.getDescription()
                        + "\n\n"

                        + "Status: "
                        + formatStatus(
                        selectedEmergency.getStatus()
                )
                        + "\n\n"

                        + "Assigned Team: "
                        + assignedTeam
                        + "\n\n"

                        + "Date/Time: "
                        + selectedEmergency.getDateTime()
        );

        detailsArea.setEditable(
                false
        );

        detailsArea.setLineWrap(
                true
        );

        detailsArea.setWrapStyleWord(
                true
        );

        detailsArea.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        detailsArea.setBackground(
                Color.decode("#E8EDDF")
        );

        detailsArea.setForeground(
                Color.decode("#242423")
        );

        detailsArea.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        15,
                        10,
                        15
                )
        );

        JScrollPane scrollPane =
                new JScrollPane(
                        detailsArea
                );

        // =========================
        // CLOSE BUTTON
        // =========================

        JButton closeButton =
                new JButton(
                        "CLOSE"
                );

        closeButton.setBackground(
                Color.decode("#333533")
        );

        closeButton.setForeground(
                Color.WHITE
        );

        closeButton.setFocusPainted(
                false
        );

        closeButton.addActionListener(
                e -> detailsDialog.dispose()
        );

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER
                        )
                );

        buttonPanel.setBackground(
                Color.decode("#E8EDDF")
        );

        buttonPanel.add(
                closeButton
        );

        // =========================
        // ADD COMPONENTS
        // =========================

        detailsDialog.add(
                titleLabel,
                BorderLayout.NORTH
        );

        detailsDialog.add(
                scrollPane,
                BorderLayout.CENTER
        );

        detailsDialog.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        // =========================
        // SHOW DIALOG
        // =========================

        detailsDialog.setVisible(
                true
        );
    }

    // =========================
    // TEAM ASSIGNMENT
    // =========================

    private void openTeamAssignment() {

        if (selectedEmergency == null) {

            JOptionPane.showMessageDialog(
                    mainFrame,
                    "Please select an emergency first.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (selectedEmergency
                .getStatus()
                != EmergencyStatus.PENDING) {

            JOptionPane.showMessageDialog(
                    mainFrame,
                    "Only pending emergencies can be assigned.",
                    "Assignment Not Allowed",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        TeamAssignmentFrame assignmentFrame =
                new TeamAssignmentFrame(
                        mainFrame,
                        manager
                );

        mainFrame.addScreen(
                "TEAM_ASSIGNMENT",
                assignmentFrame
        );

        mainFrame.showScreen(
                "TEAM_ASSIGNMENT"
        );
    }

    // =========================
    // REFRESH
    // =========================

    private void refreshCurrentView() {

        searchEmergencies();
    }

    // =========================
    // FORMAT TYPE
    // =========================

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
    // =========================
    // FORMAT PRIORITY
    // =========================

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

    // =========================
    // FORMAT STATUS
    // =========================

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

    // =========================
    // CONVERT PRIORITY
    // =========================

    private Priority convertPriority(
            String priority) {

        if (priority.equals(
                "Critical"
        )) {

            return Priority.CRITICAL;

        } else if (priority.equals(
                "High"
        )) {

            return Priority.HIGH;

        } else if (priority.equals(
                "Medium"
        )) {

            return Priority.MEDIUM;

        } else {

            return Priority.LOW;
        }
    }

    // =========================
    // CONVERT STATUS
    // =========================

    private EmergencyStatus convertStatus(
            String status) {

        if (status.equals(
                "Pending"
        )) {

            return EmergencyStatus.PENDING;

        } else if (status.equals(
                "Assigned"
        )) {

            return EmergencyStatus.ASSIGNED;

        } else if (status.equals(
                "In Progress"
        )) {

            return EmergencyStatus.IN_PROGRESS;

        } else if (status.equals(
                "Resolved"
        )) {

            return EmergencyStatus.RESOLVED;

        } else {

            return EmergencyStatus.CANCELLED;
        }
    }

    // =========================
    // PRIMARY BUTTON
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

        button.setFocusPainted(
                false
        );
    }

    // =========================
    // DELETE BUTTON
    // =========================

    private void styleDeleteButton(
            JButton button) {

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
                        11
                )
        );

        button.setFocusPainted(
                false
        );
    }

    // =========================
    // SECONDARY BUTTON
    // =========================

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

        button.setFocusPainted(
                false
        );
    }
}