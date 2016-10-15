package com.sis.footballteams.controller;

import com.sis.footballteams.exceptions.TeamNotFoundException;
import com.sis.footballteams.service.FootballTeamService;
import com.sis.footballteams.vo.TeamVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/teams")
@Log4j2
public class FootballTeamController {

    private static final String JSON = "application/json; charset=utf-8";

    @Autowired
    private FootballTeamService footballTeamService;

    /**
     * Will retrieve a list with all teams in the system
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = JSON)
    public List<TeamVO> get() {
        log.info("Getting all teams...");
        return footballTeamService.getAllTeams();
    }


    /**
     * Will retrieve a team with all its details
     * @param teamId teamId to search for
     * @return
     */
    @RequestMapping(value = "/{teamId}", method = RequestMethod.GET, produces = JSON)
    public TeamVO getTeam(
            @PathVariable Integer teamId
    ) throws TeamNotFoundException {

        log.debug("Entered: getTeam");

        TeamVO teamVO=footballTeamService.getTeam(teamId);
        if (teamVO == null || teamVO.getId()==0) {
            log.error("Can not find any team with id {}", teamId);
            throw new TeamNotFoundException(teamId);
        }

        log.debug("Exiting: getTeam {}", teamVO);

        return teamVO;

    }


    /**
     * Will add a team to the database
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = JSON, produces = JSON)
    public TeamVO addContactDetails(
            @RequestBody @Valid TeamVO teamVO
    ) {

        log.debug("Entered: addTeam");
        return footballTeamService.addTeam(teamVO);
    }
}
