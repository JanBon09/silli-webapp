package org.silli.sillibackend.controllers;

import org.silli.sillibackend.models.GroupDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("group-membership")
public class GroupMembershipController {

    @PostMapping("/join")
    public ResponseEntity<Object> join(Authentication authentication, @RequestBody GroupDto groupDto){
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String username = jwt.getSubject();

        return ResponseEntity.ok().build();
    }
}
