package model;

import enums.EmergencyStatus;
import enums.EmergencyType;
import enums.Priority;

import java.io.Serializable;

public class Emergency implements Serializable {

    private static final long serialVersionUID = 1L;

    private String emergencyId;
    private String reportedBy;
    private EmergencyType type;
    private Priority priority;
    private String location;
    private String description;
    private EmergencyStatus status;
    private String dateTime;
    private String assignedTeamId;

    // Default / No-Argument Constructor
    public Emergency() {
        this.emergencyId = "";
        this.reportedBy = "";
        this.type = null;
        this.priority = null;
        this.location = "";
        this.description = "";
        this.dateTime = "";
        this.status = EmergencyStatus.PENDING;
        this.assignedTeamId = null;
    }

    // Parameterized Constructor
    public Emergency(String emergencyId, String reportedBy,
                     EmergencyType type, Priority priority,
                     String location, String description,
                     String dateTime) {

        this.emergencyId = emergencyId;
        this.reportedBy = reportedBy;
        this.type = type;
        this.priority = priority;
        this.location = location;
        this.description = description;
        this.dateTime = dateTime;

        this.status = EmergencyStatus.PENDING;
        this.assignedTeamId = null;
    }

    public String getEmergencyId() {
        return emergencyId;
    }

    public String getReportedBy() {
        return reportedBy;
    }

    public EmergencyType getType() {
        return type;
    }

    public void setType(EmergencyType type) {
        this.type = type;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EmergencyStatus getStatus() {
        return status;
    }

    public void setStatus(EmergencyStatus status) {
        this.status = status;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getAssignedTeamId() {
        return assignedTeamId;
    }

    public void setAssignedTeamId(String assignedTeamId) {
        this.assignedTeamId = assignedTeamId;
    }
}