package model;

import java.io.Serializable;

public class Assignment implements Serializable {

    private static final long serialVersionUID = 1L;

    private String assignmentId;
    private String emergencyId;
    private String teamId;
    private String assignedTime;
    private String notes;

    public Assignment(String assignmentId, String emergencyId,
                      String teamId, String assignedTime) {

        this.assignmentId = assignmentId;
        this.emergencyId = emergencyId;
        this.teamId = teamId;
        this.assignedTime = assignedTime;
        this.notes = "";
    }

    public String getAssignmentId() {
        return assignmentId;
    }

    public String getEmergencyId() {
        return emergencyId;
    }

    public String getTeamId() {
        return teamId;
    }

    public String getAssignedTime() {
        return assignedTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}