package org.silli.sillibackend.controllers;

import org.silli.sillibackend.security.JwtTokenManagment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trigger")
public class TriggerController {
    @GetMapping
    public void trigger(){

    }
}
