package com.sis.footballteams.service

import com.sis.footballteams.core.AbstractUnitTest
import com.sis.footballteams.dbFootballTeam.dao.TeamRepository
import com.sis.footballteams.dbFootballTeam.entity.TeamEntity
import com.sis.footballteams.exceptions.TeamNotFoundException
import com.sis.footballteams.service.impl.FootballTeamServiceImpl
import com.sis.footballteams.vo.TeamVO

class FootballTeamServiceTest extends AbstractUnitTest {

    FootballTeamServiceImpl footballTeamService

    TeamRepository mockTeamRepository


    void setup() {
        footballTeamService = new FootballTeamServiceImpl()
        footballTeamService.teamRepository = mockTeamRepository = Mock(TeamRepository)

    }

    void 'on getList select all teams from Repository'() {
        given:
        List<TeamVO> expectedTeams = []
        expectedTeams<<stubTeamVO()
        List<TeamEntity> expectedTeamList = []
        expectedTeamList<<stubTeamEntity()

        when:
            List<TeamVO> returnedTeams = footballTeamService.getAllTeams()
        then:
            1 * mockTeamRepository.findAll() >> expectedTeamList
            0 * _
        and:
            returnedTeams[0].city == expectedTeams[0].city
            returnedTeams[0].team == expectedTeams[0].team
            returnedTeams[0].owner == expectedTeams[0].owner
            returnedTeams[0].competition == expectedTeams[0].competition
    }

    void 'on get by an specific team then the team is returned'() {
        given:
        def teamId=1
        TeamVO expectedTeam = stubTeamVO()
        TeamEntity expectedTeamEnt = stubTeamEntity()

        when:
        TeamVO returnedTeam = footballTeamService.getTeam(teamId)
        then:
        1 * mockTeamRepository.findOne(teamId) >> expectedTeamEnt
        0 * _
        and:
        returnedTeam.city == expectedTeam.city
        returnedTeam.team == expectedTeam.team
        returnedTeam.owner == expectedTeam.owner
        returnedTeam.competition == expectedTeam.competition
    }


    void 'when getTeam receives and id that is not in the database then throw a TeamNotFoundException'() {
        given:
        def teamId=0
            TeamEntity expectedTeamEnt = null
        when:
            footballTeamService.getTeam(teamId)
        then:
            1 * mockTeamRepository.findOne(teamId) >> expectedTeamEnt
            0 * _
        and:
        TeamNotFoundException ex = thrown()
        ex.message == 'Team  not found. teamId='+teamId

    }


    void 'on saving a new team with no validation errors then the team is saved and returned'() {
        given:
        TeamVO expectedTeam = stubTeamVO()
        TeamEntity expectedTeamEnt = stubTeamEntity()

        when:
        TeamEntity returnedTeam = footballTeamService.saveTeam(expectedTeamEnt)
        then:
        1 * mockTeamRepository.saveAndFlush(expectedTeamEnt) >> expectedTeamEnt
        0 * _
        and:
        returnedTeam.city == expectedTeam.city
        returnedTeam.name == expectedTeam.team
        returnedTeam.owner == expectedTeam.owner
        returnedTeam.competition == expectedTeam.competition
    }

    void 'on updating an existing team with id can not be found then throw a TeamNotFoundException'() {
        given:
        def teamId=0
        TeamVO expectedTeam = stubTeamVO()
        expectedTeam.id= teamId
        TeamEntity expectedTeamEnt = null
        when:
        footballTeamService.addTeam(expectedTeam)
        then:
        1 * mockTeamRepository.findOne(teamId) >> expectedTeamEnt
        0 * _
        and:
        TeamNotFoundException ex = thrown()
        ex.message == 'Team  not found. teamId='+teamId

    }
}