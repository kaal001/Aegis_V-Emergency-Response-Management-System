package gui;

import enums.TeamType;
import manager.EmergencyManager;
import model.AmbulanceTeam;
import model.FireTeam;
import model.RescueTeam;
import model.ResponseTeam;
import model.SecurityTeam;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ResponseTeamManagementFrame extends JPanel {

    private MainFrame mainFrame;
    private EmergencyManager manager;

    private JTable teamTable;
    private DefaultTableModel tableModel;

    private JTextField searchField;

    private JComboBox<String> typeFilter;
    private JComboBox<String> availabilityFilter;

    private JTextField teamIdField;
    private JTextField teamNameField;
    private JTextField contactField;
    private JTextField membersField;

    private JComboBox<String> teamTypeComboBox;
    private JComboBox<String> availabilityComboBox;

    private ResponseTeam selectedTeam;

    public ResponseTeamManagementFrame(
            MainFrame mainFrame,
            EmergencyManager manager) {

        this.mainFrame = mainFrame;
        this.manager = manager;

        createUI();

        loadTeams(
                manager.getAllTeams()
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
        // TITLE
        // =========================

        JLabel titleLabel =
                new JLabel(
                        "RESPONSE TEAM MANAGEMENT",
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

        availabilityFilter =
                new JComboBox<>();

        typeFilter.addItem(
                "All Types"
        );

        for (TeamType type :
                TeamType.values()) {

            typeFilter.addItem(
                    formatTeamType(type)
            );
        }

        availabilityFilter.addItem(
                "All Availability"
        );

        availabilityFilter.addItem(
                "Available"
        );

        availabilityFilter.addItem(
                "Busy"
        );

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
                new JLabel("Availability:")
        );

        filterPanel.add(
                availabilityFilter
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

        filterPanel.add(
                searchButton
        );

        filterPanel.add(
                clearButton
        );

        mainPanel.add(
                filterPanel,
                BorderLayout.NORTH
        );

        // =========================
        // TEAM TABLE
        // =========================

        String[] columns = {
                "Team ID",
                "Team Name",
                "Type",
                "Contact",
                "Members",
                "Availability"
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

        teamTable =
                new JTable(
                        tableModel
                );

        teamTable.setRowHeight(
                28
        );

        teamTable.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        teamTable.getTableHeader()
                .setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                12
                        )
                );

        teamTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane tableScrollPane =
                new JScrollPane(
                        teamTable
                );

        mainPanel.add(
                tableScrollPane,
                BorderLayout.CENTER
        );

        // =========================
        // BOTTOM PANEL
        // =========================

        JPanel bottomPanel =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        bottomPanel.setBackground(
                Color.decode("#E8EDDF")
        );

        // =========================
        // FORM
        // =========================

        JPanel formPanel =
                new JPanel(
                        new GridLayout(
                                3,
                                4,
                                10,
                                10
                        )
                );

        formPanel.setBackground(
                Color.decode("#CFDBD5")
        );

        formPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        10,
                        10,
                        10
                )
        );

        teamIdField =
                new JTextField();

        teamNameField =
                new JTextField();

        contactField =
                new JTextField();

        membersField =
                new JTextField();

        teamTypeComboBox =
                new JComboBox<>();

        for (TeamType type :
                TeamType.values()) {

            teamTypeComboBox.addItem(
                    formatTeamType(type)
            );
        }

        availabilityComboBox =
                new JComboBox<>(
                        new String[]{
                                "Available",
                                "Busy"
                        }
                );

        formPanel.add(
                new JLabel("Team ID:")
        );

        formPanel.add(
                teamIdField
        );

        formPanel.add(
                new JLabel("Team Name:")
        );

        formPanel.add(
                teamNameField
        );

        formPanel.add(
                new JLabel("Team Type:")
        );

        formPanel.add(
                teamTypeComboBox
        );

        formPanel.add(
                new JLabel("Contact:")
        );

        formPanel.add(
                contactField
        );

        formPanel.add(
                new JLabel("Members:")
        );

        formPanel.add(
                membersField
        );

        formPanel.add(
                new JLabel("Availability:")
        );

        formPanel.add(
                availabilityComboBox
        );

        bottomPanel.add(
                formPanel,
                BorderLayout.CENTER
        );

        // =========================
        // BUTTONS
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

        JButton addButton =
                new JButton(
                        "ADD TEAM"
                );

        JButton updateButton =
                new JButton(
                        "UPDATE TEAM"
                );

        JButton deleteButton =
                new JButton(
                        "DELETE TEAM"
                );

        JButton clearFormButton =
                new JButton(
                        "CLEAR FORM"
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

        styleSecondaryButton(
                clearFormButton
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
                clearFormButton
        );

        buttonPanel.add(
                backButton
        );

        bottomPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        // =========================
        // TABLE SELECTION
        // =========================

        teamTable
                .getSelectionModel()
                .addListSelectionListener(
                        e -> {

                            if (!e.getValueIsAdjusting()) {

                                selectTeam();
                            }
                        }
                );

        // =========================
        // BUTTON ACTIONS
        // =========================

        searchButton.addActionListener(
                e -> searchTeams()
        );

        clearButton.addActionListener(
                e -> clearSearch()
        );

        addButton.addActionListener(
                e -> addTeam()
        );

        updateButton.addActionListener(
                e -> updateTeam()
        );

        deleteButton.addActionListener(
                e -> deleteTeam()
        );

        clearFormButton.addActionListener(
                e -> clearForm()
        );

        backButton.addActionListener(
                e -> mainFrame.goBack()
        );

        // =========================
        // ADD MAIN PANEL
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
    // LOAD TEAMS
    // =========================

    private void loadTeams(
            ArrayList<ResponseTeam> teams) {

        tableModel.setRowCount(0);

        for (ResponseTeam team :
                teams) {

            String availability;

            if (team.isAvailable()) {

                availability =
                        "Available";

            } else {

                availability =
                        "Busy";
            }

            tableModel.addRow(
                    new Object[]{
                            team.getTeamId(),
                            team.getTeamName(),
                            formatTeamType(
                                    team.getTeamType()
                            ),
                            team.getContactNumber(),
                            team.getNumberOfMembers(),
                            availability
                    }
            );
        }

        selectedTeam = null;
    }

    // =========================
    // SELECT TEAM
    // =========================

    private void selectTeam() {

        int selectedRow =
                teamTable.getSelectedRow();

        if (selectedRow == -1) {

            selectedTeam = null;

            return;
        }

        String teamId =
                tableModel
                        .getValueAt(
                                selectedRow,
                                0
                        )
                        .toString();

        selectedTeam =
                manager.findTeamById(
                        teamId
                );

        if (selectedTeam != null) {

            teamIdField.setText(
                    selectedTeam.getTeamId()
            );

            teamNameField.setText(
                    selectedTeam.getTeamName()
            );

            contactField.setText(
                    selectedTeam.getContactNumber()
            );

            membersField.setText(
                    String.valueOf(
                            selectedTeam
                                    .getNumberOfMembers()
                    )
            );

            teamTypeComboBox.setSelectedItem(
                    formatTeamType(
                            selectedTeam.getTeamType()
                    )
            );

            if (selectedTeam.isAvailable()) {

                availabilityComboBox
                        .setSelectedItem(
                                "Available"
                        );

            } else {

                availabilityComboBox
                        .setSelectedItem(
                                "Busy"
                        );
            }

            teamTypeComboBox.setEnabled(
                    false
            );
        }
    }

    // =========================
    // ADD TEAM
    // =========================

    private void addTeam() {

        String teamId =
                teamIdField
                        .getText()
                        .trim();

        String teamName =
                teamNameField
                        .getText()
                        .trim();

        String contact =
                contactField
                        .getText()
                        .trim();

        String membersText =
                membersField
                        .getText()
                        .trim();

        if (teamId.isEmpty()
                || teamName.isEmpty()
                || contact.isEmpty()
                || membersText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    mainFrame,
                    "Please fill in all team information.",
                    "Missing Information",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (manager.findTeamById(
                teamId
        ) != null) {

            JOptionPane.showMessageDialog(
                    mainFrame,
                    "Team ID already exists.",
                    "Add Team Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        int numberOfMembers;

        try {

            numberOfMembers =
                    Integer.parseInt(
                            membersText
                    );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    mainFrame,
                    "Number of members must be a valid number.",
                    "Invalid Input",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (numberOfMembers <= 0) {

            JOptionPane.showMessageDialog(
                    mainFrame,
                    "Number of members must be greater than zero.",
                    "Invalid Input",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        TeamType teamType =
                convertTeamType(
                        teamTypeComboBox
                                .getSelectedItem()
                                .toString()
                );

        ResponseTeam team =
                createTeam(
                        teamId,
                        teamName,
                        teamType,
                        contact,
                        numberOfMembers
                );

        if (team == null) {

            JOptionPane.showMessageDialog(
                    mainFrame,
                    "Unable to create response team.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        String availability =
                availabilityComboBox
                        .getSelectedItem()
                        .toString();

        if (availability.equals(
                "Available"
        )) {

            team.setAvailable(
                    true
            );

        } else {

            team.setAvailable(
                    false
            );
        }

        manager.addTeam(
                team
        );

        manager.saveData();

        JOptionPane.showMessageDialog(
                mainFrame,
                "Response team added successfully.",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
        );

        clearForm();

        loadTeams(
                manager.getAllTeams()
        );
    }

    // =========================
    // UPDATE TEAM
    // =========================

    private void updateTeam() {

        if (selectedTeam == null) {

            JOptionPane.showMessageDialog(
                    mainFrame,
                    "Please select a team first.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String teamName =
                teamNameField
                        .getText()
                        .trim();

        String contact =
                contactField
                        .getText()
                        .trim();

        String membersText =
                membersField
                        .getText()
                        .trim();

        if (teamName.isEmpty()
                || contact.isEmpty()
                || membersText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    mainFrame,
                    "Please fill in all editable fields.",
                    "Missing Information",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int numberOfMembers;

        try {

            numberOfMembers =
                    Integer.parseInt(
                            membersText
                    );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    mainFrame,
                    "Number of members must be a valid number.",
                    "Invalid Input",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (numberOfMembers <= 0) {

            JOptionPane.showMessageDialog(
                    mainFrame,
                    "Number of members must be greater than zero.",
                    "Invalid Input",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        selectedTeam.setTeamName(
                teamName
        );

        selectedTeam.setContactNumber(
                contact
        );

        selectedTeam.setNumberOfMembers(
                numberOfMembers
        );

        String availability =
                availabilityComboBox
                        .getSelectedItem()
                        .toString();

        if (availability.equals(
                "Available"
        )) {

            selectedTeam.setAvailable(
                    true
            );

        } else {

            selectedTeam.setAvailable(
                    false
            );
        }

        manager.saveData();

        JOptionPane.showMessageDialog(
                mainFrame,
                "Response team updated successfully.",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
        );

        clearForm();

        loadTeams(
                manager.getAllTeams()
        );
    }

    // =========================
    // DELETE TEAM
    // =========================

    private void deleteTeam() {

        if (selectedTeam == null) {

            JOptionPane.showMessageDialog(
                    mainFrame,
                    "Please select a team first.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int choice =
                JOptionPane.showConfirmDialog(
                        mainFrame,
                        "Are you sure you want to delete "
                                + selectedTeam.getTeamName()
                                + "?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION
                );

        if (choice !=
                JOptionPane.YES_OPTION) {

            return;
        }

        manager.removeTeam(
                selectedTeam.getTeamId()
        );

        manager.saveData();

        JOptionPane.showMessageDialog(
                mainFrame,
                "Response team deleted successfully.",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
        );

        clearForm();

        loadTeams(
                manager.getAllTeams()
        );
    }

    // =========================
    // SEARCH TEAMS
    // =========================

    private void searchTeams() {

        String keyword =
                searchField
                        .getText()
                        .trim()
                        .toLowerCase();

        String selectedType =
                typeFilter
                        .getSelectedItem()
                        .toString();

        String selectedAvailability =
                availabilityFilter
                        .getSelectedItem()
                        .toString();

        ArrayList<ResponseTeam> result =
                new ArrayList<>();

        for (ResponseTeam team :
                manager.getAllTeams()) {

            boolean keywordMatch =
                    keyword.isEmpty()
                            || team.getTeamId()
                            .toLowerCase()
                            .contains(keyword)
                            || team.getTeamName()
                            .toLowerCase()
                            .contains(keyword)
                            || team.getContactNumber()
                            .toLowerCase()
                            .contains(keyword);

            boolean typeMatch =
                    selectedType.equals(
                            "All Types"
                    )
                            || formatTeamType(
                            team.getTeamType()
                    ).equals(
                            selectedType
                    );

            String availability =
                    team.isAvailable()
                            ? "Available"
                            : "Busy";

            boolean availabilityMatch =
                    selectedAvailability.equals(
                            "All Availability"
                    )
                            || availability.equals(
                            selectedAvailability
                    );

            if (keywordMatch
                    && typeMatch
                    && availabilityMatch) {

                result.add(
                        team
                );
            }
        }

        loadTeams(
                result
        );
    }

    // =========================
    // CLEAR SEARCH
    // =========================

    private void clearSearch() {

        searchField.setText(
                ""
        );

        typeFilter.setSelectedIndex(
                0
        );

        availabilityFilter.setSelectedIndex(
                0
        );

        loadTeams(
                manager.getAllTeams()
        );
    }

    // =========================
    // CLEAR FORM
    // =========================

    private void clearForm() {

        teamIdField.setText(
                ""
        );

        teamNameField.setText(
                ""
        );

        contactField.setText(
                ""
        );

        membersField.setText(
                ""
        );

        teamTypeComboBox.setSelectedIndex(
                0
        );

        availabilityComboBox.setSelectedIndex(
                0
        );

        teamTypeComboBox.setEnabled(
                true
        );

        teamTable.clearSelection();

        selectedTeam = null;
    }

    // =========================
    // CREATE TEAM OBJECT
    // =========================

    private ResponseTeam createTeam(
            String teamId,
            String teamName,
            TeamType teamType,
            String contact,
            int members) {

        if (teamType ==
                TeamType.AMBULANCE) {

            return new AmbulanceTeam(
                    teamId,
                    teamName,
                    contact,
                    members
            );

        } else if (teamType ==
                TeamType.FIRE) {

            return new FireTeam(
                    teamId,
                    teamName,
                    contact,
                    members
            );

        } else if (teamType ==
                TeamType.RESCUE) {

            return new RescueTeam(
                    teamId,
                    teamName,
                    contact,
                    members
            );

        } else {

            return new SecurityTeam(
                    teamId,
                    teamName,
                    contact,
                    members
            );
        }
    }

    // =========================
    // CONVERT TEAM TYPE
    // =========================

    private TeamType convertTeamType(
            String type) {

        if (type.equals(
                "Ambulance"
        )) {

            return TeamType.AMBULANCE;

        } else if (type.equals(
                "Fire"
        )) {

            return TeamType.FIRE;

        } else if (type.equals(
                "Rescue"
        )) {

            return TeamType.RESCUE;

        } else {

            return TeamType.SECURITY;
        }
    }

    // =========================
    // FORMAT TEAM TYPE
    // =========================

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