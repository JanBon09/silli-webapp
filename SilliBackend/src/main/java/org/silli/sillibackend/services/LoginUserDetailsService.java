package org.silli.sillibackend.services;

import org.silli.sillibackend.models.Account;
import org.silli.sillibackend.models.AccountUserDetails;
import org.silli.sillibackend.repositories.AccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// Service responsible   for that takes process in authentication by retrieving accounts with given username
@Service
public class LoginUserDetailsService implements UserDetailsService {
    private final AccountRepository accountRepository;

    public LoginUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);

        if(account == null) {
            throw new UsernameNotFoundException(username);
        }

        AccountUserDetails userDetails = new AccountUserDetails();

        userDetails.setUsername(username);
        userDetails.setPassword(account.getPassword());

        return userDetails;
    }
}
