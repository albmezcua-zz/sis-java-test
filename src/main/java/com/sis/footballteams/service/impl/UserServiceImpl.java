package com.sis.footballteams.service.impl;

import com.sis.footballteams.dbFootballTeam.annotations.FootBallTeamReadTx;
import com.sis.footballteams.dbFootballTeam.dao.UserRepository;
import com.sis.footballteams.dbFootballTeam.entity.User;
import com.sis.footballteams.security.IdentityHeaderDetails;
import com.sis.footballteams.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    @FootBallTeamReadTx
    public boolean isValid(IdentityHeaderDetails headerDetails) throws AuthenticationException {

         User user=userRepository.findUserByUuidAndSecret(headerDetails.getUserUuid(),headerDetails.getSecret());
        if(user!=null){
            return true;
        }

        return false;
    }
}
