package model;

import enums.TeamType;

import java.io.Serializable;

public abstract class ResponseTeam implements Serializable {

    private static final long serialVersionUID = 1L;

    private String teamId;
    private String teamName;
    private TeamType teamType;
    private String contactNumber;
    private int numberOfMembers;
    private boolean available;

    public ResponseTeam(String teamId, String teamName,
                        TeamType teamType, String contactNumber,
                        int numberOfMembers) {

        this.teamId = teamId;
        this.teamName = teamName;
        this.teamType = teamType;
        this.contactNumber = contactNumber;
        this.numberOfMembers = numberOfMembers;
        this.available = true;
    }

    // Protected helper method for child classes
    protected String getTeamResponseMessage() {
        return "Team " + teamName
                + " is responding to the emergency.";
    }

    // Abstract method implemented by child classes
    public abstract void respondToEmergency();

    public String getTeamId() {
        return teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public TeamType getTeamType() {
        return teamType;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public int getNumberOfMembers() {
        return numberOfMembers;
    }

    public void setNumberOfMembers(int numberOfMembers) {
        this.numberOfMembers = numberOfMembers;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}