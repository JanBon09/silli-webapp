package org.silli.sillibackend.services;

import org.silli.sillibackend.models.ManipulableEntity;
import org.silli.sillibackend.repositories.AccountRepository;
import org.silli.sillibackend.repositories.EntityRepository;
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

    public boolean checkAuthByEntity(Authentication authentication, int manipulableEntityId,
                                     EntityRepository entityRepository) {
        String username = jwtDecodingService.decodeUsernameFromJWT(authentication);
        int accountIdAuth = accountRepository.findIdByUsername(username);

        int accountIdRepo = entityRepository.findOwner(manipulableEntityId);
        Logger logger = Logger.getLogger(this.getClass().getName());
        logger.info("Account id repo: " + accountIdRepo);

        return accountIdAuth == accountIdRepo;
    }
}
