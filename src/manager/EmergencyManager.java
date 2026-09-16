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

        if (user == null) {
            return;
        }

        users.add(user);
    }

    public void addAdmin(Admin admin) {

        if (admin == null) {
            return;
        }

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

    // =========================
    // USER EMERGENCY UPDATE
    // =========================

    public boolean updateUserEmergency(
            String emergencyId,
            String userId,
            EmergencyType newType,
            Priority newPriority,
            String newLocation,
            String newDescription) {

        Emergency emergency =
                findEmergencyById(
                        emergencyId
                );

        if (emergency == null
                || userId == null
                || newType == null
                || newPriority == null) {

            return false;
        }

        if (!emergency.getReportedBy()
                .equalsIgnoreCase(userId)) {

            return false;
        }

        if (!canUserModifyEmergency(
                emergency
        )) {

            return false;
        }

        if (newLocation == null
                || newLocation.trim().isEmpty()) {

            return false;
        }

        if (newDescription == null
                || newDescription.trim().isEmpty()) {

            return false;
        }

        emergency.setType(newType);

        emergency.setPriority(newPriority);

        emergency.setLocation(
                newLocation.trim()
        );

        emergency.setDescription(
                newDescription.trim()
        );

        return true;
    }

    // =========================
    // USER EMERGENCY DELETE
    // =========================

    public boolean removeUserEmergency(
            String emergencyId,
            String userId) {

        Emergency emergency =
                findEmergencyById(
                        emergencyId
                );

        if (emergency == null
                || userId == null) {

            return false;
        }

        if (!emergency.getReportedBy()
                .equalsIgnoreCase(userId)) {

            return false;
        }

        if (!canUserModifyEmergency(
                emergency
        )) {

            return false;
        }

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

                return true;
            }
        }

        return false;
    }

    // =========================
    // USER MODIFICATION RULE
    // =========================

    private boolean canUserModifyEmergency(
            Emergency emergency) {

        if (emergency == null) {
            return false;
        }

        if (emergency.getStatus()
                != EmergencyStatus.PENDING) {

            return false;
        }

        if (emergency.getAssignedTeamId()
                != null) {

            return false;
        }

        return true;
    }

    // =========================
    // Emergency Search
    // =========================

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

            if (emergency.getStatus()
                    == status) {

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

            if (emergency.getPriority()
                    == priority) {

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

            if (emergency.getType()
                    == type) {

                result.add(emergency);
            }
        }

        return result;
    }

    public boolean updateEmergencyPriority(
            String emergencyId,
            Priority newPriority) {

        Emergency emergency =
                findEmergencyById(
                        emergencyId
                );

        if (emergency == null
                || newPriority == null) {

            return false;
        }

        emergency.setPriority(
                newPriority
        );

        return true;
    }

    // =========================
    // Team Management
    // =========================

    public void addTeam(ResponseTeam team) {

        if (team == null) {
            return;
        }

        teams.add(team);
    }

    public void removeTeam(String teamId) {

        for (int i = 0;
             i < teams.size();
             i++) {

            if (teams
                    .get(i)
                    .getTeamId()
                    .equalsIgnoreCase(
                            teamId
                    )) {

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
                    .equalsIgnoreCase(
                            teamId
                    )) {

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

        if (requiredType == null) {
            return false;
        }

        return team.getTeamType()
                == requiredType;
    }

    public TeamType getRequiredTeamType(
            EmergencyType emergencyType) {

        if (emergencyType == null) {
            return null;
        }

        switch (emergencyType) {

            case MEDICAL:
                return TeamType.AMBULANCE;

            case FIRE:
            case GAS_LEAK:
                return TeamType.FIRE;

            case ROAD_ACCIDENT:
            case ELECTRICAL_EMERGENCY:
            case BUILDING_COLLAPSE:
            case INDUSTRIAL_ACCIDENT:
            case NATURAL_DISASTER:
            case WATER_FLOOD_EMERGENCY:
                return TeamType.RESCUE;

            case SECURITY:
            case MISSING_PERSON:
                return TeamType.SECURITY;

            case CUSTOM:
                return null;

            default:
                return null;
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

        if (requiredType == null) {
            return result;
        }

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
    // ASSIGNMENT MANAGEMENT
    // =========================

    // ADD ASSIGNMENT
    public boolean addAssignment(
            String emergencyId,
            String teamId,
            String assignedTime,
            String notes) {

        Emergency emergency =
                findEmergencyById(
                        emergencyId
                );

        ResponseTeam team =
                findTeamById(
                        teamId
                );

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
                team
        )) {

            return false;
        }

        if (assignedTime == null
                || assignedTime.trim().isEmpty()) {

            return false;
        }

        String assignmentId =
                generateAssignmentId();

        Assignment assignment =
                new Assignment(
                        assignmentId,
                        emergencyId,
                        teamId,
                        assignedTime.trim()
                );

        if (notes != null) {
            assignment.setNotes(
                    notes.trim()
            );
        }

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

    // Existing assignment workflow
    // remains supported.
    public boolean assignTeam(
            String emergencyId,
            String teamId,
            String assignedTime) {

        return addAssignment(
                emergencyId,
                teamId,
                assignedTime,
                ""
        );
    }

    // FIND ASSIGNMENT BY ID
    public Assignment findAssignmentById(
            String assignmentId) {

        for (Assignment assignment :
                assignments) {

            if (assignment
                    .getAssignmentId()
                    .equalsIgnoreCase(
                            assignmentId
                    )) {

                return assignment;
            }
        }

        return null;
    }

    // SEARCH ASSIGNMENTS
    public ArrayList<Assignment>
    searchAssignments(
            String keyword) {

        ArrayList<Assignment> result =
                new ArrayList<>();

        if (keyword == null) {
            keyword = "";
        }

        keyword =
                keyword.trim().toLowerCase();

        for (Assignment assignment :
                assignments) {

            boolean matches =
                    keyword.isEmpty()
                            || assignment
                            .getAssignmentId()
                            .toLowerCase()
                            .contains(keyword)
                            || assignment
                            .getEmergencyId()
                            .toLowerCase()
                            .contains(keyword)
                            || assignment
                            .getTeamId()
                            .toLowerCase()
                            .contains(keyword)
                            || assignment
                            .getAssignedTime()
                            .toLowerCase()
                            .contains(keyword)
                            || assignment
                            .getNotes()
                            .toLowerCase()
                            .contains(keyword);

            if (matches) {
                result.add(assignment);
            }
        }

        return result;
    }

    // UPDATE ASSIGNMENT
    public boolean updateAssignment(
            String assignmentId,
            String newEmergencyId,
            String newTeamId,
            String newAssignedTime,
            String newNotes) {

        Assignment assignment =
                findAssignmentById(
                        assignmentId
                );

        if (assignment == null) {
            return false;
        }

        if (newAssignedTime == null
                || newAssignedTime.trim().isEmpty()) {

            return false;
        }

        Emergency oldEmergency =
                findEmergencyById(
                        assignment.getEmergencyId()
                );

        ResponseTeam oldTeam =
                findTeamById(
                        assignment.getTeamId()
                );

        Emergency newEmergency =
                findEmergencyById(
                        newEmergencyId
                );

        ResponseTeam newTeam =
                findTeamById(
                        newTeamId
                );

        if (oldEmergency == null
                || oldTeam == null
                || newEmergency == null
                || newTeam == null) {

            return false;
        }

        // The current assignment must still
        // belong to an active ASSIGNED emergency.
        if (oldEmergency.getStatus()
                != EmergencyStatus.ASSIGNED) {

            return false;
        }

        // New emergency must be pending unless
        // it is the same emergency.
        if (!assignment.getEmergencyId()
                .equalsIgnoreCase(newEmergencyId)
                && newEmergency.getStatus()
                != EmergencyStatus.PENDING) {

            return false;
        }

        // New team must be available unless
        // it is the same team.
        if (!assignment.getTeamId()
                .equalsIgnoreCase(newTeamId)
                && !newTeam.isAvailable()) {

            return false;
        }

        // New team must be suitable.
        if (!isTeamSuitable(
                newEmergency,
                newTeam
        )) {

            return false;
        }

        // ---------------------------------
        // Release old team
        // ---------------------------------

        if (!oldTeam.getTeamId()
                .equalsIgnoreCase(newTeamId)) {

            oldTeam.setAvailable(true);
        }

        // ---------------------------------
        // Clear old emergency
        // ---------------------------------

        if (!oldEmergency.getEmergencyId()
                .equalsIgnoreCase(newEmergencyId)) {

            oldEmergency.setAssignedTeamId(
                    null
            );

            oldEmergency.setStatus(
                    EmergencyStatus.PENDING
            );
        }

        // ---------------------------------
        // Update assignment
        // ---------------------------------

        assignment.setEmergencyId(
                newEmergencyId
        );

        assignment.setTeamId(
                newTeamId
        );

        assignment.setAssignedTime(
                newAssignedTime.trim()
        );

        if (newNotes == null) {
            assignment.setNotes("");
        } else {
            assignment.setNotes(
                    newNotes.trim()
            );
        }

        // ---------------------------------
        // Assign new emergency/team
        // ---------------------------------

        newEmergency.setAssignedTeamId(
                newTeamId
        );

        newEmergency.setStatus(
                EmergencyStatus.ASSIGNED
        );

        newTeam.setAvailable(false);

        return true;
    }

    // DELETE ASSIGNMENT
    public boolean removeAssignment(
            String assignmentId) {

        Assignment assignment =
                findAssignmentById(
                        assignmentId
                );

        if (assignment == null) {
            return false;
        }

        Emergency emergency =
                findEmergencyById(
                        assignment.getEmergencyId()
                );

        ResponseTeam team =
                findTeamById(
                        assignment.getTeamId()
                );

        if (emergency == null
                || team == null) {

            return false;
        }

        // Only active assignments can be
        // manually deleted.
        if (emergency.getStatus()
                != EmergencyStatus.ASSIGNED) {

            return false;
        }

        // Release team
        team.setAvailable(true);

        // Reset emergency
        emergency.setAssignedTeamId(
                null
        );

        emergency.setStatus(
                EmergencyStatus.PENDING
        );

        // Remove assignment
        for (int i = 0;
             i < assignments.size();
             i++) {

            if (assignments
                    .get(i)
                    .getAssignmentId()
                    .equalsIgnoreCase(
                            assignmentId
                    )) {

                assignments.remove(i);

                return true;
            }
        }

        return false;
    }

    // Generate Assignment ID
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

                } catch (
                        NumberFormatException e) {

                    // Ignore invalid old
                    // assignment IDs
                }
            }
        }

        return "AS-" + (highestNumber + 1);
    }

    public ArrayList<Assignment>
    getAllAssignments() {

        return assignments;
    }

    public ArrayList<Assignment>
    getAssignments() {

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
                findTeamById(
                        teamId
                );

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

        // Safety for older/empty data files
        if (users == null) {
            users = new ArrayList<>();
        }

        if (admins == null) {
            admins = new ArrayList<>();
        }

        if (emergencies == null) {
            emergencies = new ArrayList<>();
        }

        if (teams == null) {
            teams = new ArrayList<>();
        }

        if (assignments == null) {
            assignments = new ArrayList<>();
        }
    }
}