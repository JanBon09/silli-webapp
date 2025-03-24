package org.silli.sillibackend.services;

import org.silli.sillibackend.models.Account;
import org.silli.sillibackend.models.AccountDto;
import org.silli.sillibackend.repositories.AccountRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public void persist(Account account){
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
    }

    public int login(AccountDto accountDto){
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(accountDto.getUsername(), accountDto.getPassword());
        try{
            authenticationProvider.authenticate(authenticationToken);
        } catch (BadCredentialsException e){
            return 401;
        }

        return 200;
    }

    public Iterable<Account> getAll(){
        return accountRepository.findAll();
    }

}
