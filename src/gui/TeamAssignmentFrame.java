package gui;

import enums.EmergencyStatus;
import enums.EmergencyType;
import manager.EmergencyManager;
import model.Assignment;
import model.Emergency;
import model.ResponseTeam;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TeamAssignmentFrame extends JPanel {

    private MainFrame mainFrame;
    private EmergencyManager manager;

    private JTable assignmentTable;
    private DefaultTableModel assignmentTableModel;

    private JTextField searchField;

    public TeamAssignmentFrame(
            MainFrame mainFrame,
            EmergencyManager manager) {

        this.mainFrame = mainFrame;
        this.manager = manager;

        createUI();
        loadAssignments();
    }

    // =========================================
    // CREATE UI
    // =========================================

    private void createUI() {

        setLayout(
                new BorderLayout()
        );

        setBackground(
                Color.decode("#E8EDDF")
        );

        // =========================================
        // MAIN PANEL
        // =========================================

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

        JPanel headerPanel =
                new JPanel(
                        new BorderLayout()
                );

        headerPanel.setBackground(
                Color.decode("#E8EDDF")
        );

        JLabel titleLabel =
                new JLabel(
                        "ASSIGNMENT MANAGEMENT"
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

        headerPanel.add(
                titleLabel,
                BorderLayout.WEST
        );

        // =========================================
        // SEARCH PANEL
        // =========================================

        JPanel searchPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                8,
                                0
                        )
                );

        searchPanel.setBackground(
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
                        12
                )
        );

        searchField =
                new JTextField(
                        18
                );

        JButton refreshButton =
                new JButton(
                        "REFRESH"
                );

        stylePrimaryButton(
                refreshButton
        );

        searchPanel.add(
                searchLabel
        );

        searchPanel.add(
                searchField
        );

        searchPanel.add(
                refreshButton
        );

        headerPanel.add(
                searchPanel,
                BorderLayout.EAST
        );

        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );

        // =========================================
        // TABLE
        // =========================================

        String[] columns = {
                "Assignment ID",
                "Emergency ID",
                "Team ID",
                "Assigned Time",
                "Notes"
        };

        assignmentTableModel =
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

        assignmentTable =
                new JTable(
                        assignmentTableModel
                );

        assignmentTable.setRowHeight(
                28
        );

        assignmentTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        assignmentTable.getTableHeader()
                .setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                12
                        )
                );

        assignmentTable.getColumnModel()
                .getColumn(0)
                .setPreferredWidth(110);

        assignmentTable.getColumnModel()
                .getColumn(1)
                .setPreferredWidth(110);

        assignmentTable.getColumnModel()
                .getColumn(2)
                .setPreferredWidth(110);

        assignmentTable.getColumnModel()
                .getColumn(3)
                .setPreferredWidth(150);

        assignmentTable.getColumnModel()
                .getColumn(4)
                .setPreferredWidth(250);

        JScrollPane tableScrollPane =
                new JScrollPane(
                        assignmentTable
                );

        mainPanel.add(
                tableScrollPane,
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
                                8
                        )
                );

        buttonPanel.setBackground(
                Color.decode("#CFDBD5")
        );

        JButton addButton =
                new JButton(
                        "ADD"
                );

        JButton updateButton =
                new JButton(
                        "UPDATE"
                );

        JButton deleteButton =
                new JButton(
                        "DELETE"
                );

        JButton refreshAllButton =
                new JButton(
                        "REFRESH"
                );

        JButton backButton =
                new JButton(
                        "BACK"
                );

        stylePrimaryButton(
                addButton
        );

        stylePrimaryButton(
                updateButton
        );

        stylePrimaryButton(
                deleteButton
        );

        stylePrimaryButton(
                refreshAllButton
        );

        styleSecondaryButton(
                backButton
        );

        buttonPanel.add(
                addButton
        );

        buttonPanel.add(
                updateButton
        );

        buttonPanel.add(
                deleteButton
        );

        buttonPanel.add(
                refreshAllButton
        );

        buttonPanel.add(
                backButton
        );

        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        // =========================================
        // EVENTS
        // =========================================

        addButton.addActionListener(
                e -> addAssignment()
        );

        updateButton.addActionListener(
                e -> updateAssignment()
        );

        deleteButton.addActionListener(
                e -> deleteAssignment()
        );

        refreshButton.addActionListener(
                e -> loadAssignments()
        );

        refreshAllButton.addActionListener(
                e -> loadAssignments()
        );

        backButton.addActionListener(
                e -> mainFrame.goBack()
        );

        // =========================================
        // LIVE SEARCH
        // =========================================

        searchField.getDocument()
                .addDocumentListener(
                        new DocumentListener() {

                            @Override
                            public void insertUpdate(
                                    DocumentEvent e) {

                                performSearch();
                            }

                            @Override
                            public void removeUpdate(
                                    DocumentEvent e) {

                                performSearch();
                            }

                            @Override
                            public void changedUpdate(
                                    DocumentEvent e) {

                                performSearch();
                            }
                        }
                );

        // =========================================
        // ADD TO PANEL
        // =========================================

        add(
                mainPanel,
                BorderLayout.CENTER
        );
    }

    // =========================================
    // LOAD ASSIGNMENTS
    // =========================================

    private void loadAssignments() {

        assignmentTableModel.setRowCount(
                0
        );

        ArrayList<Assignment> assignments =
                new ArrayList<>(
                        manager.getAllAssignments()
                );

        for (Assignment assignment :
                assignments) {

            assignmentTableModel.addRow(
                    new Object[]{
                            assignment.getAssignmentId(),
                            assignment.getEmergencyId(),
                            assignment.getTeamId(),
                            assignment.getAssignedTime(),
                            assignment.getNotes()
                    }
            );
        }
    }

    // =========================================
    // SEARCH
    // =========================================

    private void performSearch() {

        String keyword =
                searchField.getText()
                        .trim();

        assignmentTableModel.setRowCount(
                0
        );

        ArrayList<Assignment> assignments;

        if (keyword.isEmpty()) {

            assignments =
                    new ArrayList<>(
                            manager.getAllAssignments()
                    );

        } else {

            assignments =
                    new ArrayList<>(
                            manager.searchAssignments(
                                    keyword
                            )
                    );
        }

        for (Assignment assignment :
                assignments) {

            assignmentTableModel.addRow(
                    new Object[]{
                            assignment.getAssignmentId(),
                            assignment.getEmergencyId(),
                            assignment.getTeamId(),
                            assignment.getAssignedTime(),
                            assignment.getNotes()
                    }
            );
        }
    }

    // =========================================
    // ADD ASSIGNMENT
    // =========================================

    private void addAssignment() {

        ArrayList<Emergency> pendingEmergencies =
                getPendingEmergencies();

        if (pendingEmergencies.isEmpty()) {

            JOptionPane.showMessageDialog(
                    mainFrame,
                    "There are no pending emergencies available.",
                    "Add Assignment",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        JComboBox<String> emergencyComboBox =
                new JComboBox<>();

        for (Emergency emergency :
                pendingEmergencies) {

            emergencyComboBox.addItem(
                    emergency.getEmergencyId()
                            + " - "
                            + formatEmergencyType(
                            emergency.getType()
                    )
                            + " - "
                            + emergency.getLocation()
            );
        }

        JComboBox<String> teamComboBox =
                new JComboBox<>();

        JTextField timeField =
                new JTextField(
                        getCurrentDateTime()
                );

        JTextField notesField =
                new JTextField();

        JPanel panel =
                new JPanel(
                        new GridLayout(
                                4,
                                2,
                                8,
                                8
                        )
                );

        panel.setPreferredSize(
                new Dimension(
                        500,
                        150
                )
        );

        panel.add(
                new JLabel(
                        "Emergency:"
                )
        );

        panel.add(
                emergencyComboBox
        );

        panel.add(
                new JLabel(
                        "Suitable Team:"
                )
        );

        panel.add(
                teamComboBox
        );

        panel.add(
                new JLabel(
                        "Assigned Time:"
                )
        );

        panel.add(
                timeField
        );

        panel.add(
                new JLabel(
                        "Notes:"
                )
        );

        panel.add(
                notesField
        );

        Runnable loadTeams =
                () -> {

                    teamComboBox.removeAllItems();

                    int selectedIndex =
                            emergencyComboBox
                                    .getSelectedIndex();

                    if (selectedIndex < 0) {

                        return;
                    }

                    Emergency emergency =
                            pendingEmergencies
                                    .get(selectedIndex);

                    ArrayList<ResponseTeam> teams =
                            manager.findSuitableTeams(
                                    emergency
                            );

                    for (ResponseTeam team :
                            teams) {

                        teamComboBox.addItem(
                                team.getTeamId()
                                        + " - "
                                        + team.getTeamName()
                        );
                    }
                };

        emergencyComboBox.addActionListener(
                e -> loadTeams.run()
        );

        loadTeams.run();

        int result =
                JOptionPane.showConfirmDialog(
                        mainFrame,
                        panel,
                        "Add Assignment",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

        if (result !=
                JOptionPane.OK_OPTION) {

            return;
        }

        // =========================================
        // VALIDATION
        // =========================================

        if (emergencyComboBox
                .getSelectedIndex() < 0) {

            JOptionPane.showMessageDialog(
                    mainFrame,
                    "Please select an emergency.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (teamComboBox
                .getSelectedIndex() < 0) {

            JOptionPane.showMessageDialog(
                    mainFrame,
                    "No suitable available team is selected.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String assignedTime =
                timeField.getText()
                        .trim();

        if (assignedTime.isEmpty()) {

            JOptionPane.showMessageDialog(
                    mainFrame,
                    "Assigned time cannot be empty.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String emergencyId =
                pendingEmergencies
                        .get(
                                emergencyComboBox
                                        .getSelectedIndex()
                        )
                        .getEmergencyId();

        String selectedTeam =
                teamComboBox
                        .getSelectedItem()
                        .toString();

        String teamId =
                selectedTeam.split(
                        " - ",
                        2
                )[0];

        String notes =
                notesField.getText()
                        .trim();

        boolean success =
                manager.addAssignment(
                        emergencyId,
                        teamId,
                        assignedTime,
                        notes
                );

        if (success) {

            manager.saveData();

            JOptionPane.showMessageDialog(
                    mainFrame,
                    "Assignment added successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadAssignments();

        } else {

            JOptionPane.showMessageDialog(
                    mainFrame,
                    "Unable to create the assignment.\n"
                            + "Check emergency status, team availability, "
                            + "and team suitability.",
                    "Add Assignment Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================
    // UPDATE ASSIGNMENT
    // =========================================

    private void updateAssignment() {

        int selectedRow =
                assignmentTable.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                    mainFrame,
                    "Please select an assignment first.",
                    "Update Assignment",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String assignmentId =
                assignmentTableModel
                        .getValueAt(
                                selectedRow,
                                0
                        )
                        .toString();

        Assignment assignment =
                manager.findAssignmentById(
                        assignmentId
                );

        if (assignment == null) {

            JOptionPane.showMessageDialog(
                    mainFrame,
                    "Assignment not found.",
                    "Update Assignment",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        Emergency currentEmergency =
                manager.findEmergencyById(
                        assignment.getEmergencyId()
                );

        if (currentEmergency == null) {

            JOptionPane.showMessageDialog(
                    mainFrame,
                    "The emergency connected to this assignment could not be found.",
                    "Update Assignment",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        ArrayList<Emergency> emergencies =
                getUpdateEmergencyList(
                        currentEmergency
                );

        JComboBox<String> emergencyComboBox =
                new JComboBox<>();

        int currentEmergencyIndex =
                0;

        for (int i = 0;
             i < emergencies.size();
             i++) {

            Emergency emergency =
                    emergencies.get(i);

            emergencyComboBox.addItem(
                    emergency.getEmergencyId()
                            + " - "
                            + formatEmergencyType(
                            emergency.getType()
                    )
                            + " - "
                            + emergency.getLocation()
            );

            if (emergency.getEmergencyId()
                    .equals(
                            assignment.getEmergencyId()
                    )) {

                currentEmergencyIndex =
                        i;
            }
        }

        emergencyComboBox.setSelectedIndex(
                currentEmergencyIndex
        );

        JComboBox<String> teamComboBox =
                new JComboBox<>();

        JTextField timeField =
                new JTextField(
                        assignment.getAssignedTime()
                );

        JTextField notesField =
                new JTextField(
                        assignment.getNotes()
                );

        JPanel panel =
                new JPanel(
                        new GridLayout(
                                4,
                                2,
                                8,
                                8
                        )
                );

        panel.setPreferredSize(
                new Dimension(
                        500,
                        150
                )
        );

        panel.add(
                new JLabel(
                        "Emergency:"
                )
        );

        panel.add(
                emergencyComboBox
        );

        panel.add(
                new JLabel(
                        "Suitable Team:"
                )
        );

        panel.add(
                teamComboBox
        );

        panel.add(
                new JLabel(
                        "Assigned Time:"
                )
        );

        panel.add(
                timeField
        );

        panel.add(
                new JLabel(
                        "Notes:"
                )
        );

        panel.add(
                notesField
        );

        Runnable loadUpdateTeams =
                () -> {

                    teamComboBox.removeAllItems();

                    int selectedIndex =
                            emergencyComboBox
                                    .getSelectedIndex();

                    if (selectedIndex < 0) {

                        return;
                    }

                    Emergency emergency =
                            emergencies
                                    .get(selectedIndex);

                    ArrayList<ResponseTeam> teams =
                            manager.findSuitableTeams(
                                    emergency
                            );

                    /*
                     * The currently assigned team can remain
                     * selected during an update because it is
                     * already occupied by this assignment.
                     */
                    ResponseTeam currentTeam =
                            manager.findTeamById(
                                    assignment.getTeamId()
                            );

                    if (currentTeam != null) {

                        boolean alreadyExists =
                                false;

                        for (ResponseTeam team :
                                teams) {

                            if (team.getTeamId()
                                    .equals(
                                            currentTeam.getTeamId()
                                    )) {

                                alreadyExists =
                                        true;

                                break;
                            }
                        }

                        if (!alreadyExists &&
                                emergency.getEmergencyId()
                                        .equals(
                                                currentEmergency
                                                        .getEmergencyId()
                                        )) {

                            teams.add(
                                    currentTeam
                            );
                        }
                    }

                    for (ResponseTeam team :
                            teams) {

                        teamComboBox.addItem(
                                team.getTeamId()
                                        + " - "
                                        + team.getTeamName()
                        );
                    }

                    for (int i = 0;
                         i < teamComboBox.getItemCount();
                         i++) {

                        String item =
                                teamComboBox
                                        .getItemAt(i);

                        if (item.startsWith(
                                assignment.getTeamId()
                                        + " - "
                        )) {

                            teamComboBox.setSelectedIndex(
                                    i
                            );

                            break;
                        }
                    }
                };

        emergencyComboBox.addActionListener(
                e -> loadUpdateTeams.run()
        );

        loadUpdateTeams.run();

        int result =
                JOptionPane.showConfirmDialog(
                        mainFrame,
                        panel,
                        "Update Assignment",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

        if (result !=
                JOptionPane.OK_OPTION) {

            return;
        }

        // =========================================
        // VALIDATION
        // =========================================

        if (emergencyComboBox
                .getSelectedIndex() < 0) {

            JOptionPane.showMessageDialog(
                    mainFrame,
                    "Please select an emergency.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (teamComboBox
                .getSelectedIndex() < 0) {

            JOptionPane.showMessageDialog(
                    mainFrame,
                    "Please select a suitable team.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String assignedTime =
                timeField.getText()
                        .trim();

        if (assignedTime.isEmpty()) {

            JOptionPane.showMessageDialog(
                    mainFrame,
                    "Assigned time cannot be empty.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String newEmergencyId =
                emergencies
                        .get(
                                emergencyComboBox
                                        .getSelectedIndex()
                        )
                        .getEmergencyId();

        String selectedTeam =
                teamComboBox
                        .getSelectedItem()
                        .toString();

        String newTeamId =
                selectedTeam.split(
                        " - ",
                        2
                )[0];

        String notes =
                notesField.getText()
                        .trim();

        boolean success =
                manager.updateAssignment(
                        assignmentId,
                        newEmergencyId,
                        newTeamId,
                        assignedTime,
                        notes
                );

        if (success) {

            manager.saveData();

            JOptionPane.showMessageDialog(
                    mainFrame,
                    "Assignment updated successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadAssignments();

        } else {

            JOptionPane.showMessageDialog(
                    mainFrame,
                    "Unable to update the assignment.\n"
                            + "Make sure the selected emergency is valid "
                            + "and the team is suitable and available.",
                    "Update Assignment Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================
    // DELETE ASSIGNMENT
    // =========================================

    private void deleteAssignment() {

        int selectedRow =
                assignmentTable.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                    mainFrame,
                    "Please select an assignment first.",
                    "Delete Assignment",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String assignmentId =
                assignmentTableModel
                        .getValueAt(
                                selectedRow,
                                0
                        )
                        .toString();

        int choice =
                JOptionPane.showConfirmDialog(
                        mainFrame,
                        "Are you sure you want to delete assignment "
                                + assignmentId
                                + "?\n\n"
                                + "The assigned team will be released "
                                + "and the emergency will return to Pending.",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

        if (choice !=
                JOptionPane.YES_OPTION) {

            return;
        }

        boolean success =
                manager.removeAssignment(
                        assignmentId
                );

        if (success) {

            manager.saveData();

            JOptionPane.showMessageDialog(
                    mainFrame,
                    "Assignment deleted successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadAssignments();

        } else {

            JOptionPane.showMessageDialog(
                    mainFrame,
                    "Assignment could not be deleted.\n"
                            + "Only an active Assigned assignment "
                            + "can be removed.",
                    "Delete Assignment Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================
    // GET PENDING EMERGENCIES
    // =========================================

    private ArrayList<Emergency>
    getPendingEmergencies() {

        ArrayList<Emergency> result =
                new ArrayList<>();

        ArrayList<Emergency> emergencies =
                new ArrayList<>(
                        manager.getAllEmergencies()
                );

        for (Emergency emergency :
                emergencies) {

            if (emergency.getStatus()
                    == EmergencyStatus.PENDING) {

                result.add(
                        emergency
                );
            }
        }

        return result;
    }

    // =========================================
    // GET UPDATE EMERGENCIES
    // =========================================

    private ArrayList<Emergency>
    getUpdateEmergencyList(
            Emergency currentEmergency) {

        ArrayList<Emergency> result =
                new ArrayList<>();

        ArrayList<Emergency> emergencies =
                new ArrayList<>(
                        manager.getAllEmergencies()
                );

        for (Emergency emergency :
                emergencies) {

            if (emergency.getEmergencyId()
                    .equals(
                            currentEmergency
                                    .getEmergencyId()
                    )) {

                result.add(
                        emergency
                );

            } else if (emergency.getStatus()
                    == EmergencyStatus.PENDING) {

                result.add(
                        emergency
                );
            }
        }

        return result;
    }

    // =========================================
    // CURRENT DATE / TIME
    // =========================================

    private String getCurrentDateTime() {

        return new SimpleDateFormat(
                "yyyy-MM-dd HH:mm"
        ).format(
                new Date()
        );
    }

    // =========================================
    // FORMAT EMERGENCY TYPE
    // =========================================

    private String formatEmergencyType(
            EmergencyType type) {

        if (type == null) {

            return "Unknown";
        }

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

        button.setBorder(
                BorderFactory.createEmptyBorder(
                        8,
                        14,
                        8,
                        14
                )
        );
    }
}