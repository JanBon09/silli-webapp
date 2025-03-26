package org.silli.sillibackend.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.silli.sillibackend.security.JwtTokenManagment;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


@Component
public class JwtCookieFilter extends OncePerRequestFilter {
    private final JwtTokenManagment jwtTokenManagment;

    public JwtCookieFilter(JwtTokenManagment jwtTokenManagment) {
        this.jwtTokenManagment = jwtTokenManagment;
    }

    private Cookie createJwtCookie(String username){
        JwtClaimsSet jwtClaimsSet = jwtTokenManagment.jwtClaimSet(username);
        JwtEncoder encoder = jwtTokenManagment.jwtEncoder();
        JwsHeader jwsHeader = JwsHeader.with(() -> "HS256").build();
        String token = encoder.encode(JwtEncoderParameters.from(jwsHeader, jwtClaimsSet)).getTokenValue();


        Cookie cookie = new Cookie("JWT", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setMaxAge(300);
        return cookie;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain){


        try{
            filterChain.doFilter(request, response);
        }
        catch(Exception e){

        }

        if(response.getStatus() == 200){
            response.addCookie(createJwtCookie(request.getParameter("username")));
        }

    }
}
