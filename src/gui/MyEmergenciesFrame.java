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

public class MyEmergenciesFrame extends JFrame {

    private EmergencyManager manager;
    private User user;

    private JTextField searchField;
    private JComboBox<String> statusCombo;

    private JTable emergencyTable;
    private DefaultTableModel tableModel;

    private JLabel selectedLabel;

    private JButton detailsButton;

    public MyEmergenciesFrame(
            EmergencyManager manager,
            User user) {

        this.manager = manager;
        this.user = user;

        setTitle(
                "My Emergencies - Emergency Response Management System"
        );

        setSize(1000, 650);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLocationRelativeTo(null);
        setResizable(false);

        createUI();

        loadEmergencies();
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

        JPanel headerPanel =
                new JPanel(
                        new BorderLayout()
                );

        headerPanel.setBackground(
                Color.decode("#242423")
        );

        JLabel titleLabel =
                new JLabel(
                        "MY EMERGENCIES"
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
                        12, 15, 12, 10
                )
        );

        JLabel userLabel =
                new JLabel(
                        "User: " + user.getName(),
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
                        12, 10, 12, 15
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

        // =========================
        // Search + Filter
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
                new JLabel("Search:");

        searchLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        searchField =
                new JTextField(18);

        JLabel statusLabel =
                new JLabel("Status:");

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
                new JButton("SEARCH");

        stylePrimaryButton(
                searchButton
        );

        filterPanel.add(searchLabel);
        filterPanel.add(searchField);
        filterPanel.add(statusLabel);
        filterPanel.add(statusCombo);
        filterPanel.add(searchButton);

        // =========================
        // Table
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

        JScrollPane scrollPane =
                new JScrollPane(
                        emergencyTable
                );

        // =========================
        // Selected Information
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
                                10, 10, 10, 10
                        )
                )
        );

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
        // Bottom Buttons
        // =========================

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

        detailsButton =
                new JButton(
                        "VIEW DETAILS"
                );

        JButton refreshButton =
                new JButton("REFRESH");

        JButton backButton =
                new JButton("BACK");

        stylePrimaryButton(
                detailsButton
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
        // Actions
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
                .addListSelectionListener(e -> {

                    if (!e.getValueIsAdjusting()) {
                        showSelectedEmergency();
                    }
                });

        detailsButton.addActionListener(
                e -> showDetails()
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
                e -> dispose()
        );

        add(mainPanel);

        // No emergency is selected initially.
        detailsButton.setEnabled(false);
    }

    // =========================
    // Load User Emergencies
    // =========================

    private void loadEmergencies() {

        tableModel.setRowCount(0);

        ArrayList<Emergency> emergencies =
                manager.getEmergenciesByUser(
                        user.getId()
                );

        ArrayList<Emergency> filtered =
                new ArrayList<>();

        String searchText =
                searchField
                        .getText()
                        .trim()
                        .toLowerCase();

        String selectedStatus =
                (String) statusCombo
                        .getSelectedItem();

        for (int i = 0;
             i < emergencies.size();
             i++) {

            Emergency emergency =
                    emergencies.get(i);

            boolean matchesSearch = true;

            if (!searchText.isEmpty()) {

                String emergencyId =
                        emergency.getEmergencyId()
                                .toLowerCase();

                String location =
                        emergency.getLocation()
                                .toLowerCase();

                if (!emergencyId.contains(
                        searchText
                )
                        && !location.contains(
                        searchText
                )) {

                    matchesSearch = false;
                }
            }

            boolean matchesStatus = true;

            if (!selectedStatus.equals("All")) {

                String emergencyStatus =
                        formatStatus(
                                emergency.getStatus()
                        );

                if (!emergencyStatus.equals(
                        selectedStatus
                )) {

                    matchesStatus = false;
                }
            }

            if (matchesSearch
                    && matchesStatus) {

                filtered.add(
                        emergency
                );
            }
        }

        sortByPriority(filtered);

        for (int i = 0;
             i < filtered.size();
             i++) {

            Emergency emergency =
                    filtered.get(i);

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

        selectedLabel.setText(
                "Select an emergency to view details."
        );

        detailsButton.setEnabled(false);

        emergencyTable.clearSelection();

        if (filtered.isEmpty()) {

            selectedLabel.setText(
                    "No emergencies found."
            );
        }
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
    // Show Selected
    // =========================

    private void showSelectedEmergency() {

        int selectedRow =
                emergencyTable.getSelectedRow();

        if (selectedRow == -1) {

            selectedLabel.setText(
                    "Select an emergency to view details."
            );

            detailsButton.setEnabled(false);

            return;
        }

        String emergencyId =
                tableModel
                        .getValueAt(
                                selectedRow,
                                0
                        )
                        .toString();

        Emergency emergency =
                manager.findEmergencyById(
                        emergencyId
                );

        if (emergency != null) {

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

            detailsButton.setEnabled(true);
        }
    }

    // =========================
    // Details
    // =========================

    private void showDetails() {

        int selectedRow =
                emergencyTable.getSelectedRow();

        if (selectedRow == -1) {
            return;
        }

        String emergencyId =
                tableModel
                        .getValueAt(
                                selectedRow,
                                0
                        )
                        .toString();

        Emergency emergency =
                manager.findEmergencyById(
                        emergencyId
                );

        if (emergency == null) {
            return;
        }

        String assignedTeam =
                emergency.getAssignedTeamId();

        if (assignedTeam == null) {
            assignedTeam = "Not Assigned";
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
                new JTextArea(details);

        textArea.setEditable(false);

        textArea.setLineWrap(true);

        textArea.setWrapStyleWord(true);

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