package org.silli.sillibackend.services;

import org.silli.sillibackend.repositories.AccountRepository;

public class AccountSerivce {
    private final AccountRepository accountRepository;

    public AccountSerivce(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
}
