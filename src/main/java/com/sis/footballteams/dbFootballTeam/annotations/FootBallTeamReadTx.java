package com.sis.footballteams.dbFootballTeam.annotations;

import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Transactional(value = "footballTeamsTransactionManager", readOnly = true)
public @interface FootBallTeamReadTx {

    //For read operation readOnly should be true.
    //The database operations will go to slave.

}
