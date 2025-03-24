package org.silli.sillibackend.controllers;

import org.silli.sillibackend.models.Account;
import org.silli.sillibackend.models.AccountDto;
import org.silli.sillibackend.services.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/users")
public class AccountController {

    private final AccountService accountService;
    private final AuthenticationManager authenticationManager;

    public AccountController(AccountService accountService, AuthenticationManager authenticationManager)
    {
        this.accountService = accountService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody AccountDto accountDto) {
        var account = new Account();
        account.setUsername(accountDto.getUsername());
        account.setPassword(accountDto.getPassword());
        account.setEnabled(1);
        account.setAuthority("ROLE_USER");
        accountService.persist(account);

        Logger logger = Logger.getLogger(AccountController.class.getName());
        logger.info("HALO");

        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody AccountDto accountDto) {
        var returnCode = accountService.login(accountDto);
        return ResponseEntity.status(returnCode).build();
    }
}
