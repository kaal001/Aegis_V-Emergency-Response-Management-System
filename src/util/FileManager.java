package util;

import model.Assignment;
import model.Emergency;
import model.ResponseTeam;
import model.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileManager {

    private static final String DATA_FOLDER = "data";

    private static final String USERS_FILE =
            DATA_FOLDER + "/users.dat";

    private static final String EMERGENCIES_FILE =
            DATA_FOLDER + "/emergencies.dat";

    private static final String TEAMS_FILE =
            DATA_FOLDER + "/teams.dat";

    private static final String ASSIGNMENTS_FILE =
            DATA_FOLDER + "/assignments.dat";


    // =========================
    // Create Data Folder
    // =========================

    private static void createDataFolder() {

        File folder = new File(DATA_FOLDER);

        if (!folder.exists()) {
            folder.mkdirs();
        }
    }


    // =========================
    // Save Users
    // =========================

    public static void saveUsers(ArrayList<User> users) {

        createDataFolder();

        try {
            ObjectOutputStream output =
                    new ObjectOutputStream(
                            new FileOutputStream(USERS_FILE));

            output.writeObject(users);
            output.close();

            System.out.println("Users saved successfully.");

        } catch (Exception e) {
            System.out.println("Error saving users.");
        }
    }


    // =========================
    // Load Users
    // =========================

    public static ArrayList<User> loadUsers() {

        createDataFolder();

        File file = new File(USERS_FILE);

        if (!file.exists()) {
            return new ArrayList<>();
        }

        try {
            ObjectInputStream input =
                    new ObjectInputStream(
                            new FileInputStream(USERS_FILE));

            ArrayList<User> users =
                    (ArrayList<User>) input.readObject();

            input.close();

            return users;

        } catch (Exception e) {
            System.out.println("Error loading users.");
            return new ArrayList<>();
        }
    }


    // =========================
    // Save Emergencies
    // =========================

    public static void saveEmergencies(ArrayList<Emergency> emergencies) {

        createDataFolder();

        try {
            ObjectOutputStream output =
                    new ObjectOutputStream(
                            new FileOutputStream(EMERGENCIES_FILE));

            output.writeObject(emergencies);
            output.close();

            System.out.println("Emergencies saved successfully.");

        } catch (Exception e) {
            System.out.println("Error saving emergencies.");
        }
    }


    // =========================
    // Load Emergencies
    // =========================

    public static ArrayList<Emergency> loadEmergencies() {

        createDataFolder();

        File file = new File(EMERGENCIES_FILE);

        if (!file.exists()) {
            return new ArrayList<>();
        }

        try {
            ObjectInputStream input =
                    new ObjectInputStream(
                            new FileInputStream(EMERGENCIES_FILE));

            ArrayList<Emergency> emergencies =
                    (ArrayList<Emergency>) input.readObject();

            input.close();

            return emergencies;

        } catch (Exception e) {
            System.out.println("Error loading emergencies.");
            return new ArrayList<>();
        }
    }


    // =========================
    // Save Teams
    // =========================

    public static void saveTeams(ArrayList<ResponseTeam> teams) {

        createDataFolder();

        try {
            ObjectOutputStream output =
                    new ObjectOutputStream(
                            new FileOutputStream(TEAMS_FILE));

            output.writeObject(teams);
            output.close();

            System.out.println("Teams saved successfully.");

        } catch (Exception e) {
            System.out.println("Error saving teams.");
        }
    }


    // =========================
    // Load Teams
    // =========================

    public static ArrayList<ResponseTeam> loadTeams() {

        createDataFolder();

        File file = new File(TEAMS_FILE);

        if (!file.exists()) {
            return new ArrayList<>();
        }

        try {
            ObjectInputStream input =
                    new ObjectInputStream(
                            new FileInputStream(TEAMS_FILE));

            ArrayList<ResponseTeam> teams =
                    (ArrayList<ResponseTeam>) input.readObject();

            input.close();

            return teams;

        } catch (Exception e) {
            System.out.println("Error loading teams.");
            return new ArrayList<>();
        }
    }


    // =========================
    // Save Assignments
    // =========================

    public static void saveAssignments(
            ArrayList<Assignment> assignments) {

        createDataFolder();

        try {
            ObjectOutputStream output =
                    new ObjectOutputStream(
                            new FileOutputStream(ASSIGNMENTS_FILE));

            output.writeObject(assignments);
            output.close();

            System.out.println("Assignments saved successfully.");

        } catch (Exception e) {
            System.out.println("Error saving assignments.");
        }
    }


    // =========================
    // Load Assignments
    // =========================

    public static ArrayList<Assignment> loadAssignments() {

        createDataFolder();

        File file = new File(ASSIGNMENTS_FILE);

        if (!file.exists()) {
            return new ArrayList<>();
        }

        try {
            ObjectInputStream input =
                    new ObjectInputStream(
                            new FileInputStream(ASSIGNMENTS_FILE));

            ArrayList<Assignment> assignments =
                    (ArrayList<Assignment>) input.readObject();

            input.close();

            return assignments;

        } catch (Exception e) {
            System.out.println("Error loading assignments.");
            return new ArrayList<>();
        }
    }
}