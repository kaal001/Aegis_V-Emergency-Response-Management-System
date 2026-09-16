package gui;

import enums.EmergencyType;
import enums.Priority;
import manager.EmergencyManager;
import model.Emergency;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportEmergencyFrame extends JPanel {

    private EmergencyManager manager;
    private User user;

    private Runnable onBack;
    private Runnable onSubmitted;

    private JComboBox<String> typeComboBox;
    private JComboBox<String> priorityComboBox;
    private JTextField locationField;
    private JTextArea descriptionArea;

    public ReportEmergencyFrame(
            EmergencyManager manager,
            User user,
            Runnable onBack,
            Runnable onSubmitted) {

        this.manager = manager;
        this.user = user;
        this.onBack = onBack;
        this.onSubmitted = onSubmitted;

        setLayout(
                new BorderLayout()
        );

        setBackground(
                Color.decode("#E8EDDF")
        );

        createReportUI();
    }

    private void createReportUI() {

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout()
                );

        mainPanel.setBackground(
                Color.decode("#E8EDDF")
        );

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        25,
                        20,
                        25
                )
        );

        // =========================
        // HEADER
        // =========================

        JLabel titleLabel =
                new JLabel(
                        "REPORT EMERGENCY",
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
                        10,
                        20,
                        10
                )
        );

        mainPanel.add(
                titleLabel,
                BorderLayout.NORTH
        );

        // =========================
        // FORM PANEL
        // =========================

        JPanel formPanel =
                new JPanel(
                        new GridBagLayout()
                );

        formPanel.setBackground(
                Color.decode("#CFDBD5")
        );

        formPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                Color.decode("#B8C4BE")
                        ),
                        BorderFactory.createEmptyBorder(
                                20,
                                30,
                                20,
                                30
                        )
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        8,
                        8,
                        8,
                        8
                );

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.anchor =
                GridBagConstraints.WEST;

        // =========================
        // EMERGENCY TYPE
        // =========================

        JLabel typeLabel =
                new JLabel(
                        "Emergency Type:"
                );

        typeComboBox =
                new JComboBox<>(
                        new String[]{
                                "Medical Emergency",
                                "Fire Emergency",
                                "Road Accident",
                                "Security Emergency",
                                "Natural Disaster",
                                "Gas Leak",
                                "Electrical Emergency",
                                "Building Collapse",
                                "Industrial Accident",
                                "Missing Person",
                                "Water / Flood Emergency",
                                "Other / Custom"
                        }
                );

        addFormRow(
                formPanel,
                gbc,
                0,
                typeLabel,
                typeComboBox
        );

        // =========================
        // PRIORITY
        // =========================

        JLabel priorityLabel =
                new JLabel(
                        "Priority:"
                );

        priorityComboBox =
                new JComboBox<>(
                        new String[]{
                                "Critical",
                                "High",
                                "Medium",
                                "Low"
                        }
                );

        addFormRow(
                formPanel,
                gbc,
                1,
                priorityLabel,
                priorityComboBox
        );

        // =========================
        // LOCATION
        // =========================

        JLabel locationLabel =
                new JLabel(
                        "Location:"
                );

        locationField =
                new JTextField();

        locationField.setToolTipText(
                "Enter building/area, floor, room, or nearby landmark."
        );

        addFormRow(
                formPanel,
                gbc,
                2,
                locationLabel,
                locationField
        );

        // =========================
        // LOCATION HINT
        // =========================

        JLabel locationHint =
                new JLabel(
                        "Enter a detailed location: building/area, floor, room, or nearby landmark."
                );

        locationHint.setFont(
                new Font(
                        "Arial",
                        Font.ITALIC,
                        11
                )
        );

        locationHint.setForeground(
                Color.decode("#333533")
        );

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        formPanel.add(
                locationHint,
                gbc
        );

        // =========================
        // DESCRIPTION
        // =========================

        JLabel descriptionLabel =
                new JLabel(
                        "Description:"
                );

        descriptionArea =
                new JTextArea(
                        7,
                        25
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

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0;
        gbc.weighty = 1.0;
        gbc.fill =
                GridBagConstraints.BOTH;

        formPanel.add(
                descriptionLabel,
                gbc
        );

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill =
                GridBagConstraints.BOTH;

        formPanel.add(
                descriptionScrollPane,
                gbc
        );

        mainPanel.add(
                formPanel,
                BorderLayout.CENTER
        );

        // =========================
        // BUTTON PANEL
        // =========================

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                15,
                                10
                        )
                );

        buttonPanel.setBackground(
                Color.decode("#E8EDDF")
        );

        JButton submitButton =
                new JButton(
                        "SUBMIT EMERGENCY"
                );

        JButton cancelButton =
                new JButton(
                        "CANCEL"
                );

        stylePrimaryButton(
                submitButton
        );

        styleSecondaryButton(
                cancelButton
        );

        buttonPanel.add(
                submitButton
        );

        buttonPanel.add(
                cancelButton
        );

        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        // =========================
        // BUTTON ACTIONS
        // =========================

        submitButton.addActionListener(
                e -> submitEmergency()
        );

        cancelButton.addActionListener(
                e -> {

                    if (onBack != null) {

                        onBack.run();
                    }
                }
        );

        add(
                mainPanel,
                BorderLayout.CENTER
        );
    }

    // =========================
    // ADD FORM ROW
    // =========================

    private void addFormRow(
            JPanel panel,
            GridBagConstraints gbc,
            int row,
            JLabel label,
            JComponent component) {

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        panel.add(
                label,
                gbc
        );

        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.weightx = 1.0;
        gbc.weighty = 0;

        panel.add(
                component,
                gbc
        );
    }

    // =========================
    // SUBMIT EMERGENCY
    // =========================

    private void submitEmergency() {

        String location =
                locationField
                        .getText()
                        .trim();

        String description =
                descriptionArea
                        .getText()
                        .trim();

        // =========================
        // VALIDATION
        // =========================

        if (location.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter the emergency location.",
                    "Missing Information",
                    JOptionPane.WARNING_MESSAGE
            );

            locationField.requestFocus();

            return;
        }

        if (description.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please describe the emergency.",
                    "Missing Information",
                    JOptionPane.WARNING_MESSAGE
            );

            descriptionArea.requestFocus();

            return;
        }

        // =========================
        // GET TYPE / PRIORITY
        // =========================

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

        // =========================
        // GENERATE ID
        // =========================

        String emergencyId =
                generateEmergencyId();

        // =========================
        // DATE / TIME
        // =========================

        String dateTime =
                new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm"
                ).format(
                        new Date()
                );

        // =========================
        // CREATE EMERGENCY
        // =========================

        Emergency emergency =
                new Emergency(
                        emergencyId,
                        user.getId(),
                        type,
                        priority,
                        location,
                        description,
                        dateTime
                );

        // =========================
        // ADD TO MANAGER
        // =========================

        boolean added =
                manager.addEmergency(
                        emergency
                );

        if (!added) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to report this emergency.\n"
                            + "Please try again.",
                    "Report Failed",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        // =========================
        // SAVE DATA
        // =========================

        manager.saveData();

        // =========================
        // SUCCESS MESSAGE
        // =========================

        JOptionPane.showMessageDialog(
                this,
                "Emergency reported successfully.\n\n"
                        + "Emergency ID: "
                        + emergencyId
                        + "\nStatus: "
                        + emergency.getStatus(),
                "Report Successful",
                JOptionPane.INFORMATION_MESSAGE
        );

        // =========================
        // CALLBACK
        // =========================

        if (onSubmitted != null) {

            onSubmitted.run();

        } else if (onBack != null) {

            onBack.run();
        }
    }

    // =========================
    // GENERATE EMERGENCY ID
    // =========================

    private String generateEmergencyId() {

        int highestNumber = 1000;

        for (Emergency emergency :
                manager.getEmergencies()) {

            String emergencyId =
                    emergency.getEmergencyId();

            if (emergencyId == null) {
                continue;
            }

            if (emergencyId.startsWith("ER-")) {

                try {

                    int number =
                            Integer.parseInt(
                                    emergencyId.substring(3)
                            );

                    if (number > highestNumber) {

                        highestNumber = number;
                    }

                } catch (
                        NumberFormatException e) {

                    // Ignore invalid emergency IDs
                }
            }
        }

        return "ER-" + (highestNumber + 1);
    }

    // =========================
    // CONVERT TYPE
    // =========================

    private EmergencyType convertEmergencyType(String type) {

        switch (type) {

            case "Medical Emergency":
                return EmergencyType.MEDICAL;

            case "Fire Emergency":
                return EmergencyType.FIRE;

            case "Road Accident":
                return EmergencyType.ROAD_ACCIDENT;

            case "Security Emergency":
                return EmergencyType.SECURITY;

            case "Natural Disaster":
                return EmergencyType.NATURAL_DISASTER;

            case "Gas Leak":
                return EmergencyType.GAS_LEAK;

            case "Electrical Emergency":
                return EmergencyType.ELECTRICAL_EMERGENCY;

            case "Building Collapse":
                return EmergencyType.BUILDING_COLLAPSE;

            case "Industrial Accident":
                return EmergencyType.INDUSTRIAL_ACCIDENT;

            case "Missing Person":
                return EmergencyType.MISSING_PERSON;

            case "Water / Flood Emergency":
                return EmergencyType.WATER_FLOOD_EMERGENCY;

            case "Other / Custom":
                return EmergencyType.CUSTOM;

            default:
                return EmergencyType.CUSTOM;
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

        } else if (
                priority.equals("High")
        ) {

            return Priority.HIGH;

        } else if (
                priority.equals("Medium")
        ) {

            return Priority.MEDIUM;

        } else {

            return Priority.LOW;
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
                        14
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
                        14
                )
        );

        button.setFocusPainted(
                false
        );
    }
}