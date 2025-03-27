package org.silli.sillibackend.services;

import org.silli.sillibackend.models.GroupDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

// Simple class for decoding username from JWT send int 'Authorization' header
@Service
public class JwtDecodingService {

    public String decodeUsernameFromJWT(Authentication authentication){
        Jwt jwt = (Jwt) authentication.getPrincipal();
        return jwt.getSubject();
    }
}
