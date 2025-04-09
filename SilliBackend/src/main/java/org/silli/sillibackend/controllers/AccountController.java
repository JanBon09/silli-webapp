package org.silli.sillibackend.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

    // It could be done through filter but use of this is so narrow that making it an endpoint seems 'more clean'
    @DeleteMapping("/logout")
    public ResponseEntity<Object> logout(HttpServletRequest request, HttpServletResponse response) {

        // Logging user out through overriding current JWT cookie and making it MaxAge = 0
        Cookie cookie = new Cookie("JWT", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setMaxAge(0);
        cookie.setAttribute("SameSite", "strict");
        cookie.setPath("/");

        response.addCookie(cookie);

        return ResponseEntity.ok().build();
    }
}
