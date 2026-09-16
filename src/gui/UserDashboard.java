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

public class UserDashboard extends JPanel {

    private MainFrame mainFrame;
    private EmergencyManager manager;
    private User user;

    private CardLayout contentLayout;
    private JPanel contentPanel;

    private JButton dashboardButton;
    private JButton reportButton;
    private JButton myEmergenciesButton;
    private JButton profileButton;

    // Dashboard statistics
    private JLabel totalReportsLabel;
    private JLabel activeReportsLabel;
    private JLabel resolvedReportsLabel;
    private JLabel cancelledReportsLabel;

    // Recent emergencies table
    private DefaultTableModel recentTableModel;

    // Profile fields
    private JTextField profileNameField;
    private JTextField profilePhoneField;
    private JTextField profileEmailField;
    private JTextField profileUsernameField;

    private JButton editProfileButton;
    private JButton saveProfileButton;
    private JButton cancelProfileButton;

    private static final String HOME = "HOME";
    private static final String REPORT = "REPORT";
    private static final String MY_EMERGENCIES = "MY_EMERGENCIES";
    private static final String PROFILE = "PROFILE";

    private final Color ALABASTER =
            Color.decode("#CFDBD5");

    private final Color LINEN =
            Color.decode("#E8EDDF");

    private final Color SUN =
            Color.decode("#F5CB5C");

    private final Color BLACK =
            Color.decode("#242423");

    private final Color GRAPHITE =
            Color.decode("#333533");

    public UserDashboard(
            MainFrame mainFrame,
            EmergencyManager manager,
            User user) {

        this.mainFrame = mainFrame;
        this.manager = manager;
        this.user = user;

        setLayout(
                new BorderLayout()
        );

        setBackground(
                LINEN
        );

        createUI();
    }

    // =========================================================
    // MAIN UI
    // =========================================================

    private void createUI() {

        // =====================================================
        // HEADER
        // =====================================================

        JPanel headerPanel =
                new JPanel(
                        new BorderLayout()
                );

        headerPanel.setBackground(
                BLACK
        );

        JLabel titleLabel =
                new JLabel(
                        "EMERGENCY RESPONSE SYSTEM"
                );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        22
                )
        );

        titleLabel.setForeground(
                LINEN
        );

        titleLabel.setBorder(
                BorderFactory.createEmptyBorder(
                        16,
                        25,
                        16,
                        10
                )
        );

        JLabel welcomeLabel =
                new JLabel(
                        "Welcome, "
                                + user.getUsername(),
                        SwingConstants.RIGHT
                );

        welcomeLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        welcomeLabel.setForeground(
                SUN
        );

        welcomeLabel.setBorder(
                BorderFactory.createEmptyBorder(
                        16,
                        10,
                        16,
                        25
                )
        );

        headerPanel.add(
                titleLabel,
                BorderLayout.WEST
        );

        headerPanel.add(
                welcomeLabel,
                BorderLayout.EAST
        );

        add(
                headerPanel,
                BorderLayout.NORTH
        );

        // =====================================================
        // MAIN AREA
        // =====================================================

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout()
                );

        mainPanel.setBackground(
                LINEN
        );

        // =====================================================
        // SIDEBAR
        // =====================================================

        JPanel sidebar =
                new JPanel(
                        new BorderLayout()
                );

        sidebar.setPreferredSize(
                new Dimension(
                        200,
                        0
                )
        );

        sidebar.setBackground(
                GRAPHITE
        );

        JLabel menuTitle =
                new JLabel(
                        "USER MENU",
                        SwingConstants.CENTER
                );

        menuTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        menuTitle.setForeground(
                LINEN
        );

        menuTitle.setBorder(
                BorderFactory.createEmptyBorder(
                        25,
                        10,
                        20,
                        10
                )
        );

        sidebar.add(
                menuTitle,
                BorderLayout.NORTH
        );

        JPanel menuPanel =
                new JPanel();

        menuPanel.setLayout(
                new BoxLayout(
                        menuPanel,
                        BoxLayout.Y_AXIS
                )
        );

        menuPanel.setBackground(
                GRAPHITE
        );

        dashboardButton =
                createMenuButton(
                        "Dashboard"
                );

        reportButton =
                createMenuButton(
                        "Report Emergency"
                );

        myEmergenciesButton =
                createMenuButton(
                        "My Emergencies"
                );

        profileButton =
                createMenuButton(
                        "My Profile"
                );

        menuPanel.add(
                dashboardButton
        );

        menuPanel.add(
                Box.createVerticalStrut(8)
        );

        menuPanel.add(
                reportButton
        );

        menuPanel.add(
                Box.createVerticalStrut(8)
        );

        menuPanel.add(
                myEmergenciesButton
        );

        menuPanel.add(
                Box.createVerticalStrut(8)
        );

        menuPanel.add(
                profileButton
        );

        sidebar.add(
                menuPanel,
                BorderLayout.CENTER
        );

        // =====================================================
        // LOGOUT
        // =====================================================

        JButton logoutButton =
                new JButton(
                        "LOGOUT"
                );

        styleLogoutButton(
                logoutButton
        );

        JPanel logoutPanel =
                new JPanel(
                        new BorderLayout()
                );

        logoutPanel.setBackground(
                GRAPHITE
        );

        logoutPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        12,
                        12,
                        18,
                        12
                )
        );

        logoutPanel.add(
                logoutButton,
                BorderLayout.CENTER
        );

        sidebar.add(
                logoutPanel,
                BorderLayout.SOUTH
        );

        mainPanel.add(
                sidebar,
                BorderLayout.WEST
        );

        // =====================================================
        // RIGHT CONTENT
        // =====================================================

        contentLayout =
                new CardLayout();

        contentPanel =
                new JPanel(
                        contentLayout
                );

        contentPanel.setBackground(
                LINEN
        );

        // =====================================================
        // HOME
        // =====================================================

        JPanel homePanel =
                createHomePanel();

        // =====================================================
        // PROFILE
        // =====================================================

        JPanel profilePanel =
                createProfilePanel();

        // =====================================================
        // REPORT
        // =====================================================

        ReportEmergencyFrame reportPanel =
                new ReportEmergencyFrame(
                        manager,
                        user,
                        () -> showContent(HOME),
                        () -> {
                            refreshDashboard();
                            showContent(HOME);
                        }
                );

        // =====================================================
        // MY EMERGENCIES
        // =====================================================

        MyEmergenciesFrame myEmergenciesPanel =
                new MyEmergenciesFrame(
                        manager,
                        user,
                        () -> showContent(HOME)
                );

        contentPanel.add(
                homePanel,
                HOME
        );

        contentPanel.add(
                reportPanel,
                REPORT
        );

        contentPanel.add(
                myEmergenciesPanel,
                MY_EMERGENCIES
        );

        contentPanel.add(
                profilePanel,
                PROFILE
        );

        mainPanel.add(
                contentPanel,
                BorderLayout.CENTER
        );

        add(
                mainPanel,
                BorderLayout.CENTER
        );

        // =====================================================
        // MENU ACTIONS
        // =====================================================

        dashboardButton.addActionListener(
                e -> {
                    refreshDashboard();
                    showContent(HOME);
                }
        );

        reportButton.addActionListener(
                e -> showContent(REPORT)
        );

        myEmergenciesButton.addActionListener(
                e -> {
                    refreshMyEmergencies();
                    showContent(MY_EMERGENCIES);
                }
        );

        profileButton.addActionListener(
                e -> {
                    loadProfileData();
                    showContent(PROFILE);
                }
        );

        logoutButton.addActionListener(
                e -> logout()
        );

        // Default screen
        refreshDashboard();
        showContent(HOME);
    }
// =========================================================
// STAT CARD
// =========================================================

    private JPanel createStatCard(
            String title,
            JLabel valueLabel) {

        JPanel card =
                new JPanel(
                        new BorderLayout(
                                5,
                                5
                        )
                );

        card.setBackground(
                ALABASTER
        );

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                Color.decode("#B8C4BE")
                        ),
                        BorderFactory.createEmptyBorder(
                                10,
                                10,
                                10,
                                10
                        )
                )
        );

        JLabel titleLabel =
                new JLabel(
                        title,
                        SwingConstants.CENTER
                );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        12
                )
        );

        titleLabel.setForeground(
                GRAPHITE
        );

        card.add(
                titleLabel,
                BorderLayout.NORTH
        );

        card.add(
                valueLabel,
                BorderLayout.CENTER
        );

        return card;
    }

// =========================================================
// STAT VALUE LABEL
// =========================================================

    private JLabel createStatValueLabel(
            String value) {

        JLabel label =
                new JLabel(
                        value,
                        SwingConstants.CENTER
                );

        label.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        label.setForeground(
                BLACK
        );

        return label;
    }
    // =========================================================
    // HOME / DASHBOARD
    // =========================================================

    private JPanel createHomePanel() {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                15,
                                15
                        )
                );

        panel.setBackground(
                LINEN
        );

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );

        JLabel title =
                new JLabel(
                        "WELCOME TO YOUR DASHBOARD"
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        title.setForeground(
                BLACK
        );

        title.setBorder(
                BorderFactory.createEmptyBorder(
                        0,
                        0,
                        5,
                        0
                )
        );

        panel.add(
                title,
                BorderLayout.NORTH
        );

        JPanel centerPanel =
                new JPanel(
                        new BorderLayout(
                                15,
                                15
                        )
                );

        centerPanel.setBackground(
                LINEN
        );

        // =====================================================
        // STATISTICS
        // =====================================================

        JPanel statisticsPanel =
                new JPanel(
                        new GridLayout(
                                2,
                                2,
                                12,
                                12
                        )
                );

        statisticsPanel.setBackground(
                LINEN
        );

        totalReportsLabel =
                createStatValueLabel(
                        "0"
                );

        activeReportsLabel =
                createStatValueLabel(
                        "0"
                );

        resolvedReportsLabel =
                createStatValueLabel(
                        "0"
                );

        cancelledReportsLabel =
                createStatValueLabel(
                        "0"
                );

        statisticsPanel.add(
                createStatCard(
                        "TOTAL REPORTS",
                        totalReportsLabel
                )
        );

        statisticsPanel.add(
                createStatCard(
                        "ACTIVE",
                        activeReportsLabel
                )
        );

        statisticsPanel.add(
                createStatCard(
                        "RESOLVED",
                        resolvedReportsLabel
                )
        );

        statisticsPanel.add(
                createStatCard(
                        "CANCELLED",
                        cancelledReportsLabel
                )
        );

        centerPanel.add(
                statisticsPanel,
                BorderLayout.NORTH
        );

        // =====================================================
        // RECENT EMERGENCIES
        // =====================================================

        JPanel recentPanel =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        recentPanel.setBackground(
                LINEN
        );

        JLabel recentTitle =
                new JLabel(
                        "RECENT EMERGENCIES"
                );

        recentTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        17
                )
        );

        recentTitle.setForeground(
                BLACK
        );

        recentPanel.add(
                recentTitle,
                BorderLayout.NORTH
        );

        recentTableModel =
                new DefaultTableModel(
                        new String[]{
                                "Emergency ID",
                                "Type",
                                "Priority",
                                "Status",
                                "Location"
                        },
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column) {

                        return false;
                    }
                };

        JTable recentTable =
                new JTable(
                        recentTableModel
                );

        recentTable.setRowHeight(
                27
        );

        recentTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        recentTable.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        recentTable.getTableHeader()
                .setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                12
                        )
                );

        JScrollPane recentScrollPane =
                new JScrollPane(
                        recentTable
                );

        recentPanel.add(
                recentScrollPane,
                BorderLayout.CENTER
        );

        centerPanel.add(
                recentPanel,
                BorderLayout.CENTER
        );

        // =====================================================
        // QUICK ACTIONS
        // =====================================================

        JPanel bottomPanel =
                new JPanel(
                        new BorderLayout(
                                5,
                                5
                        )
                );

        bottomPanel.setBackground(
                LINEN
        );

        JLabel quickActionLabel =
                new JLabel(
                        "QUICK ACTIONS"
                );

        quickActionLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        quickActionLabel.setForeground(
                BLACK
        );

        JPanel quickActionButtons =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                10,
                                5
                        )
                );

        quickActionButtons.setBackground(
                LINEN
        );

        JButton reportNowButton =
                new JButton(
                        "REPORT EMERGENCY"
                );

        JButton refreshButton =
                new JButton(
                        "REFRESH DASHBOARD"
                );

        stylePrimaryButton(
                reportNowButton
        );

        styleSecondaryButton(
                refreshButton
        );

        reportNowButton.addActionListener(
                e -> showContent(REPORT)
        );

        refreshButton.addActionListener(
                e -> refreshDashboard()
        );

        quickActionButtons.add(
                reportNowButton
        );

        quickActionButtons.add(
                refreshButton
        );

        bottomPanel.add(
                quickActionLabel,
                BorderLayout.NORTH
        );

        bottomPanel.add(
                quickActionButtons,
                BorderLayout.CENTER
        );

        centerPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        panel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        return panel;
    }

    // =========================================================
    // REFRESH DASHBOARD
    // =========================================================

    private void refreshDashboard() {

        if (totalReportsLabel == null) {
            return;
        }

        ArrayList<Emergency> emergencies =
                manager.getEmergenciesByUser(
                        user.getId()
                );

        int total = 0;
        int active = 0;
        int resolved = 0;
        int cancelled = 0;

        for (
                int i = 0;
                i < emergencies.size();
                i++
        ) {

            Emergency emergency =
                    emergencies.get(i);

            total++;

            if (
                    emergency.getStatus()
                            == EmergencyStatus.PENDING

                            ||

                            emergency.getStatus()
                                    == EmergencyStatus.ASSIGNED

                            ||

                            emergency.getStatus()
                                    == EmergencyStatus.IN_PROGRESS
            ) {

                active++;

            } else if (
                    emergency.getStatus()
                            == EmergencyStatus.RESOLVED
            ) {

                resolved++;

            } else if (
                    emergency.getStatus()
                            == EmergencyStatus.CANCELLED
            ) {

                cancelled++;
            }
        }

        totalReportsLabel.setText(
                String.valueOf(total)
        );

        activeReportsLabel.setText(
                String.valueOf(active)
        );

        resolvedReportsLabel.setText(
                String.valueOf(resolved)
        );

        cancelledReportsLabel.setText(
                String.valueOf(cancelled)
        );

        refreshRecentEmergencies(
                emergencies
        );
    }

    // =========================================================
    // RECENT EMERGENCIES
    // =========================================================

    private void refreshRecentEmergencies(
            ArrayList<Emergency> emergencies) {

        if (recentTableModel == null) {
            return;
        }

        recentTableModel.setRowCount(
                0
        );

        int startIndex =
                emergencies.size() - 1;

        int count = 0;

        for (
                int i = startIndex;
                i >= 0 && count < 5;
                i--
        ) {

            Emergency emergency =
                    emergencies.get(i);

            recentTableModel.addRow(
                    new Object[]{
                            emergency.getEmergencyId(),
                            formatEmergencyType(
                                    emergency.getType()
                            ),
                            formatPriority(
                                    emergency.getPriority()
                            ),
                            formatStatus(
                                    emergency.getStatus()
                            ),
                            emergency.getLocation()
                    }
            );

            count++;
        }

        if (
                emergencies.isEmpty()
        ) {

            recentTableModel.addRow(
                    new Object[]{
                            "-",
                            "No emergencies",
                            "-",
                            "-",
                            "-"
                    }
            );
        }
    }

    // =========================================================
    // PROFILE
    // =========================================================

    private JPanel createProfilePanel() {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                15,
                                15
                        )
                );

        panel.setBackground(
                LINEN
        );

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        25,
                        25,
                        25,
                        25
                )
        );

        JLabel title =
                new JLabel(
                        "MY PROFILE"
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        25
                )
        );

        title.setForeground(
                BLACK
        );

        panel.add(
                title,
                BorderLayout.NORTH
        );

        // =====================================================
        // PROFILE CARD
        // =====================================================

        JPanel profileCard =
                new JPanel(
                        new GridBagLayout()
                );

        profileCard.setBackground(
                ALABASTER
        );

        profileCard.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                Color.decode("#B8C4BE")
                        ),
                        BorderFactory.createEmptyBorder(
                                25,
                                30,
                                25,
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

        // =====================================================
        // USER ID
        // =====================================================

        JLabel userIdLabel =
                new JLabel(
                        "User ID:"
                );

        JLabel userIdValue =
                new JLabel(
                        user.getId()
                );

        addProfileRow(
                profileCard,
                gbc,
                0,
                userIdLabel,
                userIdValue
        );

        // =====================================================
        // NAME
        // =====================================================

        JLabel nameLabel =
                new JLabel(
                        "Name:"
                );

        profileNameField =
                new JTextField(
                        25
                );

        addProfileRow(
                profileCard,
                gbc,
                1,
                nameLabel,
                profileNameField
        );

        // =====================================================
        // PHONE
        // =====================================================

        JLabel phoneLabel =
                new JLabel(
                        "Phone:"
                );

        profilePhoneField =
                new JTextField(
                        25
                );

        addProfileRow(
                profileCard,
                gbc,
                2,
                phoneLabel,
                profilePhoneField
        );

        // =====================================================
        // EMAIL
        // =====================================================

        JLabel emailLabel =
                new JLabel(
                        "Email:"
                );

        profileEmailField =
                new JTextField(
                        25
                );

        addProfileRow(
                profileCard,
                gbc,
                3,
                emailLabel,
                profileEmailField
        );

        // =====================================================
        // USERNAME
        // =====================================================

        JLabel usernameLabel =
                new JLabel(
                        "Username:"
                );

        profileUsernameField =
                new JTextField(
                        25
                );

        addProfileRow(
                profileCard,
                gbc,
                4,
                usernameLabel,
                profileUsernameField
        );

        // =====================================================
        // BUTTON PANEL
        // =====================================================

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                10,
                                10
                        )
                );

        buttonPanel.setBackground(
                ALABASTER
        );

        editProfileButton =
                new JButton(
                        "EDIT PROFILE"
                );

        saveProfileButton =
                new JButton(
                        "SAVE PROFILE"
                );

        cancelProfileButton =
                new JButton(
                        "CANCEL"
                );

        stylePrimaryButton(
                editProfileButton
        );

        stylePrimaryButton(
                saveProfileButton
        );

        styleSecondaryButton(
                cancelProfileButton
        );

        buttonPanel.add(
                editProfileButton
        );

        buttonPanel.add(
                saveProfileButton
        );

        buttonPanel.add(
                cancelProfileButton
        );

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;

        profileCard.add(
                buttonPanel,
                gbc
        );

        panel.add(
                profileCard,
                BorderLayout.NORTH
        );

        // =====================================================
        // BUTTON ACTIONS
        // =====================================================

        editProfileButton.addActionListener(
                e -> enableProfileEditing()
        );

        saveProfileButton.addActionListener(
                e -> saveProfile()
        );

        cancelProfileButton.addActionListener(
                e -> loadProfileData()
        );

        loadProfileData();

        return panel;
    }

    // =========================================================
    // PROFILE ROW
    // =========================================================

    private void addProfileRow(
            JPanel panel,
            GridBagConstraints gbc,
            int row,
            JLabel label,
            JComponent component) {

        label.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        component.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        panel.add(
                label,
                gbc
        );

        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.weightx = 1.0;

        panel.add(
                component,
                gbc
        );
    }

    // =========================================================
    // LOAD PROFILE DATA
    // =========================================================

    private void loadProfileData() {

        if (profileNameField == null) {
            return;
        }

        profileNameField.setText(
                user.getName()
        );

        profilePhoneField.setText(
                user.getPhone()
        );

        profileEmailField.setText(
                user.getEmail()
        );

        profileUsernameField.setText(
                user.getUsername()
        );

        setProfileEditing(
                false
        );
    }

    // =========================================================
    // ENABLE PROFILE EDITING
    // =========================================================

    private void enableProfileEditing() {

        setProfileEditing(
                true
        );

        profileNameField.requestFocus();
    }

    // =========================================================
    // SET PROFILE EDITING MODE
    // =========================================================

    private void setProfileEditing(
            boolean editing) {

        profileNameField.setEditable(
                editing
        );

        profilePhoneField.setEditable(
                editing
        );

        profileEmailField.setEditable(
                editing
        );

        profileUsernameField.setEditable(
                editing
        );

        saveProfileButton.setEnabled(
                editing
        );

        cancelProfileButton.setEnabled(
                editing
        );

        editProfileButton.setEnabled(
                !editing
        );

        if (editing) {

            profileNameField.setBackground(
                    Color.WHITE
            );

            profilePhoneField.setBackground(
                    Color.WHITE
            );

            profileEmailField.setBackground(
                    Color.WHITE
            );

            profileUsernameField.setBackground(
                    Color.WHITE
            );

        } else {

            profileNameField.setBackground(
                    ALABASTER
            );

            profilePhoneField.setBackground(
                    ALABASTER
            );

            profileEmailField.setBackground(
                    ALABASTER
            );

            profileUsernameField.setBackground(
                    ALABASTER
            );
        }
    }

    // =========================================================
    // SAVE PROFILE
    // =========================================================

    private void saveProfile() {

        String name =
                profileNameField
                        .getText()
                        .trim();

        String phone =
                profilePhoneField
                        .getText()
                        .trim();

        String email =
                profileEmailField
                        .getText()
                        .trim();

        String username =
                profileUsernameField
                        .getText()
                        .trim();

        // =====================================================
        // VALIDATION
        // =====================================================

        if (name.isEmpty()
                || phone.isEmpty()
                || email.isEmpty()
                || username.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "All profile fields are required.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (!phone.matches(
                "\\d{7,15}"
        )) {

            JOptionPane.showMessageDialog(
                    this,
                    "Phone number must contain 7 to 15 digits.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            profilePhoneField.requestFocus();

            return;
        }

        if (!email.matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
        )) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid email address.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            profileEmailField.requestFocus();

            return;
        }

        // =====================================================
        // CHECK DUPLICATE USERNAME
        // =====================================================

        for (User existingUser :
                manager.getUsers()) {

            if (
                    existingUser != user
                            &&
                            existingUser
                                    .getUsername()
                                    .equalsIgnoreCase(
                                            username
                                    )
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "This username is already in use.",
                        "Validation Error",
                        JOptionPane.WARNING_MESSAGE
                );

                profileUsernameField.requestFocus();

                return;
            }
        }

        // =====================================================
        // UPDATE USER
        // =====================================================

        user.setName(
                name
        );

        user.setPhone(
                phone
        );

        user.setEmail(
                email
        );

        user.setUsername(
                username
        );

        // =====================================================
        // SAVE DATA
        // =====================================================

        manager.saveData();

        JOptionPane.showMessageDialog(
                this,
                "Profile updated successfully.",
                "Update Successful",
                JOptionPane.INFORMATION_MESSAGE
        );

        // =====================================================
        // UPDATE HEADER
        // =====================================================

        refreshWelcomeLabel();

        setProfileEditing(
                false
        );
    }

    // =========================================================
    // REFRESH WELCOME LABEL
    // =========================================================

    private void refreshWelcomeLabel() {

        Component[] components =
                getComponents();

        if (components.length == 0) {
            return;
        }

        Component firstComponent =
                components[0];

        if (
                firstComponent
                        instanceof JPanel
        ) {

            JPanel headerPanel =
                    (JPanel) firstComponent;

            for (
                    Component component :
                    headerPanel.getComponents()
            ) {

                if (
                        component instanceof JLabel
                ) {

                    JLabel label =
                            (JLabel) component;

                    if (
                            label.getText()
                                    .startsWith(
                                            "Welcome,"
                                    )
                    ) {

                        label.setText(
                                "Welcome, "
                                        + user.getUsername()
                        );

                        break;
                    }
                }
            }
        }
    }

    // =========================================================
    // MENU NAVIGATION
    // =========================================================

    private void showContent(
            String screen) {

        contentLayout.show(
                contentPanel,
                screen
        );

        setActiveMenu(
                screen
        );

        if (
                screen.equals(HOME)
        ) {

            refreshDashboard();
        }

        if (
                screen.equals(PROFILE)
        ) {

            loadProfileData();
        }
    }

    // =========================================================
    // REFRESH MY EMERGENCIES
    // =========================================================

    private void refreshMyEmergencies() {

        Component[] components =
                contentPanel.getComponents();

        for (
                int i = 0;
                i < components.length;
                i++
        ) {

            if (
                    components[i]
                            instanceof MyEmergenciesFrame
            ) {

                MyEmergenciesFrame panel =
                        (MyEmergenciesFrame)
                                components[i];

                panel.refreshData();

                break;
            }
        }
    }

    // =========================================================
    // ACTIVE MENU
    // =========================================================

    private void setActiveMenu(
            String screen) {

        resetMenuButton(
                dashboardButton
        );

        resetMenuButton(
                reportButton
        );

        resetMenuButton(
                myEmergenciesButton
        );

        resetMenuButton(
                profileButton
        );

        JButton activeButton =
                null;

        if (
                screen.equals(HOME)
        ) {

            activeButton =
                    dashboardButton;

        } else if (
                screen.equals(REPORT)
        ) {

            activeButton =
                    reportButton;

        } else if (
                screen.equals(MY_EMERGENCIES)
        ) {

            activeButton =
                    myEmergenciesButton;

        } else if (
                screen.equals(PROFILE)
        ) {

            activeButton =
                    profileButton;
        }

        if (
                activeButton != null
        ) {

            activeButton.setBackground(
                    SUN
            );

            activeButton.setForeground(
                    BLACK
            );
        }
    }

    // =========================================================
    // MENU BUTTON
    // =========================================================

    private JButton createMenuButton(
            String text) {

        JButton button =
                new JButton(
                        text
                );

        button.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        55
                )
        );

        button.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        button.setForeground(
                LINEN
        );

        button.setBackground(
                GRAPHITE
        );

        button.setBorder(
                BorderFactory.createEmptyBorder(
                        14,
                        18,
                        14,
                        18
                )
        );

        button.setFocusPainted(
                false
        );

        button.setHorizontalAlignment(
                SwingConstants.LEFT
        );

        return button;
    }

    // =========================================================
    // RESET MENU BUTTON
    // =========================================================

    private void resetMenuButton(
            JButton button) {

        button.setBackground(
                GRAPHITE
        );

        button.setForeground(
                LINEN
        );
    }

    // =========================================================
    // PRIMARY BUTTON
    // =========================================================

    private void stylePrimaryButton(
            JButton button) {

        button.setBackground(
                SUN
        );

        button.setForeground(
                BLACK
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

    // =========================================================
    // SECONDARY BUTTON
    // =========================================================

    private void styleSecondaryButton(
            JButton button) {

        button.setBackground(
                GRAPHITE
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

    // =========================================================
    // LOGOUT
    // =========================================================

    private void logout() {

        int choice =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to logout?",
                        "Confirm Logout",
                        JOptionPane.YES_NO_OPTION
                );

        if (
                choice ==
                        JOptionPane.YES_OPTION
        ) {

            mainFrame.clearNavigationHistory();

            mainFrame.showScreen(
                    "LOGIN"
            );
        }
    }

    // =========================================================
    // FORMAT TYPE
    // =========================================================

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
    // =========================================================
    // FORMAT PRIORITY
    // =========================================================

    private String formatPriority(
            Priority priority) {

        if (
                priority == Priority.CRITICAL
        ) {

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

    // =========================================================
    // FORMAT STATUS
    // =========================================================

    private String formatStatus(
            EmergencyStatus status) {

        if (
                status == EmergencyStatus.PENDING
        ) {

            return "Pending";

        } else if (
                status == EmergencyStatus.ASSIGNED
        ) {

            return "Assigned";

        } else if (
                status == EmergencyStatus.IN_PROGRESS
        ) {

            return "In Progress";

        } else if (
                status == EmergencyStatus.RESOLVED
        ) {

            return "Resolved";

        } else {

            return "Cancelled";
        }
    }

    // =========================================================
    // LOGOUT STYLE
    // =========================================================

    private void styleLogoutButton(
            JButton button) {

        button.setBackground(
                GRAPHITE
        );

        button.setForeground(
                SUN
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

        button.setBorder(
                BorderFactory.createLineBorder(
                        SUN
                )
        );
    }
}