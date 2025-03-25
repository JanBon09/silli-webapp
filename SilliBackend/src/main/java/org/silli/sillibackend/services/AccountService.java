package org.silli.sillibackend.services;

import org.silli.sillibackend.models.Account;
import org.silli.sillibackend.models.AccountDto;
import org.silli.sillibackend.repositories.AccountRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// Service responsible for communicating authentication endpoints with repository
//
@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationProvider authenticationProvider;

    public AccountService(AccountRepository accountRepository, PasswordEncoder passwordEncoder,
                          AuthenticationProvider authenticationProvider)
    {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationProvider = authenticationProvider;
    }

    // Method receiving Account object from /users/register endpoint and giving it to the repository for persisting it
    // Implements PasswordEncoder
    public void persist(AccountDto accountDto){
        var account = new Account();
        account.setUsername(accountDto.getUsername());
        account.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        account.setEnabled(1);
        account.setAuthority("ROLE_USER");

        accountRepository.save(account);
    }

    // Method receiving AccountDto from /users/login endpoint and running it through authentication process
    public int login(AccountDto accountDto){
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(accountDto.getUsername(), accountDto.getPassword());
        try{
            authenticationProvider.authenticate(authenticationToken);
        } catch (BadCredentialsException e){
            return 401;
        } catch (UsernameNotFoundException e){
            return 404;
        }

        return 200;
    }

    public Iterable<Account> getAll(){
        return accountRepository.findAll();
    }

}
