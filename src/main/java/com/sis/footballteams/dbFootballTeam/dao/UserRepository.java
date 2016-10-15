package com.sis.footballteams.dbFootballTeam.dao;

import com.sis.footballteams.dbFootballTeam.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("FROM User WHERE userUuid = :userUuid and secret=:secret")
    User findUserByUuidAndSecret(@Param("userUuid") String userUuid,@Param("secret") String secret);

}
