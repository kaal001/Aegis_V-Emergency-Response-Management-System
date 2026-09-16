package gui;

import enums.EmergencyStatus;
import enums.EmergencyType;
import enums.Priority;
import manager.EmergencyManager;
import model.Emergency;
import model.ResponseTeam;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TeamAssignmentFrame extends JFrame {

    private EmergencyManager manager;

    private JTable emergencyTable;
    private DefaultTableModel emergencyTableModel;

    private JComboBox<String> teamComboBox;

    private JLabel emergencyInfoLabel;
    private JLabel requiredTeamLabel;
    private JLabel selectedTeamLabel;

    private Emergency selectedEmergency;

    public TeamAssignmentFrame(
            EmergencyManager manager) {

        this.manager = manager;

        setTitle(
                "Team Assignment - Emergency Response Management System"
        );

        setSize(950, 650);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLocationRelativeTo(null);
        setResizable(false);

        createUI();

        loadPendingEmergencies();
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
                        "TEAM ASSIGNMENT",
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
        // Emergency Table
        // =========================

        String[] columns = {
                "Emergency ID",
                "Type",
                "Priority",
                "Location",
                "Status"
        };

        emergencyTableModel =
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
                        emergencyTableModel
                );

        emergencyTable.setRowHeight(28);

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
        // Assignment Panel
        // =========================

        JPanel assignmentPanel =
                new JPanel(
                        new BorderLayout(
                                10, 10
                        )
                );

        assignmentPanel.setBackground(
                Color.decode("#CFDBD5")
        );

        assignmentPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 15, 15, 15
                )
        );

        // =========================
        // Emergency Information
        // =========================

        emergencyInfoLabel =
                new JLabel(
                        "Select an emergency from the table."
                );

        emergencyInfoLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        emergencyInfoLabel.setForeground(
                Color.decode("#242423")
        );

        assignmentPanel.add(
                emergencyInfoLabel,
                BorderLayout.NORTH
        );

        // =========================
        // Team Selection
        // =========================

        JPanel teamPanel =
                new JPanel(
                        new GridLayout(
                                3, 2, 10, 10
                        )
                );

        teamPanel.setBackground(
                Color.decode("#CFDBD5")
        );

        requiredTeamLabel =
                new JLabel(
                        "Required Team: -"
                );

        teamComboBox =
                new JComboBox<>();

        selectedTeamLabel =
                new JLabel(
                        "Selected Team: None"
                );

        JButton refreshTeamsButton =
                new JButton(
                        "REFRESH TEAMS"
                );

        JButton assignButton =
                new JButton(
                        "ASSIGN TEAM"
                );

        JButton backButton =
                new JButton(
                        "BACK"
                );

        stylePrimaryButton(
                refreshTeamsButton
        );

        stylePrimaryButton(
                assignButton
        );

        styleSecondaryButton(
                backButton
        );

        teamPanel.add(
                new JLabel(
                        "Required Team Type:"
                )
        );

        teamPanel.add(
                requiredTeamLabel
        );

        teamPanel.add(
                new JLabel(
                        "Available Suitable Teams:"
                )
        );

        teamPanel.add(
                teamComboBox
        );

        teamPanel.add(
                new JLabel(
                        "Selected:"
                )
        );

        teamPanel.add(
                selectedTeamLabel
        );

        assignmentPanel.add(
                teamPanel,
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

        buttonPanel.add(
                refreshTeamsButton
        );

        buttonPanel.add(
                assignButton
        );

        buttonPanel.add(
                backButton
        );

        assignmentPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
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
                tableScrollPane,
                BorderLayout.CENTER
        );

        centerPanel.add(
                assignmentPanel,
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

        teamComboBox.addActionListener(
                e -> updateSelectedTeamLabel()
        );

        refreshTeamsButton.addActionListener(
                e -> loadSuitableTeams()
        );

        assignButton.addActionListener(
                e -> assignTeam()
        );

        backButton.addActionListener(
                e -> dispose()
        );

        add(mainPanel);
    }

    // =========================
    // Load Pending Emergencies
    // =========================

    private void loadPendingEmergencies() {

        emergencyTableModel.setRowCount(0);

        ArrayList<Emergency> pendingEmergencies =
                new ArrayList<>();

        for (Emergency emergency :
                manager.getAllEmergencies()) {

            if (emergency.getStatus()
                    == EmergencyStatus.PENDING) {

                pendingEmergencies.add(
                        emergency
                );
            }
        }

        sortByPriority(
                pendingEmergencies
        );

        for (Emergency emergency :
                pendingEmergencies) {

            emergencyTableModel.addRow(
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
                            )
                    }
            );
        }

        selectedEmergency = null;

        emergencyInfoLabel.setText(
                "Select an emergency from the table."
        );

        requiredTeamLabel.setText(
                "Required Team: -"
        );

        teamComboBox.removeAllItems();

        selectedTeamLabel.setText(
                "Selected Team: None"
        );
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

    // =========================
    // Priority Rank
    // =========================

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
    // Select Emergency
    // =========================

    private void selectEmergency() {

        int selectedRow =
                emergencyTable.getSelectedRow();

        if (selectedRow == -1) {

            selectedEmergency = null;

            emergencyInfoLabel.setText(
                    "Select an emergency from the table."
            );

            requiredTeamLabel.setText(
                    "Required Team: -"
            );

            teamComboBox.removeAllItems();

            selectedTeamLabel.setText(
                    "Selected Team: None"
            );

            return;
        }

        String emergencyId =
                emergencyTableModel
                        .getValueAt(
                                selectedRow,
                                0
                        )
                        .toString();

        selectedEmergency =
                manager.findEmergencyById(
                        emergencyId
                );

        if (selectedEmergency == null) {
            return;
        }

        emergencyInfoLabel.setText(
                "Selected Emergency: "
                        + selectedEmergency.getEmergencyId()
                        + " | "
                        + formatEmergencyType(
                        selectedEmergency.getType()
                )
                        + " | "
                        + formatPriority(
                        selectedEmergency.getPriority()
                )
        );

        String requiredTeam =
                formatTeamType(
                        manager.getRequiredTeamType(
                                selectedEmergency.getType()
                        )
                );

        requiredTeamLabel.setText(
                "Required Team: "
                        + requiredTeam
        );

        loadSuitableTeams();
    }

    // =========================
    // Load Suitable Teams
    // =========================

    private void loadSuitableTeams() {

        teamComboBox.removeAllItems();

        selectedTeamLabel.setText(
                "Selected Team: None"
        );

        if (selectedEmergency == null) {
            return;
        }

        ArrayList<ResponseTeam> teams =
                manager.findSuitableTeams(
                        selectedEmergency
                );

        if (teams.isEmpty()) {

            teamComboBox.addItem(
                    "No suitable team available"
            );

            return;
        }

        for (ResponseTeam team : teams) {

            String item =
                    team.getTeamId()
                            + " - "
                            + team.getTeamName();

            teamComboBox.addItem(item);
        }
    }

    // =========================
    // Selected Team Label
    // =========================

    private void updateSelectedTeamLabel() {

        if (teamComboBox.getSelectedItem()
                == null) {

            selectedTeamLabel.setText(
                    "Selected Team: None"
            );

            return;
        }

        String selected =
                teamComboBox
                        .getSelectedItem()
                        .toString();

        if (selected.startsWith(
                "No suitable team")) {

            selectedTeamLabel.setText(
                    "Selected Team: None"
            );

            return;
        }

        selectedTeamLabel.setText(
                "Selected Team: " + selected
        );
    }

    // =========================
    // Assign Team
    // =========================

    private void assignTeam() {

        if (selectedEmergency == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select an emergency first.",
                    "No Emergency Selected",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (teamComboBox.getSelectedItem()
                == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "No suitable team is available.",
                    "Assignment Failed",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String selected =
                teamComboBox
                        .getSelectedItem()
                        .toString();

        if (selected.startsWith(
                "No suitable team")) {

            JOptionPane.showMessageDialog(
                    this,
                    "No suitable team is currently available.",
                    "Assignment Failed",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String teamId =
                selected.split(" - ")[0];

        String assignedTime =
                new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm"
                ).format(new Date());

        boolean success =
                manager.assignTeam(
                        selectedEmergency.getEmergencyId(),
                        teamId,
                        assignedTime
                );

        if (success) {

            manager.saveData();

            JOptionPane.showMessageDialog(
                    this,
                    "Team assigned successfully.\n\n"
                            + "Emergency: "
                            + selectedEmergency.getEmergencyId()
                            + "\nTeam: "
                            + teamId
                            + "\nStatus: Assigned",
                    "Assignment Successful",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadPendingEmergencies();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to assign the selected team.",
                    "Assignment Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================
    // Formatting
    // =========================

    private String formatEmergencyType(
            EmergencyType type) {

        if (type ==
                EmergencyType.MEDICAL) {

            return "Medical Emergency";

        } else if (type ==
                EmergencyType.FIRE) {

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

    private String formatTeamType(
            enums.TeamType type) {

        if (type ==
                enums.TeamType.AMBULANCE) {

            return "Ambulance";

        } else if (type ==
                enums.TeamType.FIRE) {

            return "Fire";

        } else if (type ==
                enums.TeamType.RESCUE) {

            return "Rescue";

        } else {

            return "Security";
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