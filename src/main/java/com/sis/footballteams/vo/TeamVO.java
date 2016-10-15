package com.sis.footballteams.vo;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sis.footballteams.util.LocalDateTimeJSONDeserializer;
import com.sis.footballteams.util.LocalDateTimeJSONSerializer;

import lombok.Data;
import org.joda.time.LocalDateTime;


import javax.validation.constraints.NotNull;

import java.util.Set;

@Data
public class TeamVO {

    private Integer id;

    @NotNull(message = "Team is required")
    private String team;
    @NotNull(message = "city is required")
    private String city;
    @NotNull(message = "owner is required")
    private String owner;
    @NotNull(message = "competition is required")
    private String competition;
    @NotNull(message = "Players are required")
    private Set<String> players;

    @JsonDeserialize(using = LocalDateTimeJSONDeserializer.class)
    @JsonSerialize(using = LocalDateTimeJSONSerializer.class)
    private LocalDateTime creationDate;



}
