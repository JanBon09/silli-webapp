package org.silli.sillibackend.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
public class AuthenticationConfiguration {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        String usersByUsernameQuery = "SELECT username, password, enabled FROM accounts WHERE username LIKE ?";
        String authsByUsernameQuery = "SELECT username, authority FROM accounts WHERE username LIKE ?";

        var userDetailsService = new JdbcUserDetailsManager(dataSource);
        userDetailsService.setUsersByUsernameQuery(usersByUsernameQuery);
        userDetailsService.setAuthoritiesByUsernameQuery(authsByUsernameQuery);

        return userDetailsService;
    }
}
