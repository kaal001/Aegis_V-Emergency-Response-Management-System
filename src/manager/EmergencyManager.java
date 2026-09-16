package manager;

import enums.EmergencyStatus;
import enums.EmergencyType;
import enums.Priority;
import enums.TeamType;
import model.Admin;
import model.Assignment;
import model.Emergency;
import model.ResponseTeam;
import model.User;

import java.io.Serializable;
import java.util.ArrayList;

public class EmergencyManager implements Serializable {

    private static final long serialVersionUID = 1L;

    private ArrayList<User> users;
    private ArrayList<Admin> admins;
    private ArrayList<Emergency> emergencies;
    private ArrayList<ResponseTeam> teams;
    private ArrayList<Assignment> assignments;

    public EmergencyManager() {

        users = new ArrayList<>();
        admins = new ArrayList<>();
        emergencies = new ArrayList<>();
        teams = new ArrayList<>();
        assignments = new ArrayList<>();
    }

    // =========================
    // User Management
    // =========================

    public void addUser(User user) {
        users.add(user);
    }

    public void addAdmin(Admin admin) {
        admins.add(admin);
    }

    public User findUserById(String userId) {

        for (User user : users) {

            if (user.getId()
                    .equalsIgnoreCase(userId)) {

                return user;
            }
        }

        return null;
    }

    // =========================
    // Emergency Management
    // =========================

    public boolean addEmergency(Emergency emergency) {

        if (emergency == null) {
            return false;
        }

        if (emergency.getEmergencyId() == null
                || emergency.getEmergencyId()
                .trim()
                .isEmpty()) {

            return false;
        }

        if (findEmergencyById(
                emergency.getEmergencyId()
        ) != null) {

            return false;
        }

        emergencies.add(emergency);

        return true;
    }

    public void removeEmergency(String emergencyId) {

        for (int i = 0;
             i < emergencies.size();
             i++) {

            if (emergencies
                    .get(i)
                    .getEmergencyId()
                    .equalsIgnoreCase(
                            emergencyId
                    )) {

                emergencies.remove(i);

                return;
            }
        }
    }

    public Emergency findEmergencyById(
            String emergencyId) {

        for (Emergency emergency :
                emergencies) {

            if (emergency
                    .getEmergencyId()
                    .equalsIgnoreCase(
                            emergencyId
                    )) {

                return emergency;
            }
        }

        return null;
    }

    public ArrayList<Emergency> getAllEmergencies() {
        return emergencies;
    }

    public ArrayList<Emergency> getEmergenciesByUser(
            String userId) {

        ArrayList<Emergency> result =
                new ArrayList<>();

        for (Emergency emergency :
                emergencies) {

            if (emergency
                    .getReportedBy()
                    .equalsIgnoreCase(userId)) {

                result.add(emergency);
            }
        }

        return result;
    }

    public ArrayList<Emergency> getEmergenciesByStatus(
            EmergencyStatus status) {

        ArrayList<Emergency> result =
                new ArrayList<>();

        for (Emergency emergency :
                emergencies) {

            if (emergency.getStatus() == status) {

                result.add(emergency);
            }
        }

        return result;
    }

    public ArrayList<Emergency> getEmergenciesByPriority(
            Priority priority) {

        ArrayList<Emergency> result =
                new ArrayList<>();

        for (Emergency emergency :
                emergencies) {

            if (emergency.getPriority() == priority) {

                result.add(emergency);
            }
        }

        return result;
    }

    public ArrayList<Emergency> getEmergenciesByType(
            EmergencyType type) {

        ArrayList<Emergency> result =
                new ArrayList<>();

        for (Emergency emergency :
                emergencies) {

            if (emergency.getType() == type) {

                result.add(emergency);
            }
        }

        return result;
    }

    public boolean updateEmergencyPriority(
            String emergencyId,
            Priority newPriority) {

        Emergency emergency =
                findEmergencyById(emergencyId);

        if (emergency == null
                || newPriority == null) {

            return false;
        }

        emergency.setPriority(newPriority);

        return true;
    }

    // =========================
    // Team Management
    // =========================

    public void addTeam(ResponseTeam team) {
        teams.add(team);
    }

    public void removeTeam(String teamId) {

        for (int i = 0;
             i < teams.size();
             i++) {

            if (teams
                    .get(i)
                    .getTeamId()
                    .equalsIgnoreCase(teamId)) {

                teams.remove(i);

                return;
            }
        }
    }

    public ResponseTeam findTeamById(
            String teamId) {

        for (ResponseTeam team :
                teams) {

            if (team
                    .getTeamId()
                    .equalsIgnoreCase(teamId)) {

                return team;
            }
        }

        return null;
    }

    public ArrayList<ResponseTeam> getAllTeams() {
        return teams;
    }

    public ArrayList<ResponseTeam> getAvailableTeams() {

        ArrayList<ResponseTeam> result =
                new ArrayList<>();

        for (ResponseTeam team :
                teams) {

            if (team.isAvailable()) {

                result.add(team);
            }
        }

        return result;
    }

    // =========================
    // Suitable Team Logic
    // =========================

    public boolean isTeamSuitable(
            Emergency emergency,
            ResponseTeam team) {

        if (emergency == null
                || team == null) {

            return false;
        }

        TeamType requiredType =
                getRequiredTeamType(
                        emergency.getType()
                );

        return team.getTeamType()
                == requiredType;
    }

    public TeamType getRequiredTeamType(
            EmergencyType emergencyType) {

        if (emergencyType ==
                EmergencyType.MEDICAL) {

            return TeamType.AMBULANCE;

        } else if (emergencyType ==
                EmergencyType.FIRE) {

            return TeamType.FIRE;

        } else if (emergencyType ==
                EmergencyType.ROAD_ACCIDENT) {

            return TeamType.RESCUE;

        } else if (emergencyType ==
                EmergencyType.SECURITY) {

            return TeamType.SECURITY;

        } else {

            return TeamType.RESCUE;
        }
    }

    public ArrayList<ResponseTeam>
    findSuitableTeams(
            Emergency emergency) {

        ArrayList<ResponseTeam> result =
                new ArrayList<>();

        if (emergency == null) {
            return result;
        }

        TeamType requiredType =
                getRequiredTeamType(
                        emergency.getType()
                );

        for (ResponseTeam team :
                teams) {

            if (team.isAvailable()
                    && team.getTeamType()
                    == requiredType) {

                result.add(team);
            }
        }

        return result;
    }

    // =========================
    // Assignment
    // =========================

    public boolean assignTeam(
            String emergencyId,
            String teamId,
            String assignedTime) {

        Emergency emergency =
                findEmergencyById(
                        emergencyId
                );

        ResponseTeam team =
                findTeamById(teamId);

        if (emergency == null
                || team == null) {

            return false;
        }

        if (emergency.getStatus()
                != EmergencyStatus.PENDING) {

            return false;
        }

        if (!team.isAvailable()) {

            return false;
        }

        if (!isTeamSuitable(
                emergency,
                team)) {

            return false;
        }

        String assignmentId =
                generateAssignmentId();

        Assignment assignment =
                new Assignment(
                        assignmentId,
                        emergencyId,
                        teamId,
                        assignedTime
                );

        assignments.add(assignment);

        emergency.setAssignedTeamId(
                teamId
        );

        emergency.setStatus(
                EmergencyStatus.ASSIGNED
        );

        team.setAvailable(false);

        return true;
    }

    // =========================
    // Generate Assignment ID
    // =========================

    private String generateAssignmentId() {

        int highestNumber = 1000;

        for (Assignment assignment :
                assignments) {

            String assignmentId =
                    assignment.getAssignmentId();

            if (assignmentId == null) {
                continue;
            }

            if (assignmentId.startsWith("AS-")) {

                try {

                    int number =
                            Integer.parseInt(
                                    assignmentId
                                            .substring(3)
                            );

                    if (number > highestNumber) {
                        highestNumber = number;
                    }

                } catch (NumberFormatException e) {
                    // Ignore invalid old assignment IDs
                }
            }
        }

        return "AS-" + (highestNumber + 1);
    }

    public ArrayList<Assignment>
    getAllAssignments() {

        return assignments;
    }

    // =========================
    // Emergency Status
    // =========================

    public boolean updateEmergencyStatus(
            String emergencyId,
            EmergencyStatus newStatus) {

        Emergency emergency =
                findEmergencyById(
                        emergencyId
                );

        if (emergency == null
                || newStatus == null) {

            return false;
        }

        EmergencyStatus currentStatus =
                emergency.getStatus();

        // ---------------------------------
        // No change
        // ---------------------------------

        if (currentStatus == newStatus) {
            return false;
        }

        // ---------------------------------
        // PENDING
        // ---------------------------------

        if (currentStatus ==
                EmergencyStatus.PENDING) {

            if (newStatus ==
                    EmergencyStatus.ASSIGNED) {

                if (emergency.getAssignedTeamId()
                        == null) {

                    return false;
                }

                emergency.setStatus(
                        EmergencyStatus.ASSIGNED
                );

                return true;
            }

            if (newStatus ==
                    EmergencyStatus.CANCELLED) {

                emergency.setStatus(
                        EmergencyStatus.CANCELLED
                );

                return true;
            }

            return false;
        }

        // ---------------------------------
        // ASSIGNED
        // ---------------------------------

        if (currentStatus ==
                EmergencyStatus.ASSIGNED) {

            if (newStatus ==
                    EmergencyStatus.IN_PROGRESS) {

                emergency.setStatus(
                        EmergencyStatus.IN_PROGRESS
                );

                return true;
            }

            if (newStatus ==
                    EmergencyStatus.CANCELLED) {

                releaseAssignedTeam(
                        emergency
                );

                emergency.setStatus(
                        EmergencyStatus.CANCELLED
                );

                return true;
            }

            return false;
        }

        // ---------------------------------
        // IN PROGRESS
        // ---------------------------------

        if (currentStatus ==
                EmergencyStatus.IN_PROGRESS) {

            if (newStatus ==
                    EmergencyStatus.RESOLVED) {

                releaseAssignedTeam(
                        emergency
                );

                emergency.setStatus(
                        EmergencyStatus.RESOLVED
                );

                return true;
            }

            if (newStatus ==
                    EmergencyStatus.CANCELLED) {

                releaseAssignedTeam(
                        emergency
                );

                emergency.setStatus(
                        EmergencyStatus.CANCELLED
                );

                return true;
            }

            return false;
        }

        // ---------------------------------
        // RESOLVED / CANCELLED
        // ---------------------------------

        return false;
    }

    // =========================
    // Release Assigned Team
    // =========================

    private void releaseAssignedTeam(
            Emergency emergency) {

        String teamId =
                emergency.getAssignedTeamId();

        if (teamId == null) {
            return;
        }

        ResponseTeam team =
                findTeamById(teamId);

        if (team != null) {

            team.setAvailable(true);
        }
    }

    // =========================
    // Statistics
    // =========================

    public int getTotalEmergencyCount() {
        return emergencies.size();
    }

    public int getPendingCount() {

        return getEmergenciesByStatus(
                EmergencyStatus.PENDING
        ).size();
    }

    public int getCriticalCount() {

        return getEmergenciesByPriority(
                Priority.CRITICAL
        ).size();
    }

    public int getInProgressCount() {

        return getEmergenciesByStatus(
                EmergencyStatus.IN_PROGRESS
        ).size();
    }

    public int getResolvedCount() {

        return getEmergenciesByStatus(
                EmergencyStatus.RESOLVED
        ).size();
    }

    public int getAvailableTeamCount() {

        return getAvailableTeams().size();
    }

    public int getBusyTeamCount() {

        return teams.size()
                - getAvailableTeamCount();
    }

    public int getUnassignedEmergencyCount() {

        int count = 0;

        for (Emergency emergency :
                emergencies) {

            if (emergency.getAssignedTeamId()
                    == null) {

                count++;
            }
        }

        return count;
    }

    // =========================
    // Data Access
    // =========================

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Admin> getAdmins() {
        return admins;
    }

    public ArrayList<Emergency> getEmergencies() {
        return emergencies;
    }

    public ArrayList<ResponseTeam> getTeams() {
        return teams;
    }

    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }

    // =========================
    // Save All Data
    // =========================

    public void saveData() {

        util.FileManager.saveUsers(
                users
        );

        util.FileManager.saveEmergencies(
                emergencies
        );

        util.FileManager.saveTeams(
                teams
        );

        util.FileManager.saveAssignments(
                assignments
        );
    }

    // =========================
    // Load All Data
    // =========================

    public void loadData() {

        users =
                util.FileManager.loadUsers();

        emergencies =
                util.FileManager.loadEmergencies();

        teams =
                util.FileManager.loadTeams();

        assignments =
                util.FileManager.loadAssignments();
    }
}