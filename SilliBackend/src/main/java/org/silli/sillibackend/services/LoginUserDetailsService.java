package org.silli.sillibackend.services;

import org.silli.sillibackend.models.Account;
import org.silli.sillibackend.models.AccountUserDetails;
import org.silli.sillibackend.repositories.AccountRepository;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

// Service responsible for retrieving UserDetails from db. Used in authentication process
@Service
public class LoginUserDetailsService implements UserDetailsService {
    private final AccountRepository accountRepository;

    public LoginUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    // Method
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
