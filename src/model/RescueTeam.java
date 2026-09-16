package model;

import enums.TeamType;

public class RescueTeam extends ResponseTeam {

    public RescueTeam(String teamId, String teamName,
                      String contactNumber, int numberOfMembers) {

        super(teamId, teamName, TeamType.RESCUE,
                contactNumber, numberOfMembers);
    }

    @Override
    public void respondToEmergency() {
        System.out.println("Rescue team is responding to the emergency.");
    }
}