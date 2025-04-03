package org.silli.sillibackend.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.silli.sillibackend.security.JwtTokenManagment;
import org.springframework.security.oauth2.jwt.BadJwtException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.time.Instant;

@Component
public class JwtDecoderFilter extends OncePerRequestFilter {

    private final JwtTokenManagment jwtTokenManagment;

    public JwtDecoderFilter(JwtTokenManagment jwtTokenManagment) {
        this.jwtTokenManagment = jwtTokenManagment;
    }

    // Custom filter for retrieving HttpOnly cookie with JWT inside.
    // After successfully retrieving a JWT it validates it and sends the JWT subject down the backend pipeline
    // for further use.
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain){
        Cookie[] cookies = request.getCookies();
        JwtDecoder jwtDecoder = jwtTokenManagment.jwtDecoder();
        Jwt jwt = null;

        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("JWT")) {
                try{
                    jwt = jwtDecoder.decode(cookie.getValue());
                    break;
                } catch(BadJwtException e){
                    response.setStatus(401);
                    return;
                }

            }
        }

        try{
            if(jwt == null || jwt.getExpiresAt().isBefore(Instant.now())) {
                response.setStatus(401);
                return;
            }
        } catch (NullPointerException e) {
            response.setStatus(500);
            return;
        }

        request.setAttribute("Subject", jwt.getSubject());

        try{
            filterChain.doFilter(request, response);
        } catch (Exception e){

        }
    }
}
