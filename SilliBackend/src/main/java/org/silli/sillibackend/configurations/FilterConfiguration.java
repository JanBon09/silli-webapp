package org.silli.sillibackend.configurations;

import org.silli.sillibackend.filters.JwtCookieFilter;
import org.silli.sillibackend.security.JwtTokenManagment;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {
    private final JwtCookieFilter jwtCookieFilter;

    public FilterConfiguration(JwtCookieFilter jwtCookieFilter) {
        this.jwtCookieFilter = jwtCookieFilter;
    }

    @Bean
    public FilterRegistrationBean<JwtCookieFilter> cookieFilter() {
        FilterRegistrationBean<JwtCookieFilter> registrationBean =
                new FilterRegistrationBean<>();
        registrationBean.setFilter(jwtCookieFilter);
        registrationBean.addUrlPatterns("/users/login"); // Only apply to /test endpoints
        registrationBean.setOrder(1); // Execution order
        return registrationBean;
    }

}
