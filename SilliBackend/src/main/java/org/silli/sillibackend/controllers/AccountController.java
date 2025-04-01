package org.silli.sillibackend.controllers;

import org.silli.sillibackend.models.AccountDto;
import org.silli.sillibackend.services.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService)
    {
        this.accountService = accountService;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody AccountDto accountDto) {
        accountService.persist(accountDto);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody AccountDto accountDto) {
        var returnCode = accountService.login(accountDto);
        return ResponseEntity.status(returnCode).build();
    }

    @PostMapping("/who-am-i")
    public ResponseEntity<String> whoAmI(@RequestBody int id) {
        var publicInfo = accountService.introduce(id);

        if(publicInfo.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(publicInfo);
    }
}
