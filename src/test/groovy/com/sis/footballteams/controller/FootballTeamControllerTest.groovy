package com.sis.footballteams.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.sis.footballteams.config.ExceptionHandlerExceptionResolverBuilder
import com.sis.footballteams.config.TeamPayloadGenerator
import com.sis.footballteams.service.FootballTeamService
import com.sis.footballteams.util.Json
import com.sis.footballteams.util.ObjectMapperFactory
import com.sis.footballteams.vo.TeamVO
import org.springframework.http.MediaType
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup


class FootballTeamControllerTest extends Specification {


    static final String TEST_BASE_PATH = '/teams'

    FootballTeamController footballTeamController
    FootballTeamService mockFootballTeamService

    MockMvc mockMvc
    ObjectMapper objectMapper


    void setup() {
        mockFootballTeamService = Mock(FootballTeamService)

        footballTeamController = new FootballTeamController(
                footballTeamService: mockFootballTeamService
        )

        objectMapper = ObjectMapperFactory.createObjectMapper()
        MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter()
        jsonMessageConverter.setObjectMapper(objectMapper)

        mockMvc = standaloneSetup(footballTeamController).alwaysDo(print())
                .setMessageConverters(jsonMessageConverter)
                .setHandlerExceptionResolvers(ExceptionHandlerExceptionResolverBuilder.build(jsonMessageConverter, objectMapper, ExceptionHandlerController))
                .build()
    }

    void 'on GET, return all'() {
        given:
            TeamVO expectedTeam= new TeamVO()
            List<TeamVO> expectedTeamList = [expectedTeam]

        when:
        ResultActions resultActions = mockMvc.perform(
                get(TEST_BASE_PATH+'/list')
                        .accept(MediaType.APPLICATION_JSON)
        )

        then:
            1 * mockFootballTeamService.getAllTeams() >> expectedTeamList
            0 * _

        and:
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(Json.toJson(expectedTeamList)))
                .andReturn()
    }

    void 'on GET by id, return a specific team'() {
        given:
            Integer teamId = 1
            TeamVO expectedTeam = new TeamVO()
            expectedTeam.setId(1)

        when:
            ResultActions resultActions = mockMvc.perform(
                   get(TEST_BASE_PATH + "/" + teamId)
                            .accept(MediaType.APPLICATION_JSON)
            )

        then:
            1 * mockFootballTeamService.getTeam(teamId) >> expectedTeam
            0 * _

        and:
            resultActions
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andExpect(content().json(Json.toJson(expectedTeam)))
                    .andReturn()
    }

    void 'on GET by id where id not being present, return 404'() {
        given:
            Integer mockedTeamId = 0
            TeamVO expectedTeam = new TeamVO();
            expectedTeam.setId(mockedTeamId);

        when:
            ResultActions resultActions = mockMvc.perform(
                   get(TEST_BASE_PATH + "/{teamId}", mockedTeamId)
                            .accept(MediaType.APPLICATION_JSON)
            )

        then:
            1 * mockFootballTeamService.getTeam(mockedTeamId) >> expectedTeam
            0 * _

        and:
            resultActions.andExpect(status().isNotFound())

    }


    def 'on CREATE,create a new team return message'(){
        given:

        TeamPayloadGenerator payloadGenerator=new TeamPayloadGenerator();
        TeamVO teamVO =  payloadGenerator.generateTeam(TeamPayloadGenerator.Team.ADD_TEAMS)
        when:
        ResultActions resultActions = mockMvc.perform(post(TEST_BASE_PATH + "/add")
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(teamVO))
                .contentType(MediaType.APPLICATION_JSON))
        then:
        1 * mockFootballTeamService.addTeam(teamVO)>>teamVO
        0 * _
        and:
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(Json.toJson(teamVO)))
                .andReturn()

    }
}