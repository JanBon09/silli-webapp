package org.silli.sillibackend.controllers;

import org.silli.sillibackend.models.Account;
import org.silli.sillibackend.models.AccountDto;
import org.silli.sillibackend.services.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/users")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService)
    {
        this.accountService = accountService;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody AccountDto accountDto) {
        var account = new Account();
        account.setUsername(accountDto.getUsername());
        account.setPassword(accountDto.getPassword());
        account.setEnabled(1);
        account.setAuthority("ROLE_USER");
        accountService.persist(account);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody AccountDto accountDto) {
        var returnCode = accountService.login(accountDto);
        return ResponseEntity.status(returnCode).build();
    }

}
