package model;

import enums.TeamType;

public class FireTeam extends ResponseTeam {

    public FireTeam(String teamId, String teamName,
                    String contactNumber, int numberOfMembers) {

        super(teamId, teamName, TeamType.FIRE,
                contactNumber, numberOfMembers);
    }

    @Override
    public void respondToEmergency() {
        System.out.println("Fire team is responding to the emergency.");
    }
}