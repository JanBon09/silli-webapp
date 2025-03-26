package org.silli.sillibackend.services;

import org.silli.sillibackend.models.ManipulableEntity;
import org.silli.sillibackend.repositories.AccountRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class EntityManipulationAuthService {

    private final AccountRepository accountRepository;
    private final JwtDecodingService jwtDecodingService;

    public EntityManipulationAuthService(AccountRepository accountRepository, JwtDecodingService jwtDecodingService) {
        this.accountRepository = accountRepository;
        this.jwtDecodingService = jwtDecodingService;
    }

    public boolean checkAuthByEntity(Authentication authentication, ManipulableEntity manipulableEntity) {
        String username = jwtDecodingService.decodeUsernameFromJWT(authentication);

        int id = accountRepository.findIdByUsername(username);

        return id == manipulableEntity.getAccountId();
    }

    public boolean checkAuthById(Authentication authentication, int manipulableEntityCreatorId) {
        String username = jwtDecodingService.decodeUsernameFromJWT(authentication);

        int id = accountRepository.findIdByUsername(username);

        return id == manipulableEntityCreatorId;
    }
}
