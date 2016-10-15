package com.sis.footballteams.dbFootballTeam.dao;

import com.sis.footballteams.dbFootballTeam.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, Integer> {
}
