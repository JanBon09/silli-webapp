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

import java.util.logging.Logger;


// Filter responsible for cookie creation and assigment to users that successfully ran through authentication process
@Component
public class JwtCookieFilter extends OncePerRequestFilter {
    private final JwtTokenManagment jwtTokenManagment;

    public JwtCookieFilter(JwtTokenManagment jwtTokenManagment) {
        this.jwtTokenManagment = jwtTokenManagment;
    }

    // Could be moved into standalone service
    private Cookie createJwtCookie(String username){
        JwtClaimsSet jwtClaimsSet = jwtTokenManagment.jwtClaimSet(username);
        JwtEncoder encoder = jwtTokenManagment.jwtEncoder();
        JwsHeader jwsHeader = JwsHeader.with(() -> "HS256").build();
        String token = encoder.encode(JwtEncoderParameters.from(jwsHeader, jwtClaimsSet)).getTokenValue();


        Cookie cookie = new Cookie("JWT", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setMaxAge(900);

        cookie.setAttribute("SameSite", "strict");

        // Remember about this thing
        cookie.setPath("/");
        return cookie;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain){
        try{
            filterChain.doFilter(request, response);
        } catch(Exception e){

        }

        if(response.getStatus() == 200){
            String username = request.getParameter("username");
            if(username == null){
                response.setStatus(403);
                return;
            }
            response.addCookie(createJwtCookie(request.getParameter("username")));
        }

    }
}
