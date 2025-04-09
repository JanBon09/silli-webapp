package org.silli.sillibackend.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.silli.sillibackend.security.RefreshTokenManagment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping("/trigger")
public class TriggerController {

    private final RefreshTokenManagment refreshTokenManagment;

    public TriggerController(RefreshTokenManagment refreshTokenManagment) {
        this.refreshTokenManagment = refreshTokenManagment;
    }

    @GetMapping
    public ResponseEntity<String> test(HttpServletRequest request) {

        PrivateKey privateKey;

        try{
            privateKey = refreshTokenManagment.getKeyFactory();
        } catch(NoSuchAlgorithmException e){
            return ResponseEntity.status(404).body(e.getMessage());
        } catch(InvalidKeySpecException e){
            return ResponseEntity.status(401).body(e.getMessage());
        }


        return ResponseEntity.ok().body("All good");
    }
}
