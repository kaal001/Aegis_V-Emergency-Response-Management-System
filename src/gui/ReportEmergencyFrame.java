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

public class ReportEmergencyFrame extends JFrame {

    private EmergencyManager manager;
    private User user;

    private JComboBox<String> typeComboBox;
    private JComboBox<String> priorityComboBox;
    private JTextField locationField;
    private JTextArea descriptionArea;

    public ReportEmergencyFrame(
            EmergencyManager manager,
            User user) {

        this.manager = manager;
        this.user = user;

        setTitle("Report Emergency");
        setSize(650, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        createReportUI();
    }

    private void createReportUI() {

        JPanel mainPanel =
                new JPanel(new BorderLayout());

        mainPanel.setBackground(
                Color.decode("#E8EDDF")
        );

        // =========================
        // Header
        // =========================

        JLabel titleLabel = new JLabel(
                "REPORT EMERGENCY",
                SwingConstants.CENTER
        );

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        titleLabel.setForeground(
                Color.decode("#242423")
        );

        titleLabel.setBorder(
                BorderFactory.createEmptyBorder(
                        25, 10, 20, 10
                )
        );

        mainPanel.add(
                titleLabel,
                BorderLayout.NORTH
        );

        // =========================
        // Form Panel
        // =========================

        JPanel formPanel = new JPanel(
                new GridBagLayout()
        );

        formPanel.setBackground(
                Color.decode("#CFDBD5")
        );

        formPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 35, 20, 35
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(8, 8, 8, 8);

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.anchor =
                GridBagConstraints.WEST;

        // =========================
        // Emergency Type
        // =========================

        JLabel typeLabel =
                new JLabel("Emergency Type:");

        String[] emergencyTypes = {
                "Medical Emergency",
                "Fire Emergency",
                "Road Accident",
                "Security Emergency",
                "Natural Disaster"
        };

        typeComboBox =
                new JComboBox<>(emergencyTypes);

        addFormRow(
                formPanel,
                gbc,
                0,
                typeLabel,
                typeComboBox
        );

        // =========================
        // Priority
        // =========================

        JLabel priorityLabel =
                new JLabel("Priority:");

        String[] priorities = {
                "Critical",
                "High",
                "Medium",
                "Low"
        };

        priorityComboBox =
                new JComboBox<>(priorities);

        addFormRow(
                formPanel,
                gbc,
                1,
                priorityLabel,
                priorityComboBox
        );

        // =========================
        // Location
        // =========================

        JLabel locationLabel =
                new JLabel("Location:");

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
        // Location Instruction
        // =========================

        JLabel locationHint =
                new JLabel(
                        "Enter a detailed location: building/area, floor, room, or nearby landmark."
                );

        locationHint.setFont(
                new Font("Arial", Font.ITALIC, 11)
        );

        locationHint.setForeground(
                Color.decode("#333533")
        );

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1.0;

        formPanel.add(
                locationHint,
                gbc
        );

        // =========================
        // Description
        // =========================

        JLabel descriptionLabel =
                new JLabel("Description:");

        descriptionArea =
                new JTextArea(7, 25);

        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);

        JScrollPane descriptionScrollPane =
                new JScrollPane(descriptionArea);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0;

        formPanel.add(
                descriptionLabel,
                gbc
        );

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        formPanel.add(
                descriptionScrollPane,
                gbc
        );

        mainPanel.add(
                formPanel,
                BorderLayout.CENTER
        );

        // =========================
        // Button Panel
        // =========================

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                15,
                                15
                        )
                );

        buttonPanel.setBackground(
                Color.decode("#E8EDDF")
        );

        JButton submitButton =
                new JButton("SUBMIT EMERGENCY");

        JButton cancelButton =
                new JButton("CANCEL");

        stylePrimaryButton(submitButton);
        styleSecondaryButton(cancelButton);

        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);

        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        // =========================
        // Button Actions
        // =========================

        submitButton.addActionListener(
                e -> submitEmergency()
        );

        cancelButton.addActionListener(
                e -> dispose()
        );

        add(mainPanel);
    }

    // =========================
    // Add Form Row
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

        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.weightx = 1.0;

        panel.add(component, gbc);
    }

    // =========================
    // Submit Emergency
    // =========================

    private void submitEmergency() {

        String location =
                locationField.getText().trim();

        String description =
                descriptionArea.getText().trim();

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

        EmergencyType type =
                convertEmergencyType(
                        typeComboBox.getSelectedItem().toString()
                );

        Priority priority =
                convertPriority(
                        priorityComboBox.getSelectedItem().toString()
                );

        String emergencyId =
                generateEmergencyId();

        String dateTime =
                new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm"
                ).format(new Date());

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

        manager.addEmergency(emergency);

        manager.saveData();

        JOptionPane.showMessageDialog(
                this,
                "Emergency reported successfully.\n\n"
                        + "Emergency ID: " + emergencyId
                        + "\nStatus: " + emergency.getStatus(),
                "Report Successful",
                JOptionPane.INFORMATION_MESSAGE
        );

        dispose();
    }

    // =========================
    // Generate Emergency ID
    // =========================

    private String generateEmergencyId() {

        int nextNumber =
                manager.getEmergencies().size() + 1001;

        return "ER-" + nextNumber;
    }

    // =========================
    // Convert Emergency Type
    // =========================

    private EmergencyType convertEmergencyType(
            String type) {

        if (type.equals("Medical Emergency")) {
            return EmergencyType.MEDICAL;

        } else if (type.equals("Fire Emergency")) {
            return EmergencyType.FIRE;

        } else if (type.equals("Road Accident")) {
            return EmergencyType.ROAD_ACCIDENT;

        } else if (type.equals("Security Emergency")) {
            return EmergencyType.SECURITY;

        } else {
            return EmergencyType.NATURAL_DISASTER;
        }
    }

    // =========================
    // Convert Priority
    // =========================

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

    // =========================
    // Button Styling
    // =========================

    private void stylePrimaryButton(JButton button) {

        button.setBackground(
                Color.decode("#F5CB5C")
        );

        button.setForeground(
                Color.decode("#242423")
        );

        button.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        button.setFocusPainted(false);
    }

    private void styleSecondaryButton(JButton button) {

        button.setBackground(
                Color.decode("#333533")
        );

        button.setForeground(Color.WHITE);

        button.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        button.setFocusPainted(false);
    }
}