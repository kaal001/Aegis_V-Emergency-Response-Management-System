package model;

import enums.TeamType;

public class AmbulanceTeam extends ResponseTeam {

    public AmbulanceTeam(String teamId, String teamName,
                         String contactNumber, int numberOfMembers) {

        super(teamId, teamName, TeamType.AMBULANCE,
                contactNumber, numberOfMembers);
    }

    @Override
    public void respondToEmergency() {
        System.out.println("Ambulance team is responding to the emergency.");
    }
}