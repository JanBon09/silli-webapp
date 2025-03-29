package org.silli.sillibackend.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.logging.Logger;

@Component
public class JwtDecoderFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain){
        Cookie[] cookies = request.getCookies();
        Logger logger = Logger.getLogger(this.getClass().getName());


        logger.info("Number of cookies: " + cookies.length);

        try{
            filterChain.doFilter(request, response);
        } catch (Exception e){

        }
    }
}
