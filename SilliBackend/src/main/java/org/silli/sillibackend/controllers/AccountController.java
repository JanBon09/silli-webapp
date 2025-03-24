package org.silli.sillibackend.controllers;

import org.silli.sillibackend.models.Account;
import org.silli.sillibackend.services.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<Iterable<Account>> findAll() {
        return ResponseEntity.ok(accountService.getAll());
    }

    @PostMapping("/create")
    public ResponseEntity<Object> register(@RequestBody Account account) {
        accountService.persist(account);

        return ResponseEntity.ok().build();
    }
}
