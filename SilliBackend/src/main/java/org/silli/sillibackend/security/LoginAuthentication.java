package org.silli.sillibackend.security;

import org.silli.sillibackend.models.AccountDto;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

// Component responsible for authenticating users trying to login
@Component
public class LoginAuthentication implements AuthenticationProvider {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public LoginAuthentication(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(AccountDto.class);
    }

    // Method takes Authentication object made by AccountService and compares it with the actual data stored in database
    // for account with given username
    @Override
    public Authentication authenticate(Authentication authentication) throws BadCredentialsException {
        // Username and password that were send via request
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        // Retrieving UserDetails for account with given username
        UserDetails userDetails;
        try{
             userDetails = userDetailsService.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException("User with given username doesn't exist");
        }

        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        } else {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
