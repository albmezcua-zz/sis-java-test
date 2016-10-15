package com.sis.footballteams.dbFootballTeam.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "players")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;


}
