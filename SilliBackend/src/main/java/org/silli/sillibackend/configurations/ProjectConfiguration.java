package org.silli.sillibackend.configurations;

import org.silli.sillibackend.security.JwtTokenManagment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class ProjectConfiguration {
    private final AuthenticationProvider authenticationProvider;
    private final JwtTokenManagment jwtTokenManagment;


    public ProjectConfiguration(AuthenticationProvider authenticationProvider, JwtTokenManagment jwtTokenManagment) {
        this.authenticationProvider = authenticationProvider;
        this.jwtTokenManagment = jwtTokenManagment;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authenticationProvider(authenticationProvider);
        // No general authorization included, because there is a custom authorization logic implemented based on filters
        // and JWT

        return http.build();
    }

    @Bean
    public UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }


}