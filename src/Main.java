import gui.LoginFrame;
import manager.EmergencyManager;
import model.Admin;
import model.AmbulanceTeam;
import model.FireTeam;
import model.RescueTeam;
import model.ResponseTeam;
import model.SecurityTeam;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        EmergencyManager manager =
                new EmergencyManager();

        // Load previously saved data
        manager.loadData();

        // Create default admin if no admin exists
        createDefaultAdmin(manager);

        // Create initial response teams if they do not exist
        createDefaultTeams(manager);

        // Save initialized data
        manager.saveData();

        // Start the application
        SwingUtilities.invokeLater(() -> {

            LoginFrame loginFrame =
                    new LoginFrame(manager);

            loginFrame.setVisible(true);
        });
    }

    // =========================
    // Default Admin
    // =========================

    private static void createDefaultAdmin(
            EmergencyManager manager) {

        if (!manager.getAdmins().isEmpty()) {
            return;
        }

        Admin admin = new Admin(
                "A001",
                "System Administrator",
                "01900000000",
                "admin@aegis.com",
                "admin",
                "admin123"
        );

        manager.addAdmin(admin);
    }

    // =========================
    // Default Response Teams
    // =========================

    private static void createDefaultTeams(
            EmergencyManager manager) {

        if (manager.findTeamById("AT-001") == null) {

            ResponseTeam ambulanceTeam =
                    new AmbulanceTeam(
                            "AT-001",
                            "Central Ambulance Team",
                            "01711111111",
                            4
                    );

            manager.addTeam(ambulanceTeam);
        }

        if (manager.findTeamById("FT-001") == null) {

            ResponseTeam fireTeam =
                    new FireTeam(
                            "FT-001",
                            "Central Fire Team",
                            "01722222222",
                            6
                    );

            manager.addTeam(fireTeam);
        }

        if (manager.findTeamById("RT-001") == null) {

            ResponseTeam rescueTeam =
                    new RescueTeam(
                            "RT-001",
                            "Central Rescue Team",
                            "01733333333",
                            5
                    );

            manager.addTeam(rescueTeam);
        }

        if (manager.findTeamById("ST-001") == null) {

            ResponseTeam securityTeam =
                    new SecurityTeam(
                            "ST-001",
                            "Campus Security Team",
                            "01744444444",
                            8
                    );

            manager.addTeam(securityTeam);
        }
    }
}