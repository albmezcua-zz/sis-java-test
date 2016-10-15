package com.sis.footballteams.service.impl;

import com.sis.footballteams.dbFootballTeam.annotations.FootBallTeamReadTx;
import com.sis.footballteams.dbFootballTeam.annotations.FootballTeamWriteTx;
import com.sis.footballteams.dbFootballTeam.dao.TeamRepository;
import com.sis.footballteams.dbFootballTeam.entity.PlayerEntity;
import com.sis.footballteams.dbFootballTeam.entity.TeamEntity;
import com.sis.footballteams.exceptions.TeamNotFoundException;
import com.sis.footballteams.service.FootballTeamService;
import com.sis.footballteams.vo.TeamVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Log4j2
public class FootballTeamServiceImpl implements FootballTeamService {

    @Autowired
    TeamRepository teamRepository;


    @Override
    @FootBallTeamReadTx
    public List<TeamVO> getAllTeams() {
        List<TeamVO> filterTypeVOs = new ArrayList<>();


            teamRepository.findAll().forEach(f -> {
                filterTypeVOs.add(convertToVO(f));
            });

        log.info("Returning a total of {} teams", filterTypeVOs.size());

        return filterTypeVOs;
    }

    @Override
    @FootBallTeamReadTx
    public TeamVO getTeam(Integer teamId) {
        log.info("Getting team with id {]", teamId);
        TeamEntity team=teamRepository.findOne(teamId);
        if(team==null){
            log.error("Can not find any team with id {}", teamId);
            throw new TeamNotFoundException(teamId);
        }
        return convertToVO(team);
    }

    @Override
    public TeamVO addTeam(TeamVO teamVO) throws TeamNotFoundException {
        return convertToVO(saveTeam(convertToEntity(teamVO)));
    }


    @Override
    @FootballTeamWriteTx
    public TeamEntity saveTeam(TeamEntity teamEntity) {
        log.info("Saving team...");
        return teamRepository.saveAndFlush(teamEntity);
    }


    /**
     * Converts a VO to an Entity
     *
     * @param teamVO the vo to convert into an entity
     * @return Request object
     */
    public TeamEntity convertToEntity(TeamVO teamVO) {
        TeamEntity team = null;

        log.info("Generating entity from teamVO: {}",teamVO);
        if (teamVO.getId() == null) {
            log.info("Adding new team  with name : {}", teamVO.getTeam());
            team = new TeamEntity();
            team.setCity(teamVO.getCity());
            team.setOwner(teamVO.getOwner());
            team.setCompetition(teamVO.getCompetition());
            team.setDateCreation(teamVO.getCreationDate());
            team.setName(teamVO.getTeam());
            Set<PlayerEntity> listPlayers= teamVO.getPlayers().stream()
                    .map(p -> buildPlayerVo(p))
                    .collect(Collectors.toSet());

            team.setPlayerList(listPlayers);
        }else{

            team=teamRepository.findOne(teamVO.getId());
            if(team!=null){
                log.info("Updating existing team with id {} and name : {}", team.getId(),team.getName());
                team.setCity(teamVO.getCity());
                team.setOwner(teamVO.getOwner());
                team.setCompetition(teamVO.getCompetition());
                team.setDateCreation(teamVO.getCreationDate());
                team.setName(teamVO.getTeam());
                Set<PlayerEntity> listPlayers= teamVO.getPlayers().stream()
                        .map(p -> buildPlayerVo(p))
                        .collect(Collectors.toSet());

                team.setPlayerList(listPlayers);
            }else{
                log.error("Can not find any team with id {}", teamVO.getId());
                throw new TeamNotFoundException(teamVO.getId());
            }
        }

        log.info("Exiting from convert to Entity : {}", teamVO);
        return team;
    }

    private PlayerEntity buildPlayerVo(String name) {
        PlayerEntity player=new PlayerEntity();
        player.setName(name);
        return player;
    }

    public TeamVO convertToVO(TeamEntity team) {
        log.info("converting team entity to vo for team {} with id {}", team.getName(), team.getId());
        TeamVO teamVO = new TeamVO();
        teamVO.setId(team.getId());
        teamVO.setCity(team.getCity());
        teamVO.setCompetition(team.getCompetition());
        teamVO.setOwner(team.getOwner());
        log.info("getting players from team {}", team.getName());
        teamVO.setPlayers(getPlayersVO(team.getPlayerList()));
        log.info(" got players from team  {} --> {}", team.getName(), teamVO.getPlayers());
        teamVO.setTeam(team.getName());
        teamVO.setCreationDate(team.getDateCreation());
        log.info("returning info for team {} --> {}", team.getName(), teamVO);
        return teamVO;
    }


    public Set<String> getPlayersVO(Set<PlayerEntity> listPlayersEntity) {
              return  listPlayersEntity.stream()
                        .map(p -> p.getName())
                        .collect(Collectors.toSet());

    }

}
