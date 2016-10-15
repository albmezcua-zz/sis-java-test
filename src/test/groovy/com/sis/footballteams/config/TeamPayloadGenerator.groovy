package com.sis.footballteams.config

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.sis.footballteams.util.ObjectMapperFactory
import com.sis.footballteams.vo.TeamVO


class TeamPayloadGenerator {

    ObjectMapper objectMapper = ObjectMapperFactory.createObjectMapper()

    public static enum Team {
        ADD_TEAMS( 'addTeam.json');

        String jsonFile

        Team(String jsonFile) {
            this.jsonFile = jsonFile
        }

    }
    public TeamVO generateTeam(Team schedule) throws IOException {
        String file = schedule.jsonFile
        return generateTeam("/payloads/${file}")
    }

    public TeamVO generateTeam(String pathPayLoad) throws IOException {
        String jsonAsStream = this.getClass().getResource( '/datatest/addTeam.json' ).text
        return objectMapper.readValue(jsonAsStream, TeamVO.class)
    }

}
