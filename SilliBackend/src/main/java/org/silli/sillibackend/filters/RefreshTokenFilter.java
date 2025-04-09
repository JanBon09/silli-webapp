package org.silli.sillibackend.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class RefreshTokenFilter extends OncePerRequestFilter {

    private Cookie createRefreshTokenCookie(){
        return new Cookie("e", null);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain){


        try{
            filterChain.doFilter(request, response);
        } catch(Exception e){

        }
    }
}
