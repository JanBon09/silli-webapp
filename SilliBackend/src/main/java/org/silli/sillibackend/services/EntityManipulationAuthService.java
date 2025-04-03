package org.silli.sillibackend.services;

import org.silli.sillibackend.repositories.AccountRepository;
import org.silli.sillibackend.repositories.EntityRepository;
import org.springframework.stereotype.Service;

@Service
public class EntityManipulationAuthService {

    private final AccountRepository accountRepository;

    public EntityManipulationAuthService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    //Checks if username decoded from JWT is the same that is assigned as a owner of an object
    public boolean authBySubjectAndEntity(String subject, int manipulableEntityId,
                                     EntityRepository entityRepository) {
        int accountIdAuth = accountRepository.findIdByUsername(subject);
        int accountIdRepo = entityRepository.findOwner(manipulableEntityId);

        return accountIdAuth == accountIdRepo;
    }
}
