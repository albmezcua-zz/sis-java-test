package com.sis.footballteams.core

import com.sis.footballteams.config.TeamPayloadGenerator
import com.sis.footballteams.dbFootballTeam.entity.TeamEntity
import com.sis.footballteams.vo.TeamVO
import org.joda.time.LocalDateTime
import spock.lang.Specification


public abstract class AbstractUnitTest extends Specification {

    protected TeamVO stubTeamVO() {
        TeamPayloadGenerator payloadGenerator=new TeamPayloadGenerator();
        return payloadGenerator.generateTeam(TeamPayloadGenerator.Team.ADD_TEAMS)
    }

    protected TeamEntity stubTeamEntity() {
        TeamEntity teamEntity=Stub()
        teamEntity.city >> "Barcelona"
        teamEntity.name >> "FC. Barcelona"
        teamEntity.competition >> "La Liga"
        teamEntity.owner >> "Bartomeu"
        teamEntity.dateCreation >> new LocalDateTime()
        return teamEntity
    }
}