package com.sis.footballteams.service;


import com.sis.footballteams.dbFootballTeam.entity.TeamEntity;
import com.sis.footballteams.exceptions.TeamNotFoundException;
import com.sis.footballteams.vo.TeamVO;

import java.util.List;

public interface FootballTeamService {


    List<TeamVO> getAllTeams();

    TeamVO getTeam(Integer teamId);

    TeamVO addTeam(TeamVO teamVO) throws TeamNotFoundException;

    TeamEntity saveTeam(TeamEntity teamEntity);
}
