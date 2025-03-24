package org.silli.sillibackend.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.Arrays;
import java.util.List;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain){
        List<Cookie> cookies = Arrays.asList(request.getCookies());
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("JWT")) {

            }
        }
        try {
            filterChain.doFilter(request, response);
        } catch (Exception e) {

        }
    }
}
