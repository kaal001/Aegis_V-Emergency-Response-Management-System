package model;

import enums.TeamType;

public class SecurityTeam extends ResponseTeam {

    public SecurityTeam(String teamId, String teamName,
                        String contactNumber, int numberOfMembers) {

        super(teamId, teamName, TeamType.SECURITY,
                contactNumber, numberOfMembers);
    }

    @Override
    public void respondToEmergency() {
        System.out.println("Security team is responding to the emergency.");
    }
}