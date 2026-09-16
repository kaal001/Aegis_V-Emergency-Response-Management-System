package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Stack;

public class MainFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel contentPanel;

    private HashMap<String, JPanel> screens;

    private Stack<String> navigationHistory;

    private String currentScreen;

    // =========================
    // APPLICATION CONSTANTS
    // =========================

    private static final String APP_TITLE =
            "Emergency Response Management System";

    public MainFrame() {

        setTitle(
                APP_TITLE
        );

        setSize(
                1100,
                720
        );

        setMinimumSize(
                new Dimension(
                        900,
                        600
                )
        );

        setDefaultCloseOperation(
                JFrame.DO_NOTHING_ON_CLOSE
        );

        setLocationRelativeTo(
                null
        );

        // =========================
        // RESIZABLE WINDOW
        // =========================

        setResizable(
                true
        );

        // =========================
        // CARD LAYOUT
        // =========================

        cardLayout =
                new CardLayout();

        contentPanel =
                new JPanel(
                        cardLayout
                );

        // =========================
        // SCREEN STORAGE
        // =========================

        screens =
                new HashMap<>();

        navigationHistory =
                new Stack<>();

        currentScreen =
                null;

        // =========================
        // MAIN WINDOW
        // =========================

        add(
                contentPanel,
                BorderLayout.CENTER
        );

        // =========================
        // MENU BAR
        // =========================

        setJMenuBar(
                buildMenuBar()
        );

        // =========================
        // EXIT CONFIRMATION
        // =========================

        addWindowListener(
                new WindowAdapter() {

                    @Override
                    public void windowClosing(
                            WindowEvent e
                    ) {

                        int choice =
                                JOptionPane.showConfirmDialog(
                                        MainFrame.this,
                                        "Are you sure you want to exit?",
                                        "Confirm Exit",
                                        JOptionPane.YES_NO_OPTION
                                );

                        if (choice ==
                                JOptionPane.YES_OPTION) {

                            System.exit(0);
                        }
                    }
                }
        );
    }

    // =========================
    // BUILD MENU BAR
    // =========================

    private JMenuBar buildMenuBar() {

        JMenuBar menuBar =
                new JMenuBar();

        // =========================
        // FILE MENU
        // =========================

        JMenu fileMenu =
                new JMenu(
                        "File"
                );

        JMenuItem exitItem =
                new JMenuItem(
                        "Exit"
                );

        exitItem.addActionListener(
                e -> {

                    int choice =
                            JOptionPane.showConfirmDialog(
                                    MainFrame.this,
                                    "Are you sure you want to exit?",
                                    "Confirm Exit",
                                    JOptionPane.YES_NO_OPTION
                            );

                    if (choice ==
                            JOptionPane.YES_OPTION) {

                        System.exit(0);
                    }
                }
        );

        fileMenu.add(
                exitItem
        );

        // =========================
        // VIEW MENU
        // =========================

        JMenu viewMenu =
                new JMenu(
                        "View"
                );

        JMenuItem backItem =
                new JMenuItem(
                        "Go Back"
                );

        backItem.addActionListener(
                e -> goBack()
        );

        viewMenu.add(
                backItem
        );

        // =========================
        // HELP MENU
        // =========================

        JMenu helpMenu =
                new JMenu(
                        "Help"
                );

        JMenuItem aboutItem =
                new JMenuItem(
                        "About"
                );

        aboutItem.addActionListener(
                e -> {

                    JOptionPane.showMessageDialog(
                            MainFrame.this,
                            "Emergency Response Management System\n"
                                    + "Built with Java Swing\n"
                                    + "OOP Lab Project",
                            "About",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
        );

        helpMenu.add(
                aboutItem
        );

        // =========================
        // ADD MENUS
        // =========================

        menuBar.add(
                fileMenu
        );

        menuBar.add(
                viewMenu
        );

        menuBar.add(
                helpMenu
        );

        return menuBar;
    }

    // =========================
    // ADD SCREEN
    // =========================

    public void addScreen(
            String screenName,
            JPanel panel
    ) {

        if (screenName == null ||
                screenName.trim().isEmpty()) {

            return;
        }

        if (panel == null) {

            return;
        }

        screens.put(
                screenName,
                panel
        );

        contentPanel.add(
                panel,
                screenName
        );
    }

    // =========================
    // SHOW SCREEN
    // =========================

    public void showScreen(
            String screenName
    ) {

        if (!screens.containsKey(
                screenName
        )) {

            JOptionPane.showMessageDialog(
                    this,
                    "Screen not found: "
                            + screenName,
                    "Navigation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (currentScreen != null
                &&
                !currentScreen.equals(
                        screenName
                )) {

            navigationHistory.push(
                    currentScreen
            );
        }

        currentScreen =
                screenName;

        cardLayout.show(
                contentPanel,
                screenName
        );
    }

    // =========================
    // GO BACK
    // =========================

    public void goBack() {

        if (navigationHistory.empty()) {

            return;
        }

        String previousScreen =
                navigationHistory.pop();

        currentScreen =
                previousScreen;

        cardLayout.show(
                contentPanel,
                previousScreen
        );
    }

    // =========================
    // CLEAR HISTORY
    // =========================

    public void clearNavigationHistory() {

        navigationHistory.clear();
    }

    // =========================
    // CURRENT SCREEN
    // =========================

    public String getCurrentScreen() {

        return currentScreen;
    }

    // =========================
    // CHECK SCREEN
    // =========================

    public boolean hasScreen(
            String screenName
    ) {

        return screens.containsKey(
                screenName
        );
    }

    // =========================
    // GET SCREEN
    // =========================

    public JPanel getScreen(
            String screenName
    ) {

        return screens.get(
                screenName
        );
    }

    // =========================
    // GET CONTENT PANEL
    // =========================

    public JPanel getContentPanel() {

        return contentPanel;
    }
}