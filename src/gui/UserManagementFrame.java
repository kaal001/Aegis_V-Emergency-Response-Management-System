package gui;

import manager.EmergencyManager;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class UserManagementFrame extends JPanel {

    private MainFrame mainFrame;
    private EmergencyManager manager;

    private JTable userTable;
    private DefaultTableModel tableModel;

    private JTextField searchField;

    private JTextField userIdField;
    private JTextField nameField;
    private JTextField phoneField;
    private JTextField emailField;
    private JTextField usernameField;
    private JPasswordField passwordField;

    private User selectedUser;

    public UserManagementFrame(
            MainFrame mainFrame,
            EmergencyManager manager) {

        this.mainFrame = mainFrame;
        this.manager = manager;

        createUI();
        loadUsers(manager.getUsers());
    }

    // =========================================
    // CREATE UI
    // =========================================

    private void createUI() {

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout(10, 10)
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

        JLabel titleLabel =
                new JLabel(
                        "USER MANAGEMENT",
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

        // =========================================
        // SEARCH
        // =========================================

        JPanel searchPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                10,
                                5
                        )
                );

        searchPanel.setBackground(
                Color.decode("#CFDBD5")
        );

        JLabel searchLabel =
                new JLabel("Search:");

        searchField =
                new JTextField(25);

        JButton searchButton =
                new JButton("SEARCH");

        JButton clearSearchButton =
                new JButton("CLEAR");

        stylePrimaryButton(searchButton);
        styleSecondaryButton(clearSearchButton);

        searchPanel.add(
                searchLabel
        );

        searchPanel.add(
                searchField
        );

        searchPanel.add(
                searchButton
        );

        searchPanel.add(
                clearSearchButton
        );

        // =========================================
        // TABLE
        // =========================================

        String[] columns = {
                "User ID",
                "Name",
                "Phone",
                "Email",
                "Username"
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

        userTable =
                new JTable(
                        tableModel
                );

        userTable.setRowHeight(
                28
        );

        userTable.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        userTable.getTableHeader()
                .setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                12
                        )
                );

        userTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        userTable.setAutoCreateRowSorter(
                true
        );

        JScrollPane tableScrollPane =
                new JScrollPane(
                        userTable
                );

        // =========================================
        // FORM
        // =========================================

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

        userIdField =
                new JTextField();

        nameField =
                new JTextField();

        phoneField =
                new JTextField();

        emailField =
                new JTextField();

        usernameField =
                new JTextField();

        passwordField =
                new JPasswordField();

        formPanel.add(
                new JLabel("User ID:")
        );

        formPanel.add(
                userIdField
        );

        formPanel.add(
                new JLabel("Name:")
        );

        formPanel.add(
                nameField
        );

        formPanel.add(
                new JLabel("Phone:")
        );

        formPanel.add(
                phoneField
        );

        formPanel.add(
                new JLabel("Email:")
        );

        formPanel.add(
                emailField
        );

        formPanel.add(
                new JLabel("Username:")
        );

        formPanel.add(
                usernameField
        );

        formPanel.add(
                new JLabel("Password:")
        );

        formPanel.add(
                passwordField
        );

        // =========================================
        // BUTTONS
        // =========================================

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
                        "ADD USER"
                );

        JButton updateButton =
                new JButton(
                        "UPDATE USER"
                );

        JButton deleteButton =
                new JButton(
                        "DELETE USER"
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

        styleDeleteButton(
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

        // =========================================
        // BOTTOM PANEL
        // =========================================

        JPanel bottomPanel =
                new JPanel(
                        new BorderLayout(10, 10)
                );

        bottomPanel.setBackground(
                Color.decode("#E8EDDF")
        );

        bottomPanel.add(
                formPanel,
                BorderLayout.CENTER
        );

        bottomPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        // =========================================
        // CENTER
        // =========================================

        JPanel centerPanel =
                new JPanel(
                        new BorderLayout(10, 10)
                );

        centerPanel.setBackground(
                Color.decode("#E8EDDF")
        );

        centerPanel.add(
                searchPanel,
                BorderLayout.NORTH
        );

        centerPanel.add(
                tableScrollPane,
                BorderLayout.CENTER
        );

        centerPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        // =========================================
        // TABLE SELECTION
        // =========================================

        userTable
                .getSelectionModel()
                .addListSelectionListener(
                        e -> {

                            if (!e.getValueIsAdjusting()) {

                                selectUser();
                            }
                        }
                );

        // =========================================
        // ACTIONS
        // =========================================

        searchButton.addActionListener(
                e -> searchUsers()
        );

        clearSearchButton.addActionListener(
                e -> clearSearch()
        );

        addButton.addActionListener(
                e -> addUser()
        );

        updateButton.addActionListener(
                e -> updateUser()
        );

        deleteButton.addActionListener(
                e -> deleteUser()
        );

        clearFormButton.addActionListener(
                e -> clearForm()
        );

        backButton.addActionListener(
                e -> mainFrame.goBack()
        );

        // =========================================
        // ADD TO PANEL
        // =========================================

        setLayout(
                new BorderLayout()
        );

        add(
                mainPanel,
                BorderLayout.CENTER
        );
    }

    // =========================================
    // LOAD USERS
    // =========================================

    private void loadUsers(
            ArrayList<User> users) {

        tableModel.setRowCount(
                0
        );

        for (User user :
                users) {

            tableModel.addRow(
                    new Object[]{
                            user.getId(),
                            user.getName(),
                            user.getPhone(),
                            user.getEmail(),
                            user.getUsername()
                    }
            );
        }

        selectedUser =
                null;
    }

    // =========================================
    // SELECT USER
    // =========================================

    private void selectUser() {

        int selectedRow =
                userTable.getSelectedRow();

        if (selectedRow == -1) {

            selectedUser =
                    null;

            return;
        }

        int modelRow =
                userTable.convertRowIndexToModel(
                        selectedRow
                );

        String userId =
                tableModel
                        .getValueAt(
                                modelRow,
                                0
                        )
                        .toString();

        selectedUser =
                manager.findUserById(
                        userId
                );

        if (selectedUser != null) {

            userIdField.setText(
                    selectedUser.getId()
            );

            nameField.setText(
                    selectedUser.getName()
            );

            phoneField.setText(
                    selectedUser.getPhone()
            );

            emailField.setText(
                    selectedUser.getEmail()
            );

            usernameField.setText(
                    selectedUser.getUsername()
            );

            // Password remains blank while editing.
            // Blank means existing password stays unchanged.
            passwordField.setText(
                    ""
            );

            // User ID is not changed during update.
            userIdField.setEnabled(
                    false
            );
        }
    }

    // =========================================
    // SEARCH USERS
    // =========================================

    private void searchUsers() {

        String keyword =
                searchField
                        .getText()
                        .trim()
                        .toLowerCase();

        ArrayList<User> result =
                new ArrayList<>();

        for (User user :
                manager.getUsers()) {

            boolean match =
                    keyword.isEmpty()
                            || user.getId()
                            .toLowerCase()
                            .contains(keyword)
                            || user.getName()
                            .toLowerCase()
                            .contains(keyword)
                            || user.getPhone()
                            .toLowerCase()
                            .contains(keyword)
                            || user.getEmail()
                            .toLowerCase()
                            .contains(keyword)
                            || user.getUsername()
                            .toLowerCase()
                            .contains(keyword);

            if (match) {

                result.add(
                        user
                );
            }
        }

        loadUsers(
                result
        );
    }

    // =========================================
    // CLEAR SEARCH
    // =========================================

    private void clearSearch() {

        searchField.setText(
                ""
        );

        loadUsers(
                manager.getUsers()
        );
    }

    // =========================================
    // ADD USER
    // =========================================

    private void addUser() {

        String id =
                userIdField
                        .getText()
                        .trim();

        String name =
                nameField
                        .getText()
                        .trim();

        String phone =
                phoneField
                        .getText()
                        .trim();

        String email =
                emailField
                        .getText()
                        .trim();

        String username =
                usernameField
                        .getText()
                        .trim();

        String password =
                new String(
                        passwordField
                                .getPassword()
                );

        if (!validateUserInput(
                id,
                name,
                phone,
                email,
                username,
                password,
                true
        )) {

            return;
        }

        if (manager.findUserById(id) != null) {

            JOptionPane.showMessageDialog(
                    mainFrame,
                    "User ID already exists.",
                    "Duplicate User ID",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (usernameExists(
                username,
                null
        )) {

            JOptionPane.showMessageDialog(
                    mainFrame,
                    "Username already exists.",
                    "Duplicate Username",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        User user =
                new User(
                        id,
                        name,
                        phone,
                        email,
                        username,
                        password
                );

        manager.addUser(
                user
        );

        manager.saveData();

        JOptionPane.showMessageDialog(
                mainFrame,
                "User added successfully.",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
        );

        clearForm();

        loadUsers(
                manager.getUsers()
        );
    }

    // =========================================
    // UPDATE USER
    // =========================================

    private void updateUser() {

        if (selectedUser == null) {

            JOptionPane.showMessageDialog(
                    mainFrame,
                    "Please select a user first.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String name =
                nameField
                        .getText()
                        .trim();

        String phone =
                phoneField
                        .getText()
                        .trim();

        String email =
                emailField
                        .getText()
                        .trim();

        String username =
                usernameField
                        .getText()
                        .trim();

        String password =
                new String(
                        passwordField
                                .getPassword()
                );

        if (name.isEmpty()) {

            JOptionPane.showMessageDialog(
                    mainFrame,
                    "Please enter the user's name.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (!phone.matches(
                "\\d{7,15}"
        )) {

            JOptionPane.showMessageDialog(
                    mainFrame,
                    "Phone number must contain 7 to 15 digits.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (!email.contains("@")
                || !email.contains(".")) {

            JOptionPane.showMessageDialog(
                    mainFrame,
                    "Please enter a valid email address.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (username.isEmpty()) {

            JOptionPane.showMessageDialog(
                    mainFrame,
                    "Please enter a username.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (usernameExists(
                username,
                selectedUser
        )) {

            JOptionPane.showMessageDialog(
                    mainFrame,
                    "Username already exists.",
                    "Duplicate Username",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (!password.isEmpty()
                && password.length() < 6) {

            JOptionPane.showMessageDialog(
                    mainFrame,
                    "Password must be at least 6 characters.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        selectedUser.setName(
                name
        );

        selectedUser.setPhone(
                phone
        );

        selectedUser.setEmail(
                email
        );

        selectedUser.setUsername(
                username
        );

        if (!password.isEmpty()) {

            selectedUser.setPassword(
                    password
            );
        }

        manager.saveData();

        JOptionPane.showMessageDialog(
                mainFrame,
                "User updated successfully.",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
        );

        clearForm();

        loadUsers(
                manager.getUsers()
        );
    }

    // =========================================
    // DELETE USER
    // =========================================

    private void deleteUser() {

        if (selectedUser == null) {

            JOptionPane.showMessageDialog(
                    mainFrame,
                    "Please select a user first.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int choice =
                JOptionPane.showConfirmDialog(
                        mainFrame,
                        "Are you sure you want to delete user "
                                + selectedUser.getUsername()
                                + "?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

        if (choice != JOptionPane.YES_OPTION) {

            return;
        }

        manager.getUsers().remove(
                selectedUser
        );

        manager.saveData();

        JOptionPane.showMessageDialog(
                mainFrame,
                "User deleted successfully.",
                "Delete Successful",
                JOptionPane.INFORMATION_MESSAGE
        );

        clearForm();

        loadUsers(
                manager.getUsers()
        );
    }

    // =========================================
    // VALIDATION
    // =========================================

    private boolean validateUserInput(
            String id,
            String name,
            String phone,
            String email,
            String username,
            String password,
            boolean passwordRequired) {

        if (id.isEmpty()) {

            showValidation(
                    "Please enter a user ID."
            );

            return false;
        }

        if (name.isEmpty()) {

            showValidation(
                    "Please enter the user's name."
            );

            return false;
        }

        if (!phone.matches(
                "\\d{7,15}"
        )) {

            showValidation(
                    "Phone number must contain 7 to 15 digits."
            );

            return false;
        }

        if (!email.contains("@")
                || !email.contains(".")) {

            showValidation(
                    "Please enter a valid email address."
            );

            return false;
        }

        if (username.isEmpty()) {

            showValidation(
                    "Please enter a username."
            );

            return false;
        }

        if (passwordRequired
                && password.isEmpty()) {

            showValidation(
                    "Please enter a password."
            );

            return false;
        }

        if (!password.isEmpty()
                && password.length() < 6) {

            showValidation(
                    "Password must be at least 6 characters."
            );

            return false;
        }

        return true;
    }

    private void showValidation(
            String message) {

        JOptionPane.showMessageDialog(
                mainFrame,
                message,
                "Validation Error",
                JOptionPane.WARNING_MESSAGE
        );
    }

    // =========================================
    // USERNAME CHECK
    // =========================================

    private boolean usernameExists(
            String username,
            User ignoredUser) {

        for (User user :
                manager.getUsers()) {

            if (user == ignoredUser) {

                continue;
            }

            if (user.getUsername()
                    .equalsIgnoreCase(
                            username
                    )) {

                return true;
            }
        }

        return false;
    }

    // =========================================
    // CLEAR FORM
    // =========================================

    private void clearForm() {

        userIdField.setText("");
        nameField.setText("");
        phoneField.setText("");
        emailField.setText("");
        usernameField.setText("");
        passwordField.setText("");

        userIdField.setEnabled(
                true
        );

        userTable.clearSelection();

        selectedUser =
                null;
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
                        11
                )
        );

        button.setFocusPainted(
                false
        );
    }

    // =========================================
    // DELETE BUTTON
    // =========================================

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
                        11
                )
        );

        button.setFocusPainted(
                false
        );
    }
}