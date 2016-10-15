package com.sis.footballteams.dbFootballTeam.annotations;

import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Transactional(value = "footballTeamsTransactionManager", readOnly = false)
public @interface FootballTeamWriteTx {

    //For write  operations readOnly should be false.By default this attribute "should" be false.
    //Set it just in case.
    //The database operations will go to master.

}
