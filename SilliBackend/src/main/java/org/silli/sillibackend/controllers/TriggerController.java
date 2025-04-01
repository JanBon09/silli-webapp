package org.silli.sillibackend.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.silli.sillibackend.models.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.logging.Logger;

@RestController
@RequestMapping("/trigger")
public class TriggerController {

    @GetMapping
    public ResponseEntity<String> test(HttpServletRequest request) {
        Logger logger = Logger.getLogger(this.getClass().getName());

        logger.info((String) request.getAttribute("Subject"));

        return ResponseEntity.ok("!Trigger test!");
    }
}
