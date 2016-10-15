package com.sis.footballteams.exceptions;


public class TeamNotFoundException extends RuntimeException {

    private static final String MESSAGE_FORMAT = "Team  not found. teamId=%s";
    public final Integer teamId;

    public TeamNotFoundException(Integer teamId) {
        super(String.format(MESSAGE_FORMAT, teamId));
        this.teamId = teamId;
    }
}
