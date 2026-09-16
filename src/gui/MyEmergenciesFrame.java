package gui;

import enums.EmergencyStatus;
import enums.EmergencyType;
import enums.Priority;
import manager.EmergencyManager;
import model.Emergency;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class MyEmergenciesFrame extends JPanel {

    private EmergencyManager manager;
    private User user;

    private Runnable onBack;

    private JTextField searchField;
    private JComboBox<String> statusCombo;

    private JTable emergencyTable;
    private DefaultTableModel tableModel;

    private JLabel selectedLabel;

    private JButton detailsButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton refreshButton;
    private JButton backButton;

    public MyEmergenciesFrame(
            EmergencyManager manager,
            User user,
            Runnable onBack) {

        this.manager = manager;
        this.user = user;
        this.onBack = onBack;

        setLayout(
                new BorderLayout()
        );

        setBackground(
                Color.decode("#E8EDDF")
        );

        createUI();
        loadEmergencies();
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
                        "MY EMERGENCIES",
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
                        15,
                        5
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
                        new FlowLayout(
                                FlowLayout.LEFT,
                                10,
                                5
                        )
                );

        filterPanel.setBackground(
                Color.decode("#E8EDDF")
        );

        JLabel searchLabel =
                new JLabel(
                        "Search:"
                );

        searchLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        searchField =
                new JTextField(
                        18
                );

        JLabel statusLabel =
                new JLabel(
                        "Status:"
                );

        statusLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        statusCombo =
                new JComboBox<>();

        statusCombo.addItem("All");
        statusCombo.addItem("Pending");
        statusCombo.addItem("Assigned");
        statusCombo.addItem("In Progress");
        statusCombo.addItem("Resolved");
        statusCombo.addItem("Cancelled");

        JButton searchButton =
                new JButton(
                        "SEARCH"
                );

        stylePrimaryButton(
                searchButton
        );

        filterPanel.add(
                searchLabel
        );

        filterPanel.add(
                searchField
        );

        filterPanel.add(
                statusLabel
        );

        filterPanel.add(
                statusCombo
        );

        filterPanel.add(
                searchButton
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

        emergencyTable
                .getTableHeader()
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

        JScrollPane scrollPane =
                new JScrollPane(
                        emergencyTable
                );

        // =========================
        // SELECTED EMERGENCY
        // =========================

        selectedLabel =
                new JLabel(
                        "Select an emergency to view details.",
                        SwingConstants.LEFT
                );

        selectedLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        selectedLabel.setForeground(
                Color.decode("#242423")
        );

        selectedLabel.setBorder(
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
                filterPanel,
                BorderLayout.NORTH
        );

        centerPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        centerPanel.add(
                selectedLabel,
                BorderLayout.SOUTH
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        // =========================
        // BUTTON PANEL
        // =========================

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                10,
                                5
                        )
                );

        buttonPanel.setBackground(
                Color.decode("#E8EDDF")
        );

        detailsButton =
                new JButton(
                        "VIEW DETAILS"
                );

        editButton =
                new JButton(
                        "EDIT"
                );

        deleteButton =
                new JButton(
                        "DELETE"
                );

        refreshButton =
                new JButton(
                        "REFRESH"
                );

        backButton =
                new JButton(
                        "BACK TO DASHBOARD"
                );

        stylePrimaryButton(
                detailsButton
        );

        stylePrimaryButton(
                editButton
        );

        styleDeleteButton(
                deleteButton
        );

        stylePrimaryButton(
                refreshButton
        );

        styleSecondaryButton(
                backButton
        );

        buttonPanel.add(
                detailsButton
        );

        buttonPanel.add(
                editButton
        );

        buttonPanel.add(
                deleteButton
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

        // =========================
        // ACTIONS
        // =========================

        searchButton.addActionListener(
                e -> loadEmergencies()
        );

        searchField.addActionListener(
                e -> loadEmergencies()
        );

        statusCombo.addActionListener(
                e -> loadEmergencies()
        );

        emergencyTable
                .getSelectionModel()
                .addListSelectionListener(
                        e -> showSelectedEmergency()
                );

        detailsButton.addActionListener(
                e -> showDetails()
        );

        editButton.addActionListener(
                e -> editSelectedEmergency()
        );

        deleteButton.addActionListener(
                e -> deleteSelectedEmergency()
        );

        refreshButton.addActionListener(
                e -> {

                    searchField.setText("");

                    statusCombo.setSelectedItem(
                            "All"
                    );

                    loadEmergencies();
                }
        );

        backButton.addActionListener(
                e -> {

                    if (onBack != null) {

                        onBack.run();
                    }
                }
        );

        // Initially disabled
        editButton.setEnabled(
                false
        );

        deleteButton.setEnabled(
                false
        );

        detailsButton.setEnabled(
                false
        );

        add(
                mainPanel
        );
    }

    // =========================
    // LOAD USER EMERGENCIES
    // =========================

    public void refreshData() {

        loadEmergencies();
    }

    private void loadEmergencies() {

        tableModel.setRowCount(
                0
        );

        ArrayList<Emergency> emergencies =
                manager.getEmergenciesByUser(
                        user.getId()
                );

        String searchText =
                searchField
                        .getText()
                        .trim()
                        .toLowerCase();

        String selectedStatus =
                (String)
                        statusCombo.getSelectedItem();

        for (
                int i = 0;
                i < emergencies.size();
                i++
        ) {

            Emergency emergency =
                    emergencies.get(i);

            String emergencyId =
                    emergency.getEmergencyId();

            String location =
                    emergency.getLocation();

            boolean matchesSearch =
                    true;

            if (!searchText.isEmpty()) {

                if (
                        !emergencyId
                                .toLowerCase()
                                .contains(searchText)

                                &&

                                !location
                                        .toLowerCase()
                                        .contains(searchText)
                ) {

                    matchesSearch =
                            false;
                }
            }

            boolean matchesStatus =
                    true;

            if (!selectedStatus
                    .equals("All")) {

                String emergencyStatus =
                        formatStatus(
                                emergency.getStatus()
                        );

                if (
                        !emergencyStatus
                                .equals(selectedStatus)
                ) {

                    matchesStatus =
                            false;
                }
            }

            if (
                    matchesSearch
                            && matchesStatus
            ) {

                String assignedTeam =
                        emergency
                                .getAssignedTeamId();

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

        selectedLabel.setText(
                "Select an emergency to view details."
        );

        updateActionButtons();
    }

    // =========================
    // SELECTED EMERGENCY
    // =========================

    private Emergency getSelectedEmergency() {

        int selectedRow =
                emergencyTable.getSelectedRow();

        if (selectedRow == -1) {

            return null;
        }

        String emergencyId =
                tableModel.getValueAt(
                        selectedRow,
                        0
                ).toString();

        return manager.findEmergencyById(
                emergencyId
        );
    }

    private void showSelectedEmergency() {

        Emergency emergency =
                getSelectedEmergency();

        if (emergency == null) {

            selectedLabel.setText(
                    "Select an emergency to view details."
            );

            updateActionButtons();

            return;
        }

        selectedLabel.setText(
                "Selected: "
                        + emergency.getEmergencyId()
                        + " | "
                        + formatEmergencyType(
                        emergency.getType()
                )
                        + " | "
                        + formatPriority(
                        emergency.getPriority()
                )
                        + " | "
                        + formatStatus(
                        emergency.getStatus()
                )
        );

        updateActionButtons();
    }

    // =========================
    // BUTTON STATE
    // =========================

    private void updateActionButtons() {

        Emergency emergency =
                getSelectedEmergency();

        boolean selected =
                emergency != null;

        detailsButton.setEnabled(
                selected
        );

        boolean canModify =
                selected
                        && emergency.getStatus()
                        == EmergencyStatus.PENDING
                        && emergency.getAssignedTeamId()
                        == null
                        && emergency.getReportedBy()
                        .equalsIgnoreCase(
                                user.getId()
                        );

        editButton.setEnabled(
                canModify
        );

        deleteButton.setEnabled(
                canModify
        );
    }

    // =========================
    // EDIT EMERGENCY
    // =========================

    private void editSelectedEmergency() {

        Emergency emergency =
                getSelectedEmergency();

        if (emergency == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select an emergency first.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (
                emergency.getStatus()
                        != EmergencyStatus.PENDING

                        ||

                        emergency.getAssignedTeamId()
                                != null

                        ||

                        !emergency.getReportedBy()
                                .equalsIgnoreCase(
                                        user.getId()
                                )
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "This emergency can no longer be edited.",
                    "Edit Not Allowed",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // =========================
        // EDIT FORM
        // =========================

        JPanel formPanel =
                new JPanel(
                        new GridBagLayout()
                );

        formPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        10,
                        10,
                        10
                )
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

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;

        JLabel typeLabel =
                new JLabel(
                        "Emergency Type:"
                );

        formPanel.add(
                typeLabel,
                gbc
        );

        JComboBox<String> typeComboBox =
                new JComboBox<>();

        typeComboBox.addItem(
                "Medical Emergency"
        );

        typeComboBox.addItem(
                "Fire Emergency"
        );

        typeComboBox.addItem(
                "Road Accident"
        );

        typeComboBox.addItem(
                "Security Emergency"
        );

        typeComboBox.addItem(
                "Natural Disaster"
        );

        typeComboBox.setSelectedItem(
                formatEmergencyType(
                        emergency.getType()
                )
        );

        gbc.gridx = 1;
        gbc.weightx = 1;

        formPanel.add(
                typeComboBox,
                gbc
        );

        // =========================
        // PRIORITY
        // =========================

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;

        formPanel.add(
                new JLabel(
                        "Priority:"
                ),
                gbc
        );

        JComboBox<String> priorityComboBox =
                new JComboBox<>();

        priorityComboBox.addItem(
                "Critical"
        );

        priorityComboBox.addItem(
                "High"
        );

        priorityComboBox.addItem(
                "Medium"
        );

        priorityComboBox.addItem(
                "Low"
        );

        priorityComboBox.setSelectedItem(
                formatPriority(
                        emergency.getPriority()
                )
        );

        gbc.gridx = 1;
        gbc.weightx = 1;

        formPanel.add(
                priorityComboBox,
                gbc
        );

        // =========================
        // LOCATION
        // =========================

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;

        formPanel.add(
                new JLabel(
                        "Location:"
                ),
                gbc
        );

        JTextField locationField =
                new JTextField(
                        emergency.getLocation()
                );

        gbc.gridx = 1;
        gbc.weightx = 1;

        formPanel.add(
                locationField,
                gbc
        );

        // =========================
        // DESCRIPTION
        // =========================

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        gbc.anchor =
                GridBagConstraints.NORTHWEST;

        formPanel.add(
                new JLabel(
                        "Description:"
                ),
                gbc
        );

        JTextArea descriptionArea =
                new JTextArea(
                        emergency.getDescription()
                );

        descriptionArea.setRows(
                5
        );

        descriptionArea.setLineWrap(
                true
        );

        descriptionArea.setWrapStyleWord(
                true
        );

        JScrollPane descriptionScrollPane =
                new JScrollPane(
                        descriptionArea
                );

        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill =
                GridBagConstraints.BOTH;

        formPanel.add(
                descriptionScrollPane,
                gbc
        );

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        formPanel,
                        "Edit Emergency",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

        if (
                result
                        != JOptionPane.OK_OPTION
        ) {

            return;
        }

        String location =
                locationField
                        .getText()
                        .trim();

        String description =
                descriptionArea
                        .getText()
                        .trim();

        if (location.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter the emergency location.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (description.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter the emergency description.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        EmergencyType type =
                convertEmergencyType(
                        typeComboBox
                                .getSelectedItem()
                                .toString()
                );

        Priority priority =
                convertPriority(
                        priorityComboBox
                                .getSelectedItem()
                                .toString()
                );

        boolean updated =
                manager.updateUserEmergency(
                        emergency.getEmergencyId(),
                        user.getId(),
                        type,
                        priority,
                        location,
                        description
                );

        if (updated) {

            manager.saveData();

            JOptionPane.showMessageDialog(
                    this,
                    "Emergency updated successfully.",
                    "Update Successful",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadEmergencies();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to update this emergency.",
                    "Update Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================
    // DELETE EMERGENCY
    // =========================

    private void deleteSelectedEmergency() {

        Emergency emergency =
                getSelectedEmergency();

        if (emergency == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select an emergency first.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (
                emergency.getStatus()
                        != EmergencyStatus.PENDING

                        ||

                        emergency.getAssignedTeamId()
                                != null

                        ||

                        !emergency.getReportedBy()
                                .equalsIgnoreCase(
                                        user.getId()
                                )
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "This emergency can no longer be deleted.",
                    "Delete Not Allowed",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int choice =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete emergency "
                                + emergency.getEmergencyId()
                                + "?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

        if (
                choice
                        != JOptionPane.YES_OPTION
        ) {

            return;
        }

        boolean deleted =
                manager.removeUserEmergency(
                        emergency.getEmergencyId(),
                        user.getId()
                );

        if (deleted) {

            manager.saveData();

            JOptionPane.showMessageDialog(
                    this,
                    "Emergency deleted successfully.",
                    "Delete Successful",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadEmergencies();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to delete this emergency.",
                    "Delete Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================
    // DETAILS
    // =========================

    private void showDetails() {

        Emergency emergency =
                getSelectedEmergency();

        if (emergency == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select an emergency first.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String assignedTeam =
                emergency.getAssignedTeamId();

        if (assignedTeam == null) {

            assignedTeam =
                    "Not Assigned";
        }

        String details =
                "Emergency ID: "
                        + emergency.getEmergencyId()
                        + "\n\n"

                        + "Type: "
                        + formatEmergencyType(
                        emergency.getType()
                )
                        + "\n\n"

                        + "Priority: "
                        + formatPriority(
                        emergency.getPriority()
                )
                        + "\n\n"

                        + "Status: "
                        + formatStatus(
                        emergency.getStatus()
                )
                        + "\n\n"

                        + "Location: "
                        + emergency.getLocation()
                        + "\n\n"

                        + "Description:\n"
                        + emergency.getDescription()
                        + "\n\n"

                        + "Assigned Team: "
                        + assignedTeam;

        JTextArea textArea =
                new JTextArea(
                        details
                );

        textArea.setEditable(
                false
        );

        textArea.setLineWrap(
                true
        );

        textArea.setWrapStyleWord(
                true
        );

        textArea.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        textArea.setBackground(
                Color.decode("#E8EDDF")
        );

        JScrollPane scrollPane =
                new JScrollPane(
                        textArea
                );

        scrollPane.setPreferredSize(
                new Dimension(
                        500,
                        350
                )
        );

        JOptionPane.showMessageDialog(
                this,
                scrollPane,
                "Emergency Details",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // =========================
    // CONVERT TYPE
    // =========================

    private EmergencyType convertEmergencyType(
            String type) {

        if (type.equals(
                "Medical Emergency"
        )) {

            return EmergencyType.MEDICAL;

        } else if (type.equals(
                "Fire Emergency"
        )) {

            return EmergencyType.FIRE;

        } else if (type.equals(
                "Road Accident"
        )) {

            return EmergencyType.ROAD_ACCIDENT;

        } else if (type.equals(
                "Security Emergency"
        )) {

            return EmergencyType.SECURITY;

        } else {

            return EmergencyType.NATURAL_DISASTER;
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

        } else if (
                priority == Priority.HIGH
        ) {

            return "High";

        } else if (
                priority == Priority.MEDIUM
        ) {

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
                EmergencyStatus.PENDING) {

            return "Pending";

        } else if (
                status ==
                        EmergencyStatus.ASSIGNED
        ) {

            return "Assigned";

        } else if (
                status ==
                        EmergencyStatus.IN_PROGRESS
        ) {

            return "In Progress";

        } else if (
                status ==
                        EmergencyStatus.RESOLVED
        ) {

            return "Resolved";

        } else {

            return "Cancelled";
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
                        12
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
                Color.decode("#333533")
        );

        button.setForeground(
                Color.decode("#F5CB5C")
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
                        12
                )
        );

        button.setFocusPainted(
                false
        );
    }
}