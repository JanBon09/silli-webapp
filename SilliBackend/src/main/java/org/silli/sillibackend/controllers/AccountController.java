package org.silli.sillibackend.controllers;

import org.silli.sillibackend.models.Account;
import org.silli.sillibackend.repositories.AccountRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin("http://localhost:4200")
@RequestMapping("/users")
public class AccountController {
    public final AccountRepository accountRepository;

    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping
    public ResponseEntity<Iterable<Account>> findAll() {
        return ResponseEntity.ok(accountRepository.findAll());
    }

    @PostMapping
    public void createAccount(){

    }
}
