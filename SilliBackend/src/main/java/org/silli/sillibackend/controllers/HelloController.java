package org.silli.sillibackend.controllers;

import org.silli.sillibackend.models.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public ResponseEntity<Message> hello() {
        return ResponseEntity.ok(new Message("Hello world", "No witam witam"));
    }
}
