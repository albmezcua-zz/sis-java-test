package com.sis.footballteams.dbFootballTeam.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import com.sis.footballteams.util.LocalDateTimeJSONDeserializer;
import com.sis.footballteams.util.LocalDateTimeJSONSerializer;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "teams")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "owner", nullable = false)
    private String owner;

    @Column(name = "competition", nullable = false)
    private String competition;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "team_players",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    private Set<PlayerEntity> playerList = new HashSet<>();


    @Column(name = "date_creation")
    @JsonDeserialize(using = LocalDateTimeJSONDeserializer.class)
    @JsonSerialize(using = LocalDateTimeJSONSerializer.class)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime dateCreation;


}
